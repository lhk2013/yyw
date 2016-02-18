package com.yyw.fangkuaiyi.security.shiro.realm;

import com.yyw.fangkuaiyi.account.AccountFacade;
import com.yyw.fangkuaiyi.account.pojo.Account;
import com.yyw.fangkuaiyi.security.ShiroUser;
import com.yyw.fangkuaiyi.security.utils.Encodes;
import com.yyw.fangkuaiyi.security.utils.constants.Securitys;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;

/**
 * Created by lins on 15-12-21.
 */
//@Component
public class StandardRealm extends AuthorizingRealm {

    @Autowired
    private AccountFacade accountFacade;

    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Securitys.HASH_ALGORITHM);
        matcher.setHashIterations(Securitys.HASH_INTERATIONS);

        setCredentialsMatcher(matcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Account user = accountFacade.findAccount(token.getUsername());


        if (user != null) {
            if ("disabled".equals(user.islock())) {
                throw new DisabledAccountException();
            }

            byte[] salt = Encodes.decodeHex(user.getSalt());
            return new SimpleAuthenticationInfo(new ShiroUser(user.getId(),user.getLoginName(),user.getAliasName()), user.getHashPassword(), ByteSource.Util.bytes(salt), getName());
        } else {
            throw new UnknownAccountException();
        }

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
//        User user = accountService.findUserByLoginName(shiroUser.loginName);


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        for (Role role : user.getRoles()) {
        // 基于Role的权限信息
//            info.addRole(role.roleName);
        info.addRole("system");

        // 基于Permission的权限信息
//            info.addStringPermissions(Lists.newArrayList("SHOW:UNOPENED"));
//        }
        return info;
    }

}
