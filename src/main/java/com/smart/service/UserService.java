package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;

/**
 * Created by dongzijiang on 2017/6/23.
 */

@Service //将UserService标注为 一个服务层的 Bean
public class UserService {

    @Autowired
    private  UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.getMatchCount(userName, password);
        return  matchCount > 0;
    }
    public User findUserByUserName(String userName) {
        return  userDao.findUserByUserName(userName);
    }
    public void loginSuccess(User user) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());

        loginLogDao.insertLoginLog(loginLog);
        userDao.updateLoginInfo(user);

    }
}