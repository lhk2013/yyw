package com.yyw.fangkuaiyi.api;

import com.yyw.fangkuaiyi.account.pojo.Account;
import com.yyw.fangkuaiyi.codec.response.StandardResult;
import com.yyw.fangkuaiyi.security.ShiroUser;
import com.yyw.fangkuaiyi.security.jwt.JWTTokenService;
import com.yyw.fangkuaiyi.security.jwt.TokenResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by lins on 16-2-18.
 */
@Controller
@Path("/auth")
public class AccountEndpoint {

    @Autowired
    private JWTTokenService tokens;

    @POST
    @Path("/login")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public StandardResult login(Account user) {
        //当前Subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken uptoken = new UsernamePasswordToken(
                user.getLoginName(),
                user.getHashPassword());
//		uptoken.setRememberMe(true);
        try {
            subject.login(uptoken);

        } catch (AuthenticationException e) {//登录失败
            e.printStackTrace();
        } catch (Exception e) {//登录失败
            e.printStackTrace();
        }
        Subject subject2 = SecurityUtils.getSubject();
        subject2.hasRole("admin");
        ShiroUser cuuser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        TokenResponse token = tokens.createToken(cuuser);
        return new StandardResult(token);
    }

    @POST
    @Path("/tet")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @RequiresRoles("admin")
    public StandardResult tet() {
        Subject subject = SecurityUtils.getSubject();
        subject.hasRole("admin");
        return new StandardResult("------auth.tet()-------");
    }
}
