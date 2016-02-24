package com.yyw.fangkuaiyi.api;

import com.yyw.fangkuaiyi.account.pojo.Account;
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
@Path("/index")
public class IndexEndpoint {

    @GET
    @Path("/test")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @RequiresRoles("admin")
    public String tet() {
        return "------index.tet()-------";
    }
}
