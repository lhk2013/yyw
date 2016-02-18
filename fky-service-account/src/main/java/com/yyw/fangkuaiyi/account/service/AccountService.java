package com.yyw.fangkuaiyi.account.service;

import com.yyw.fangkuaiyi.account.AccountFacade;
import com.yyw.fangkuaiyi.account.pojo.Account;
import com.yyw.fangkuaiyi.account.repository.RoleDao;
import com.yyw.fangkuaiyi.account.repository.UserDao;
import com.yyw.fangkuaiyi.util.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lins on 16-1-11.
 */
@Service
public class AccountService implements AccountFacade {
    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;


    public Account findAccount(String loginName) {
        return BeanMapper.map(userDao.findByLoginName(loginName),Account.class);
    }

    public Account findAccount(Long id) {
        return BeanMapper.map(userDao.findById(id),Account.class);
    }
}
