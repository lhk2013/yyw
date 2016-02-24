package com.yyw.fangkuaiyi.security.handlers;

import com.yyw.fangkuaiyi.codec.request.AuthHttpRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by lins on 16-2-23.
 */
public class AuthBasicHandler extends SimpleChannelInboundHandler<AuthHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AuthHttpRequest auth) throws Exception {
        channelHandlerContext.fireChannelRead(auth.getRequest());
    }
}
