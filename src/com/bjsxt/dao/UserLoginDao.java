package com.bjsxt.dao;

import com.bjsxt.pojo.Users;

public interface UserLoginDao {
    public Users selectUsersByUserNameAndUserPwd(String username,String userpwd);
}
