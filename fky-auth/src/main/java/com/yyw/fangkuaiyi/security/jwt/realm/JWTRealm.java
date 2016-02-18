package com.yyw.fangkuaiyi.security.jwt.realm;


import com.yyw.fangkuaiyi.account.AccountFacade;
import com.yyw.fangkuaiyi.account.pojo.Account;
import com.yyw.fangkuaiyi.role.pojo.Role;
import com.yyw.fangkuaiyi.security.ShiroUser;
import com.yyw.fangkuaiyi.security.Tokens;
import com.yyw.fangkuaiyi.security.jwt.JWTAuthenticationToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    private Tokens jwtTokens;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JWTAuthenticationToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        JWTAuthenticationToken upToken = (JWTAuthenticationToken) token;
        Account user = accountFacade.findAccount((Long) upToken.getUserId());

        if (user != null && jwtTokens.validateToken(upToken.getToken())) {
            SimpleAccount account = new SimpleAccount(new ShiroUser(user.getId(),user.getLoginName(),user.getAliasName()), upToken.getToken(), getName());
            for (Role role : user.getRoles()) {
                // 基于Role的权限信息
                account.addRole(role.getRoleName());

                // 基于Permission的权限信息
//            info.addStringPermissions(Lists.newArrayList("SHOW:UNOPENED"));
            }
            return account;
        }

        return null;
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
