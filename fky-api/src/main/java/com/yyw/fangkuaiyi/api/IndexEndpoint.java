package com.yyw.fangkuaiyi.api;

import com.yyw.fangkuaiyi.codec.response.StandardResult;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public StandardResult tet() {
        return new StandardResult("------index.tet()-------");
    }
}
