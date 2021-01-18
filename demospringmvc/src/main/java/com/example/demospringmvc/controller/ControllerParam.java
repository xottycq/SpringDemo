/**
 * 1.@RequestMapping:修饰类或方法，将请求路径（URI）转化为执行相应的方法，其属性值成为是否执行该方法的约束条件
 * <p>
 * 2.数据绑定之V2C传参：
 * A.URI中参数传递
 * 0)方法中的参数为HttpServletRequest类型，request.getParameter("参数名")用来获取URI中的参数值
 * 1)方法中申明参数，该参数名与URI中的参数名完全一致，URI中缺少任一参数都将报错。
 * 2)方法中申明参数，参数用@RequestParam修饰，name属性用来映射URI中参数名称，required属性表明是否必须
 * 3)方法中申明对象参数，URI中参数名须与对象属性名完全一致
 * 4)方法中申明嵌套对象参数，URI中参数名须与为"嵌套对象名.属性名"
 * 5)方法注解@requesMaping的参数（URI）中使用{variable}，方法中申明的参数用@PathVariable("variable")来修饰
 * B.Header中的参数传递
 * 1）方法中申明参数，参数用@CookieValue/@CookieValue("cookie中的属性名")修饰，以便获取cookie中的相应属性值
 * 2）方法中申明参数，参数用@RequestHeader/@RequestHeader(required = false, value = "header属性名")修饰，以便获取header中的相应属性值
 * C.Body中的参数传递（POST）
 * 1）若Content-Type="application/x-www-form-urlencoded"，A中的方法都适用，Body中的数据须是js对象
 * 2）若Content-Type="application/application/json"，方法中申明的参数须用@RequestBody修饰，Body中的数据须是json字符串
 * <p>
 * 3.数据绑定之C2V传值：
 * 1）ModelAndView/Model/Map---通过键值对将值传给JSP，JSP中通过引用键（${键名}）来获取值；
 * 2）@ModelAttribute--将值提前传给Model（带参数的键为参数，不带参数的自行赋值或隐式确定，值为返回值或显式赋值），之后按照1）中的方法自动转给JSP
 * 3）@SessionAttribute--将需要跨请求（同一会话）传递的Model值放到其中
 * 4）JSON---通常须指定接收数据的格式为json（否则收到的是json字符串），通常前端由js接收和处理后再放入JSP，
 *           后端用@responseBody注解或直接由response的write输出。
 * Attribute优先顺序：Model/ModelMap.addAttribute-->request.setAttribute-->request.getSession.setAttribute
 * 4.File--上传（multipart/data-form）、下载（APPLICATION_OCTET_STREAM）均需特定的格式和方法来处理
 * <p>
 * 5.controller方法返回值主要有下列几种情况：
 * 1）String--jsp的文件名称，直接跳转到该页面/前转（"forward:/itemEdit.action"）/重定向（"redirect:/item/itemList.action"）
 * 2）ModelAndView--数据和jsp文件名都封装其中
 * 3）Map---值通过map映射到Model中，jsp文件名称为requestMapping中的路径名
 * 4）@responseBody---1～3均是将返回值封装到ModelAndView中，而本例是将返回值封装到response的body中，基础类型直接封装，对象均封装成js对象。
 * 包括：Object、ObjectP[]、List<Object>等。
 * 5）void--页面由request带参前转/response页面重定向/response直接write；也可以直接write json数据给js接收
 */
package com.example.demospringmvc.controller;

import com.alibaba.fastjson.JSON;
import com.example.demospringmvc.pojo.Account;
import com.example.demospringmvc.pojo.User;
import com.example.demospringmvc.pojo.UserList;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/controller")      //类上的注解，表示其中的所有方法的路径都在该路径下
@SessionAttributes("xuser")            //session保存xuser信息
public class ControllerParam implements ApplicationContextAware {
    private ApplicationContext context;

    @Override    //这个是ApplicationContextAware 要实现的接口
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    //专为controller首页跳转
    @RequestMapping("")
    public String controllers() {
        return "controller";
    }

    //专为path页面跳转
    @RequestMapping("/path")
    public String toPath() {
        System.out.println("Direct to path-----");
        return "path";
    }


