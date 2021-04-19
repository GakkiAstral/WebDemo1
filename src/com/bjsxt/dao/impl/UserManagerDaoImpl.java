package com.bjsxt.dao.impl;

import com.bjsxt.commons.JdbcUtils;
import com.bjsxt.dao.UserManagerDao;
import com.bjsxt.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理持久层
 */
public class UserManagerDaoImpl implements UserManagerDao {
    @Override
    public void insertUser(Users users) {
        Connection conn = null;
        try {
            //需要考虑到事务提交概念
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into users values(default,?,?,?,?,?)");
            ps.setString(1, users.getUsername());
            ps.setString(2, users.getUserpwd());
            ps.setString(3, users.getUsersex());
            ps.setString(4, users.getPhonenumber());
            ps.setString(5, users.getQqnumber());
            ps.execute();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollbackConnection(conn);
        } finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    /**
     * 查询用户
     *
     * @param users
     * @return
     */
    @Override
    public List<Users> selectUserByProperty(Users users) {
        Connection conn = null;
        List<Users> list = new ArrayList<>();
        try {
            conn = JdbcUtils.getConnection();
            String sql = this.createSQL(users);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Users user = new Users();
                user.setUserid(resultSet.getInt("userid"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setQqnumber(resultSet.getString("qqnumber"));
                user.setUsersex(resultSet.getString("usersex"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setUsername(resultSet.getString("username"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
        return list;
    }

    /**
     * 根据用户id查询用户
     * @param userid
     * @return
     */
    @Override
    public Users selectUserBuUserid(int userid) {
        Connection conn = null;
        Users user = null;
        try{
            conn = JdbcUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from users where userid = ?");
            preparedStatement.setInt(1,userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new Users();
                user.setUserid(resultSet.getInt("userid"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setQqnumber(resultSet.getString("qqnumber"));
                user.setUsersex(resultSet.getString("usersex"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setUsername(resultSet.getString("username"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.closeConnection(conn);
        }
        return user;
    }

    /**
     * 拼接查询的sql语句
     * @param users
     * @return
     */
    private String createSQL(Users users) {
        StringBuffer stringBuffer = new StringBuffer("select * from users where 1=1");
        if (users.getUsersex() != null && users.getUsersex().length() > 0) {
            stringBuffer.append(" and usersex = "+users.getUsersex());
        }
        if (users.getQqnumber() != null && users.getQqnumber().length() > 0) {
            stringBuffer.append(" and qqnumber = "+users.getQqnumber());
        }
        if (users.getUsername() != null && users.getUsername().length() > 0) {
            stringBuffer.append(" and username = "+users.getUsername());
        }
        if (users.getPhonenumber() != null && users.getPhonenumber().length() > 0) {
            stringBuffer.append(" and phonenumber = "+users.getPhonenumber());
        }
        return stringBuffer.toString();
    }

    /**
     * 更新用户信息
     * @param users
     */
    @Override
    public void updateUserByUserid(Users users) {
        Connection conn = null;
        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("Update users set username = ?,usersex = ?,phonenumber = ?,qqnumber = ? where userid = ?");
            //参数绑定
            preparedStatement.setString(1,users.getUsername());
            preparedStatement.setString(2,users.getUsersex());
            preparedStatement.setString(3,users.getPhonenumber());
            preparedStatement.setString(4,users.getQqnumber());
            preparedStatement.setInt(5,users.getUserid());
            preparedStatement.execute();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            JdbcUtils.rollbackConnection(conn);
        }finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    /**
     * 根据用户id删除用户
     * @param userid
     */
    @Override
    public void deleteUserByUserid(int userid) {
        Connection conn = null;
        try{
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("delete from users where userid = ?");
            preparedStatement.setInt(1,userid);
            preparedStatement.execute();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            JdbcUtils.rollbackConnection(conn);
        }finally {
            JdbcUtils.closeConnection(conn);
        }
    }
}
