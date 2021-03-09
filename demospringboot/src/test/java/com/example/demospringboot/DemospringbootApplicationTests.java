/**引入spring-boot-starter-test后，缺省引入下列测试框架：
 *--JUnit5
 *-- Spring Test & Spring Boot Test
 *--AssertJ
 *--Hamcrest
 *--Mockito
 *--JSONassert
 *--JsonPath
 */
package com.example.demospringboot;

import com.alibaba.fastjson.JSONObject;
import com.example.demospringboot.domain.User;
import com.example.demospringboot.service.AccountService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
@SpringBootTest
class DemospringbootApplicationTests {

    @Autowired
    AccountService accountService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeAll
    static void initAll() {
        System.out.println("test init all");
    }

    @BeforeEach
    void init() {
        System.out.println("test init each");
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @AfterEach
    void tearDown() {
        System.out.println("test after each");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("test after all");
    }

    //Service测试
    @Test
    void openAccountTest() {
        User user=new User();
        user.setName("zhangsan1");
        user.setAge(21);
        boolean result=accountService.openAccount(user);
        if(result) {
            System.out.println(user.getId());
        }
        Assertions.assertTrue(result);
    }

    @Test
    @Transactional //数据库操作会回滚，数据库值没有任何变化
    void closeAccountTest() {
        User user=new User();
        user.setName("zhangsan");
        user.setAge(20);
        boolean result=accountService.closeAccount(user);
        assertThat(result,is(true));
    }


    //Controller测试
    @Test
    public void openAccount() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/openaccount/?name=lisi&age=20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void transferAccount() throws Exception{
        User user1=new User("zhangsan",21);
        User user2=new User("lisi",21);
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("outuser",user1);
        jsonobject.put("inuser",user2);
        jsonobject.put("money",10);
        mvc.perform(MockMvcRequestBuilders.post("/transfer1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonobject.toJSONString())
            )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("result",is("成功！")))
                .andDo(MockMvcResultHandlers.print());
    }
}