    //只有满足所有@RequestMapping中属性值要求的请求才被其拦截处理，不指定method时，默认get/post都支持
    //E1
    @RequestMapping(value = "requestmap", method = RequestMethod.POST, params = {"action=insert"}, headers = {"Host=localhost:8080"}, consumes = "application/json", produces = "application/json")
    public String testRequestMapping(Model model) {
        System.out.println("@RequestMapping");
        model.addAttribute("message", "RequestMapping");
        return "hello";
    }

    //---------DataBinding  ViewToController-----------
    //绑定默认数据类型：HttpServletRequest、HttpServletResponse、HttpSession、Model/ModelMap
    //E2
    @RequestMapping("paratype")
    public String myRequest(HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println("@Request" + "-----------" + name + "---" + age);
        request.setAttribute("paramtype", "Default Request Method Param Type");
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        return "path";
    }


    //绑定简单数据类型,返回String---ModelMap参数可以将其中的数据传递到视图中
    //E3
    @RequestMapping("uriParam1")
    public String testModel(String name, int age, Model model) {
        System.out.println("ModelMap" + "-----------" + name + "---" + age);
        model.addAttribute("paramtype", "RequestParam---ModelMap");
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return ("path");
    }

    //绑定简单数据类型,返回Void------HttpServletRequest参数可以将其中的数据传递到视图中
    //E4
    @RequestMapping("uriParam2")
    public void testRequestParam(@RequestParam(required = false) String name, @RequestParam("age") int age, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("@RequestParam1" + "-----------" + name + "---" + age);
        request.setAttribute("paramtype", "RequestParam---Void");
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        request.getRequestDispatcher("path").forward(request, response);
        //response.sendRedirect("path");   不能带数据
    }

    //E101
    @RequestMapping("json4")
    public void testJson(HttpServletResponse response) throws Exception { ;
        User user=(User)context.getBean("user");
        user.setName("zhangsan");
        user.setAge(50);
        String jsonString=JSON.toJSONString(user);
        response.getWriter().write(jsonString);
    }

    //用map绑定简单数据类型，Map中的数据即可直接返回到视图中
    //E5
    @GetMapping("uriParam3")
    public String testMap(@RequestParam Map<String, Object> map, ModelMap model) {
        System.out.println("@RequestParam2" + "-----------" + map.get("name") + "---" + map.get("age"));
        ;
        model.putAll(map);
        return "path";
    }

    //绑定POJO以便一次性完成多个数据的绑定,url中参数名称（或表单中name属性值）必须与POJO属性名称完全一致，否则收到的参数值为null
    //E6
    @RequestMapping("pojo1")
    public String testPojo1(User user, Model model) {
        System.out.println("POJO1" + "-----------" + user);
        model.addAttribute("user", user);
        return "user1";
    }

    //绑定嵌套POJO，参数名必须为对象.属性，其中对象名要与包装POJO中的对象属性名称一致
    //E7
    @GetMapping("pojo1_1")
    public String testPojo1_1(Account account) {
        System.out.println("POJO1_1" + "-----------" + account);
        return "user1";
    }

    //E11
    @PostMapping("pojo2")
    public String testPojo2(String name, int age) {
        System.out.println("POJO2" + "-----------" + name + "---" + age);
        return "path";
    }

    //E102
    @PostMapping("requestbody")
    @ResponseBody    //Responsebody表示该方法的返回结果直接写入HTTP response body中（通常是json或xml数据），而不会被解析为跳转路径
    public String testRequestBody1(@RequestBody String jsonstring) {    //@RequestBody用于读取Request请求的body中的数据
        System.out.println("@RequestBody0" + "-----------" + jsonstring);
        return jsonstring;
    }

    //E12
    @PostMapping("pojo3")
    public String testPojo3(@RequestBody User user, Model model) {
        System.out.println("POJO3" + "-----------" + user);
        model.addAttribute("user", user);
        return "user1";
    }

    //E14
    @PostMapping("pojo4")
    public String testPojo4(@RequestBody Map<String, String> map, ModelMap mapx) {
        System.out.println("@RRequetbody1" + "-----------" + map.get("name") + "---" + map.get("age"));
        mapx.putAll(map);
        return "path";
    }


