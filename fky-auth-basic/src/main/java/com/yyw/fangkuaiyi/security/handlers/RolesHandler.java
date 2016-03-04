package com.yyw.fangkuaiyi.security.handlers;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.yyw.fangkuaiyi.codec.request.Roles;
import com.yyw.fangkuaiyi.codec.response.StandardResponse;
import com.yyw.fangkuaiyi.codec.response.StandardResult;
import com.yyw.fangkuaiyi.security.mgt.Env;
import com.yyw.fangkuaiyi.security.mgt.FkyHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

import java.util.Set;

import static com.yyw.fangkuaiyi.security.mgt.DefaultHandler.perms;

/**
 * Created by lins on 16-2-23.
 */
public class RolesHandler extends SimpleChannelInboundHandler<Roles> implements FkyHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Roles roles) throws Exception {
        NettyHttpRequest request = (NettyHttpRequest) roles.getRequest();
        String requestUri = request.getUri().getMatchingPath();
        Env env = testEnv.get(requestUri);

        Subject subject = SecurityUtils.getSubject();
        String[] rolesArray = StringUtils.split(env.getRoles());
        if(rolesArray != null && rolesArray.length != 0) {
            Set roleSet = CollectionUtils.asSet(rolesArray);
            if(!subject.hasAllRoles(roleSet)){
                StandardResult result = new StandardResult(401, "Role authorization failure!");
                StandardResponse.write(channelHandlerContext,result);
                return;
            }
        }
        channelHandlerContext.fireChannelRead(this.newInstance(perms.name(),request));
        return;
    }
}
