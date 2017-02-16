package mybatis-generator.datacenter.dao;

import mybatis-generator.datacenter.pojo.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}