    //特殊参数注解
    //动态url，常用来实现RESTful风格
    @RequestMapping("/{variable1}/{variable2}")
    //E8
    public ModelAndView showView(@PathVariable String variable1, @PathVariable("variable2") int variable2) {
        System.out.println("@PathVariable" + "-----" + variable1 + "-----" + variable2);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paramtype", "PathVariable");
        modelAndView.addObject("name", variable1);
        modelAndView.addObject("age", variable2);
        modelAndView.setViewName("path");
        return modelAndView;
    }

    @RequestMapping(value = "/setcookie", method = RequestMethod.GET)
    public void setCookie(HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("uname", "zhangsan");
        cookie.setMaxAge(10); //设置cookie的过期时间是10s
        httpServletResponse.addCookie(cookie);
        System.out.println("cookie set successful");
    }

    @RequestMapping("cookie")
    //E9
    public void testCookieValue(@CookieValue("JSESSIONID") String jid, @CookieValue String uname, @CookieValue int age, HttpServletResponse response) throws Exception {
        System.out.println("@CookieValue" + "-----------" + uname + "---" + age);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().write("<h1>Spring Controller Demo</h1>");
        response.getWriter().write("<h3>Cookie</h3>");
        response.getWriter().write("<p>JSESSIONID=" + jid + "</p>");
        response.getWriter().write("<p>username=" + uname + "</p>");
        response.getWriter().write("<p>age=" + age + "</p>");
    }

    @RequestMapping("header")
    //E10
    public void testRequestHeader(@RequestHeader(required = false, value = "customer") String headitem, @RequestHeader String Host, HttpServletResponse response) throws Exception {
        System.out.println("@RequestHeader" + "-----------" + headitem + "---" + Host);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().write("<h1>Spring Controller Demo</h1>");
        response.getWriter().write("<h3>RequestHeader</h3>");
        response.getWriter().write("<p>header=" + headitem + "</p>");
        response.getWriter().write("<p>host=" + Host + "</p>");
    }

    //数组：只能传送一维数组
    //E15
    @GetMapping("array1")
    public String testArr1(String[] names, Model model) {
        for (String name : names) {
            System.out.println("Array1" + "-----------" + name);
        }
        model.addAttribute("users", names);
        model.addAttribute("flag", "array");
        return "userlist";
    }

    //E16
    @PostMapping("array2")
    public String testArr2(@RequestParam(value = "names[]") String[] xnames, Model model) {
        for (String name : xnames) {
            System.out.println("Array1" + "-----------" + name);
        }
        model.addAttribute("users", xnames);
        model.addAttribute("flag", "array");
        return "userlist";
    }

    //E17
    @PostMapping("array3")
    public String testArr3(HttpServletRequest request) {
        String[] names = request.getParameterValues("names[]");
        for (String name : names) {
            System.out.println("Array3" + "-----------" + name);
        }
        request.setAttribute("users", names);
        request.setAttribute("flag", "array");
        return "userlist";
    }

    //E18
    @PostMapping("array4")
    @ResponseBody
    public User[] testArr4(@RequestBody User[] users) {
        System.out.println("arr4-------");
        for (User user : users) {
            System.out.println("Array4" + "-----------" + user);
        }
        return users;
    }

    //集合
    //E19
    @GetMapping("list1")
    public String testCollection1(UserList userlist, Model model) {
        List<User> users = userlist.getUsers();
        for (User user : users) {
            System.out.println("Collection1" + "-----------" + user);
        }
        model.addAttribute("users", userlist.getUsers());
        model.addAttribute("flag", "list");
        return "userlist";
    }

    //E20
    @PostMapping("list2")
    @ResponseBody
    public List<User> testCollection2(@RequestBody List<User> users) {
        for (User user : users) {
            System.out.println("Collection2" + "-----------" + user);
        }
        return users;
    }

    //E21
    @PostMapping("list3")
    @ResponseBody
    public UserList testCollection3(@RequestBody UserList userlist) {
        List<User> users = userlist.getUsers();
        for (User user : users) {
            System.out.println("Collection3" + "-----------" + user);
        }
        return userlist;
    }

