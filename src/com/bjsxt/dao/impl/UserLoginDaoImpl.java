package com.bjsxt.dao.impl;

import com.bjsxt.commons.JdbcUtils;
import com.bjsxt.dao.UserLoginDao;
import com.bjsxt.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLoginDaoImpl implements UserLoginDao {
    /**
     * 用户登录的数据库查询
     * @param username
     * @param userpwd
     * @return
     */
    @Override
    public Users selectUsersByUserNameAndUserPwd(String username, String userpwd) {
        Users user = null;
        Connection conn = null;
        try{
            conn = JdbcUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from users where username = ? and userpwd = ?");
            ps.setString(1,username);
            ps.setString(2,userpwd);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                user = new Users();
                user.setUsersex(resultSet.getString("usersex"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setUsername(resultSet.getString("username"));
                user.setUserid(resultSet.getInt("userid"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setQqnumber(resultSet.getString("qqnumber"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConnection(conn);
        }
        return user;
    }
}
