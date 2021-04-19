package com.bjsxt.service;

import com.bjsxt.pojo.Users;

import java.util.List;

public interface UserManagerService {
    void addUser(Users users);
    List<Users> findUser(Users users);
    Users findUserByUserId(int userid);
    void modifyUser(Users users);
    void dropUser(int userid);
}
