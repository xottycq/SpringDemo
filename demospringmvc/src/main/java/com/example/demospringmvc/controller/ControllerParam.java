/**
 * Attribute优先顺序：Model/ModelMap.addAttribute-->request.setAttribute-->request.getSession.setAttribute
 */
package com.example.demospringmvc.controller;

import com.example.demospringmvc.pojo.Account;
import com.example.demospringmvc.pojo.User;
import com.example.demospringmvc.pojo.UserList;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/controller")
@SessionAttributes("xuser")
public class ControllerParam  implements ApplicationContextAware {
    private ApplicationContext context;

    @Override    //这个是ApplicationContextAware 要实现的接口
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context= applicationContext;
    }

    @RequestMapping("")
    public String controllers() {
    return "controller";
    }

    //---------RequestMapping-----------
    @RequestMapping("/path")
    public String toPath() {
        System.out.println("-----");
        return "path";
    }

    //只有满足所有@RequestMapping中属性值要求的请求才被其注解的方法拦截处理
    @RequestMapping(value="requestmap",method=RequestMethod.POST,params={"action=insert"},headers={"host=localhost:8080"},consumes = "application/json",produces = "application/json")
    public String testRequestMapping(Model model) {
        System.out.println("@RequestMapping");
        model.addAttribute("message","RequestMapping");
        return "hello";
    }

    //---------DataBinding  ViewToController-----------
    //绑定默认数据类型：HttpServletRequest、HttpServletResponse、HttpSession、Model/ModelMap
    @RequestMapping("paratype")
    public String myRequest(HttpServletRequest request) throws Exception{
        String name=request.getParameter("name");
        int age= Integer.parseInt(request.getParameter("age"));
        System.out.println("@Request" + "-----------" + name + "---" +age);
        request.setAttribute("paramtype", "Default Request Method Param Type");
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        return "path";
    }


    //绑定简单数据类型,返回String---ModelMap参数可以将其中的数据传递到视图中
    @RequestMapping("requestParam1")
    public String testModel(@RequestParam(required = false) String name, @RequestParam("age") int age, Model model){
        System.out.println("ModelMap" + "-----------" + name + "---" + age);
        model.addAttribute("paramtype", "RequestParam---ModelMap");
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return ("path");
    }
    //绑定简单数据类型,返回Void------HttpServletRequest参数可以将其中的数据传递到视图中
    @RequestMapping("requestParam2")
    public void testRequestParam(@RequestParam(required = false) String name, @RequestParam("age") int age, HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("@RequestParam" + "-----------" + name + "---" + age);
        request.setAttribute("paramtype", "RequestParam---Void");
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        request.getRequestDispatcher("path").forward(request, response);
//        response.sendRedirect("path");
    }
    //绑定简单数据类型,返回Map，Map中的数据即可直接返回到视图中
    @GetMapping("map")
    public String testMap( @RequestParam(required = false) String name, @RequestParam("age") int age,Map<String,Object> map) {
        System.out.println("@RtestMap " + "-----------" +name + "---" + age);
        map.put("paramtype", "RequestParam---Map");
        map.put("name",name);
        map.put("age",String.valueOf(age));
        return "path";
    }

    //特殊参数注解
    //动态url，常用来实现RESTful风格
    @RequestMapping("/{variable1}/{variable2}")
    public ModelAndView showView(@PathVariable String variable1,@PathVariable ( "variable2" ) int variable2) {
        System.out.println("@PathVariable" + "-----" + variable1+ "-----" +variable2);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paramtype", "PathVariable");
        modelAndView.addObject("name", variable1);
        modelAndView.addObject("age", variable2);
        modelAndView.setViewName("path");
        return modelAndView;
    }

    @RequestMapping(value = "/setcookie", method = RequestMethod.GET)
    public void setCookie(HttpServletResponse httpServletResponse)  {
        Cookie cookie = new Cookie("username", "zhangsan");
        cookie.setMaxAge(10); //设置cookie的过期时间是10s
        httpServletResponse.addCookie(cookie);
        System.out.println("cookie set successful");
    }

    @RequestMapping("cookie")
    public void testCookieValue(@CookieValue("JSESSIONID") String jid, @CookieValue String username, @CookieValue int age,HttpServletResponse response) throws Exception{
        System.out.println("@CookieValue" + "-----------" + username + "---" + age);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().write("<h1>Spring Controller Demo</h1>");
        response.getWriter().write("<h3>Cookie</h3>");
        response.getWriter().write("<p>username="+username+"</p>");
        response.getWriter().write("<p>age="+age+"</p>");
    }

    @RequestMapping("header")
    public void testRequestHeader(@RequestHeader(required = false, value="customer") String headitem, @RequestHeader String Host,HttpServletResponse response) throws Exception {
        System.out.println("@RequestHeader" + "-----------" + headitem + "---" + Host );
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().write("<h1>Spring Controller Demo</h1>");
        response.getWriter().write("<h3>RequestHeader</h3>");
        response.getWriter().write("<p>header="+headitem+"</p>");
        response.getWriter().write("<p>host="+Host+"</p>");
    }
    //绑定POJO以便一次性完成多个数据的绑定,url中参数名称（或表单中name属性值）必须与POJO属性名称完全一致，否则收到的参数值为null
    @GetMapping("pojo1")
    public String testPojo1(User user) {
        System.out.println("POJO1" + "-----------" +user);
        return "user1";
    }

    //绑定嵌套POJO，参数名必须为对象.属性，其中对象名要与包装POJO中的对象属性名称一致
    @GetMapping("pojo2")
    public String testPojo2(Account account) {
        System.out.println("POJO2" + "-----------" +account);
        return "user1";
    }

    //数组：只能传送一维数组
    @GetMapping("array1")
    public String testArr1(String[] names) {
        for (String name : names){
            System.out.println("Array1" + "-----------" + name);
        }
        return "userlist";
    }
    @PostMapping("array2")
    public String testArr2(@RequestParam(value = "names[]")String[] xnames) {
        for (String name : xnames){
            System.out.println("Array1" + "-----------" + name);
        }
        return "userlist";
    }
    @PostMapping("array3")
    public String testArr3(HttpServletRequest request) {
        String[] names = request.getParameterValues("names[]");
        for (String name : names){
            System.out.println("Array3" + "-----------" + name);
        }
        return "userlist";
    }

    @PostMapping("array4")
    @ResponseBody
    public User[] testArr4(@RequestBody User[] users ) {
        for (User user : users){
            System.out.println("Array4" + "-----------" + user);
        }
        return users;
    }

    //集合
    @GetMapping("list1")
    public String testCollection1(UserList userlist) {
        List<User> users=userlist.getUsers();
        for (User user : users) {
            System.out.println("Collection1" + "-----------" + user);
        }
        return "userlist";
    }

    @PostMapping("list2")
    @ResponseBody
    public String testCollection2(@RequestBody List<User> users) {
        for (User user : users) {
            System.out.println("Collection2" + "-----------" + user);
        }
        return "userlist";
    }

    @PostMapping("list3")
    @ResponseBody
    public String testCollection3(@RequestBody UserList userlist) {
        List<User> users=userlist.getUsers();
        for (User user : users) {
            System.out.println("Collection3" + "-----------" + user);
        }
        return "userlist";
    }
    //---------DataBinding  ControllerToView-----------
    //用Model/ModelMap/Map作为入参，可以将数据传回视图，相关例子见前面的testModel和tesMap方法
    //返回值为void、String例子参见前面相关方法
    //ModelAndView
    @RequestMapping("mv")
    public ModelAndView testModelAndView(){
        System.out.println("ModelAndView");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "return ModelAndView");  //向视图传递的数据
        modelAndView.setViewName("hello");       //跳转视图名称
        return modelAndView;
    }

    //HashMap
    @RequestMapping("mp")
    public Map<String,String> testMap( @RequestParam(required = false) String name, @RequestParam("age") int age) {
        System.out.println("@Return Map " + "-----------" +name + "---" + age);
        Map<String,String> map=new HashMap<>();
        map.put("paramtype", "return Map");
        map.put("name",name);
        map.put("age",String.valueOf(age));
        return map;
    }

    //---------ModelAttribute-----------
    //在每次调用@RequestMapping注解的方法前都要执行一次，完成对Model和View名称的赋值

    //无参数注解void方法，key/value在model参数中赋值，本例中key：message  value：url中传入的para值
    @ModelAttribute
    public void testModelAttribute1(@RequestParam(required = false) String para, Model model) {
            System.out.println("testModelAttribute1");
            model.addAttribute("message", para);
       }
    //带参数注解String（返回值）方法，key为参数值，value为方法返回值，本例中key：message     value：para
    @ModelAttribute("message")
    public String testModelAttribute2(@RequestParam(required = false) String para) {
        System.out.println("testModelAttribute2");
        return para;
    }
    //jsp直接在Model中引用上述变量值
    @RequestMapping(value = "/hello")
    public String helloWorld(ModelMap model) {
        System.out.println(model.get("testAttribute1/2-----"+"message"));
        return "hello";
    }

    //无参数注解对象（返回值）方法，key为类名（小写）  value为方法返回的对象，本例中key：user（返回类型隐含表示）   value：mUser（方法返回值）
    @ModelAttribute
    public User testModelAttribute3(@RequestParam(required = false) String name) {
        System.out.println("testModelAttribute3");
        User mUser= (User) context.getBean("user");   //使用注入的User Bean
        mUser.setName(name);     //值来自url的name参数
        mUser.setAge(10);
        return mUser;
    }
    //在jsp中可以读取user的属性，
    @RequestMapping("user1")
    public String user1() {
        System.out.println("user1");
      return "user1";
    }

    //有参数注解对象（返回值）方法，key为参数值  value为方法返回的对象，本例中key：xuser   value：mUser（方法返回值）
    @ModelAttribute("xuser")
    public User testModelAttribute4() {
        System.out.println("ModelAttribute4");
        User mUser=new User();
        mUser.setName("wangwu");
        mUser.setAge(30);
        return  mUser;
    }
    //jsp中读取xuser的属性
    @RequestMapping("user2")
    public  String user2() {
        System.out.println("user2");
        return "user2";
    }

    //@ModelAttribute注解用于方法参数,将url请求参数名称和@ModelAttribute注解的属性自动匹配和传递
    //和@RequestParam相比，可以一次性绑定和传递多个属性值
    @RequestMapping("user3")
    public String testModelAttribute5(@ModelAttribute("user") User userx) {
        System.out.println("ModelAttribute5");
        return "user1";
    }

    //RequestMapping与ModelAttribute同时使用（带参数），key为参数，value为方法的返回值，视图名称为url
    //本例中key:message,value:Hello Spring!，视图名称:hellospring.jsp
    @RequestMapping(value="/hellospring")
    @ModelAttribute("message")
    public String testModelAttribute6(){
        System.out.println("ModelAttribute6");
        return "Hello Spring!";
    }

    //---------SessiomAttributes-----------
    @RequestMapping(value="/getUser1")
    public String getUser(ModelMap model){
        User user=new User();
        user.setName("qianba");
        user.setAge(25);
        //向ModelMap中添加一个属性,使其成为sessionAttribute
        model.addAttribute("xuser",user);
        return "user2";
    }
    //在另一个请求中调用sessionAttribute，从model中引入
    @RequestMapping(value="/getUser2")
    public String getUser1(ModelMap model){
        User user=(User)model.get("xuser");
        System.out.println(user.getName());
        System.out.println(user.getAge());
        return "user2";
    }
    //在另一个请求中调用sessionAttribute，从@ModelAttribute中引入
    @RequestMapping("/getUser3")
    public String hello(@ModelAttribute("xuser") User user){
        System.out.println(user.getName());
        System.out.println(user.getAge());
        return "user2";
    }

    //绑定Json数据
    @PostMapping("post1")
    @ResponseBody    //Responsebody表示该方法的返回结果直接写入HTTP response body中（通常是json或xml数据），而不会被解析为跳转路径
    public String testRequestBody1( @RequestBody String jsonstring) {    //@RequestBody用于读取Request请求的body中的数据
        System.out.println("@RequestBody1" + "-----------" +jsonstring);
        return jsonstring;
    }

    @PostMapping("post2")
    @ResponseBody
    public User testRequestBody2(@RequestBody User user) {
        System.out.println("@RequestBody2" + "-----------" +user);
        return user;
    }


    //---------File  Download/Upload-----------

}
