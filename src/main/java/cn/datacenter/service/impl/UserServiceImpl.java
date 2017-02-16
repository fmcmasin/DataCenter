package cn.datacenter.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.datacenter.dao.UserMapper;
import cn.datacenter.pojo.User;
import cn.datacenter.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userDao;

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(User record) {
		// TODO Auto-generated method stub
		return userDao.insert(record);
	}

	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public User selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
