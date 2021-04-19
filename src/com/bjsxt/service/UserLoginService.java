package com.bjsxt.service;

import com.bjsxt.pojo.Users;

public interface UserLoginService {
    Users userLogin(String username,String userpwd);
}
