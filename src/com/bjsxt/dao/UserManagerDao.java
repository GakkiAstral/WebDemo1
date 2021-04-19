package com.bjsxt.dao;

import com.bjsxt.pojo.Users;

import java.util.List;

public interface UserManagerDao {
    void insertUser(Users users);
    List<Users> selectUserByProperty(Users users);
    Users selectUserBuUserid(int userid);
    void updateUserByUserid(Users users);
    void deleteUserByUserid(int userid);
}
