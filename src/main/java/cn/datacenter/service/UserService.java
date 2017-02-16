package cn.datacenter.service;

import cn.datacenter.pojo.User;

public interface UserService {

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(int id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}
