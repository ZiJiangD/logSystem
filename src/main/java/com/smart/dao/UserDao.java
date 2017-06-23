package com.smart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

/**
 * Created by dongzijiang on 2017/6/23.
 */

@Repository //通过Spring注解 定义一个DAO
public class UserDao {

    @Autowired//自动注入 JdbcTemplate的Bean
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName, String password){
        String sqlstr = " SELECT count(*) FROM t_user " + " WHERE user_name =? and password=? ";

        /*
        jdbcTemplate.queryForInt()方法过时的处理办法

        代替的方法为queryForObject(String sql, Object[] args, Class<T> requiredType)。
        需要返回的是什么类型，就在第三个参数写什么类型。比如int类型就写Integer.class.
        */
        return jdbcTemplate.queryForObject(sqlstr, new Object[]{userName,password},Integer.class);
    }


    public User findUserByUserName(final String userName){
        String sqlStr = " SELECT user_id,user_name "
                + " FROM t_user WHERE user_name =? ";
        final  User user = new User();

        jdbcTemplate.query(sqlStr, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(userName);
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        String sqlStr = " UPDATE t_user SET last_visit=?,last_ip=?"
                + " WHERE user_id =?";

        jdbcTemplate.update(sqlStr, new Object[] {user.getLastVisit(),user.getLastIp(),user.getUserId()});
    }

}
