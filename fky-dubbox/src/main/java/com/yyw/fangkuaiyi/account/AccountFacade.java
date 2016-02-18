package com.yyw.fangkuaiyi.account;

import com.yyw.fangkuaiyi.account.pojo.Account;

/**
 * Created by lins on 16-2-18.
 */
public interface AccountFacade {

    Account findAccount(String loginName);
    Account findAccount(Long id);
}
