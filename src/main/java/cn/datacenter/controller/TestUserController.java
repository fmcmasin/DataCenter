package cn.datacenter.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.datacenter.pojo.TestUserBean;
import cn.datacenter.pojo.User;
import cn.datacenter.service.UserService;

@Controller
@RequestMapping("/user")
public class TestUserController {
	private static Logger log = LoggerFactory.getLogger(TestUserController.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// @Autowired注解来自sping框架
	// @Resource注解来自J2EE
	// 在不设置装配要求时,这两个注解没什么区别
	@Resource
	private UserService userService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		System.out.println("userId:" + userId);
		TestUserBean user = null;
		if (userId == 1) {
			user = new TestUserBean();
			user.setId(1);
			user.setName("测试姓名");
		}

		log.debug(user.toString());
		model.addAttribute("user", user);
		return "index";
	}

	@RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.GET)
	public String deleteByPrimaryKey(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		userService.deleteByPrimaryKey(id);
		return "index";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	@ResponseBody
	public String insert(HttpServletRequest request, Model model) {

		User user = new User();
		user.setName(request.getParameter("name"));
		try {
			user.setBirthday(dateFormat.parse(request.getParameter("birthday")));
		} catch (ParseException e) {
			log.debug("出生日期转换异常!");
			Date now = new Date();
			user.setBirthday(now);
		}
		int temp = userService.insert(user);
		return "成功添加:" + temp + "ksdbfgk";
	}

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	@ResponseBody
	public User selectByPrimaryKey(HttpServletRequest request, Model model) {
		return userService.selectByPrimaryKey(1);
	}

}
