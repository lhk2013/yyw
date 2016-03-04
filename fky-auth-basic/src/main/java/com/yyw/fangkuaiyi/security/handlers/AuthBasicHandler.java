package com.yyw.fangkuaiyi.security.handlers;

import com.yyw.fangkuaiyi.codec.request.Authc;
import com.yyw.fangkuaiyi.security.mgt.Env;
import com.yyw.fangkuaiyi.security.mgt.FkyHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

import static com.yyw.fangkuaiyi.security.mgt.DefaultHandler.login;
/**
 * Created by lins on 16-2-23.
 */
public class AuthBasicHandler extends SimpleChannelInboundHandler<Authc> implements FkyHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Authc auth) throws Exception {
        channelHandlerContext.fireChannelRead(auth.getRequest());
    }
}
