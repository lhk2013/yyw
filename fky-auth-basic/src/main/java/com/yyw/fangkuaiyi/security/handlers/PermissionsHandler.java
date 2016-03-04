package com.yyw.fangkuaiyi.security.handlers;

import com.yyw.fangkuaiyi.codec.request.Permissions;
import com.yyw.fangkuaiyi.codec.request.Roles;
import com.yyw.fangkuaiyi.codec.response.StandardResponse;
import com.yyw.fangkuaiyi.codec.response.StandardResult;
import com.yyw.fangkuaiyi.security.mgt.Env;
import com.yyw.fangkuaiyi.security.mgt.FkyHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

import static com.yyw.fangkuaiyi.security.mgt.DefaultHandler.perms;

/**
 * Created by lins on 16-2-23.
 */
public class PermissionsHandler extends SimpleChannelInboundHandler<Permissions> implements FkyHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Permissions perms) throws Exception {
        NettyHttpRequest request = (NettyHttpRequest) perms.getRequest();
        String requestUri = request.getUri().getMatchingPath();
        Env env = testEnv.get(requestUri);

        boolean isPermitted = true;
        Subject subject = SecurityUtils.getSubject();
        String[] permsArray = StringUtils.split(env.getPerms());
        if(permsArray != null && permsArray.length > 0) {
            if(permsArray.length == 1) {
                if(!subject.isPermitted(permsArray[0])) {
                    isPermitted = false;
                }
            } else if(!subject.isPermittedAll(permsArray)) {
                isPermitted = false;
            }
        }

        if (!isPermitted){
            StandardResult result = new StandardResult(401, "Perms authorization failure!");
            StandardResponse.write(channelHandlerContext,result);
            return;
        }

        channelHandlerContext.fireChannelRead(request);
        return;
    }
}
