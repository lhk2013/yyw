package com.yyw.fangkuaiyi.security.handlers;

import com.yyw.fangkuaiyi.security.mgt.DefaultHandler;
import com.yyw.fangkuaiyi.security.mgt.Env;
import com.yyw.fangkuaiyi.security.mgt.FkyHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

import static com.yyw.fangkuaiyi.security.mgt.DefaultHandler.jwt;
/**
 * Created by lins on 16-2-23.
 */
public class ShiroHandler extends SimpleChannelInboundHandler<NettyHttpRequest> implements FkyHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NettyHttpRequest netty) throws Exception {
        String requestUri = netty.getUri().getMatchingPath();
        Env env= testEnv.get(requestUri);

        if (env!=null && DefaultHandler.valueOf(env.getBasics())!=null) {
            channelHandlerContext.fireChannelRead(this.newInstance(env.getBasics(), netty));
            return;
        }else {
            channelHandlerContext.fireChannelRead(this.newInstance(jwt.name(), netty));
            return;
        }
    }
}