    //---------DataBinding  ControllerToView-----------
    //用Model/ModelMap/Map作为入参，可以将数据传回视图，相关例子见前面的testModel和tesMap方法
    //返回值为void、String例子参见前面相关方法
    //ModelAndView
    //E23
    @RequestMapping("mv")
    public ModelAndView testModelAndView() {
        System.out.println("ModelAndView");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "return ModelAndView");  //向视图传递的数据
        modelAndView.setViewName("hello");       //跳转视图名称
        return modelAndView;
    }

    //HashMap
    //E24
    @RequestMapping("map")
    public Map<String, String> testMap(@RequestParam(required = false) String name, @RequestParam("age") int age) {
        System.out.println("@Return Map " + "-----------" + name + "---" + age);
        Map<String, String> xmap = new HashMap<>();
        xmap.put("paramtype", "return Map");
        xmap.put("name", name);
        xmap.put("age", String.valueOf(age));
        return xmap;
    }

    //E25
    @RequestMapping("/json1") //返回Object对象需要开启消息转换器HttpMessageConverter,<mvc:annotation-driven/>
    @ResponseBody
    public double testResponseBody1() {
        return 12.34;
    }

    //E26
    @RequestMapping("/json2") //返回String
    @ResponseBody
    public String testResponseBody2() {
        return "Hello SpringMVC";
    }



    //E27
    @PostMapping("json3")
    @ResponseBody
    public User testRequestBody2(@RequestBody User user) {
        System.out.println("@RequestBody2" + "-----------" + user);
        return user;
    }

    //---------ModelAttribute-----------
    //在每次调用@RequestMapping注解的方法前都要执行一次，完成对Model和View名称的赋值

    //无参数注解返回void方法，key/value在model参数中赋值，本例中key：message  value：url中传入的para值
    //E28
    @ModelAttribute
    public void testModelAttribute1(@RequestParam(required = false) String para, Model model) {
        System.out.println("testModelAttribute1"+"----"+para);
        model.addAttribute("message", para);
    }

    //带参数注解返回String值方法，key为参数值，value为方法返回值，本例中key：message     value：para
    //E29
    @ModelAttribute("message")
    public String testModelAttribute2(@RequestParam(required = false) String para) {
        System.out.println("testModelAttribute2"+"----"+para);
        return para;
    }

    //jsp直接在Model中引用上述变量值
    //E22
    @RequestMapping(value = "/hello")
    public String helloWorld() {
        System.out.println("modelAttribute传简单类型参数");
        return "hello";
    }

    //无参数注解返回对象方法，key为类名（小写）  value为方法返回的对象，本例中key：user（返回类型隐含表示）   value：mUser（方法返回值）
    //E30
    @ModelAttribute
    public User testModelAttribute3(@RequestParam(required = false) String name) {
        User mUser = (User) context.getBean("user");   //使用注入的User Bean
        mUser.setName(name);     //值来自url的name参数
        mUser.setAge(10);
        System.out.println("ModelAttribute3"+"----"+mUser);
        return mUser;
    }
    //在jsp中可以读取user的属性，
    //E30
    @RequestMapping("user1")
    public String user1() {
        return "user1";
    }

    //有参数注解返回对象方法，key为参数值  value为方法返回的对象，本例中key：xuser   value：mUser（方法返回值）
    //E31
    @ModelAttribute("xuser")
    public User testModelAttribute4() {
        User mUser = new User();
        mUser.setName("wangwu");
        mUser.setAge(30);
        System.out.println("ModelAttribute4"+"----"+mUser);
        return mUser;
    }

    //jsp中读取xuser的属性
    //E31
    @RequestMapping("user2")
    public String user2() {
        return "user2";
    }

    //@ModelAttribute注解用于方法参数,将url请求参数名称和@ModelAttribute注解的属性自动匹配和传递，
    //key：@ModelAttribute参数名  值：@ModelAttribute修饰的参数值
    //E32
    @RequestMapping("user3")
    public String testModelAttribute5(@ModelAttribute("user") User userx) {
        System.out.println("ModelAttribute5"+"----"+userx);
        return "user1";
    }

    //RequestMapping与ModelAttribute同时使用（带参数），key为参数，value为方法的返回值，视图名称为@RequestMapping中URI
    //本例中key:message,value:Hello Spring!，视图名称:hellospring.jsp
    //E33
    @RequestMapping(value = "hellospring")
    @ModelAttribute("message")
    public String testModelAttribute6() {
        System.out.println("ModelAttribute6");
        return "Hello Spring!";
    }

    //---------SessiomAttributes-----------
    //E34
    @RequestMapping(value = "/setSessionUser")
    public String getUser(ModelMap model) {
        User user = new User();
        user.setName("lisi");
        user.setAge(25);
        //向ModelMap中添加一个属性,使其成为sessionAttribute
        model.addAttribute("xuser", user);
        return "user2";
    }

    //在另一个请求中调用sessionAttribute，从model中引入
    //E35
    @RequestMapping(value = "/getSessionUser1")
    public String getUser1(ModelMap model) {
        System.out.println(model.get("xuser"));
        return "user2";
    }

    //在另一个请求中调用sessionAttribute，从@ModelAttribute中引入
    //E36
    @RequestMapping("/getSessionUser2")
    public String hello(@ModelAttribute("xuser") User user) {
        System.out.println(user);
        return "user2";
    }

    //---------File  Upload/Download-----------
    //E37
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFormUpload(@RequestParam("name") String name,
                                   @RequestParam("uploadfile") List<MultipartFile> uploadfile,
                                   HttpServletRequest request) {
        System.out.println("Uploading........");
        // 判断所上传文件是否存在
        if (!uploadfile.isEmpty() && uploadfile.size() > 0) {
            //循环输出上传的文件
            for (MultipartFile file : uploadfile) {
                // 获取上传文件的原始名称
                String originalFilename = file.getOriginalFilename();
                // 设置上传文件的保存地址目录
                String dirPath =
                        request.getServletContext().getRealPath("/upload/");
                File filePath = new File(dirPath);
                // 如果保存文件的地址不存在，就先创建目录
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                // 使用UUID重新命名上传的文件名称(上传人_uuid_原始文件名称)
                String newFilename = name + "_" + UUID.randomUUID() + "_" + originalFilename;
                try {
                    // 使用MultipartFile接口的方法完成文件上传到指定位置
                    file.transferTo(new File(dirPath + File.separator + newFilename));
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Internal Error";
                }
            }
            // 跳转到成功页面
            return "Upload Success!";
        } else {
            return "Upload Error";
        }
    }

    //E38
    @RequestMapping("/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request,
                                               String filename) throws Exception {
        // 指定要下载的文件所在路径
        String path = request.getServletContext().getRealPath("/upload/");
        // 创建该文件对象
        File file = new File(path + File.separator + filename);
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        // 通知浏览器以下载的方式打开文件
        headers.setContentDispositionFormData("attachment", filename);
        // 定义以流的形式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 使用Sring MVC框架的ResponseEntity对象封装返回下载数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.OK);
    }

    //E39
    @RequestMapping("/downloadcn")
    public ResponseEntity<byte[]> fileDownloadcn(HttpServletRequest request,
                                                 String filename) throws Exception {
        System.out.println("downloadcn.....");
        // 指定要下载的文件所在路径
        String path = request.getServletContext().getRealPath("/upload/");
        // 创建该文件对象
        File file = new File(path + File.separator + filename);
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        System.out.println(this.getFilename(request, filename));
        // 通知浏览器以下载的方式打开文件
        headers.setContentDispositionFormData("attachment",
                this.getFilename(request, filename));
        // 定义以流的形式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 使用Sring MVC框架的ResponseEntity对象封装返回下载数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.OK);
    }

    // 根据浏览器的不同进行编码设置，返回编码后的文件名
    //E39
    public String getFilename(HttpServletRequest request,
                              String filename) throws Exception {
        // IE不同版本User-Agent中出现的关键词
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        // 获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                //IE内核浏览器，统一为UTF-8编码显示
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        //火狐等其它浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }

}
