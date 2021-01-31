/**对Dao的功能（增删改查）进行演示，Dao功能通常应该由Service来调用，这里为演示简化成Controller直接调用
 * 通过@Qualifier分别注入IUserDao的两个具体实现类：userdao_jdbctemplate或userdao_mybatis
 */
package com.example.demospringmvc.controller;

import com.example.demospringmvc.dao.IUserDao;
import com.example.demospringmvc.dao.UserDaoMapper;
import com.example.demospringmvc.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DaoController {
	@Autowired
	@Qualifier("userdao_jdbctemplate")
//	@Qualifier("userdao_mybatis")
	private IUserDao userDao;

/*直接使用UserDaoMapper时须在其上增加类注解：@Repository(value="userdao_mybatis")，然后用下列配置替换上述配置：
	@Autowired
	@Qualifier("userdao_mybatis")
	private UserDaoMapper userDao;

	@Resource
	private SqlSessionTemplate sqlsessionTemplate;

	@PostConstruct
	public void init(){
		System.out.println("DaoController init");
		userDao=sqlsessionTemplate.getMapper(UserDaoMapper.class);
	}*/
	@RequestMapping("jdbctemplate")
	public String demo1(Model model) {
		System.out.println("jdbcTemplate demo");
		model.addAttribute("daotitle","Dao Demo--jdbcTemplate");
		return "daomenu";
	}

	@RequestMapping("mybatis")
	public String demo2(Model model) {
		System.out.println("mybatis demo");
		model.addAttribute("daotitle","Dao Demo--mybatis");
		return "daomenu";
	}

	@RequestMapping("adduser")
	public String adduser(User user, Model model) {
		System.out.println("adduser："+user);
		model.addAttribute("operation","新增用户");
		if(userDao.addUser(user)>0)
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("deleteuser")
	public String deleteuser(String name,Model model) {
		System.out.println("deleteuser："+name);
		model.addAttribute("operation","删除用户");
		if(userDao.deleteUser(name)>0)
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("updateuser")
	public String updateuser(User user,Model model) {
		System.out.println("updateuser："+user);
		model.addAttribute("operation","修改用户");
		if(userDao.updateUser(user)>0)
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("queryuser")
	public String queryuser(String name,Model model) {
		System.out.println("queryuser："+name);
		model.addAttribute("operation","查询用户");
		User user=userDao.queryUser(name);
		if(user!=null) {
			model.addAttribute("result", "成功！");
			List<User> list = new ArrayList<>();
			list.add(user);
			model.addAttribute("users", list);
		}
		else
			model.addAttribute("result","失败！");

		return "result";
	}

	@RequestMapping("queryalluser")
	public String queryalluser(Model model) {
		System.out.println("queryAlluser");
		model.addAttribute("operation","查询用户");
		List<User> list =userDao.queryAllUser();
		if(list.size()>0) {
			model.addAttribute("result", "成功！");
			model.addAttribute("users", list);
		}
		else {
			model.addAttribute("result", "失败！");
		}
		return "result";
	}

}
