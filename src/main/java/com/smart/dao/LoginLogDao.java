package com.smart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smart.domain.LoginLog;
/**
 * Created by dongzijiang on 2017/6/23.
 */

@Repository
public class LoginLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog loginLog) {
        String sqlStr = "INSERT INTO t_login_log(user_id,ip,login_datetime) "
                + "VALUES(?,?,?) ";
        Object[] args = {loginLog.getUserId(), loginLog.getIp(),
                loginLog.getLoginDate()};
        System.out.print(args);

        jdbcTemplate.update(sqlStr, args);
    }
}
