package com.yyw.fangkuaiyi.security.handlers;

import com.yyw.fangkuaiyi.codec.request.Anon;
import com.yyw.fangkuaiyi.security.mgt.Env;
import com.yyw.fangkuaiyi.security.mgt.FkyHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by lins on 16-2-23.
 */
public class AnonHandler extends SimpleChannelInboundHandler<Anon> implements FkyHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Anon anon) throws Exception {
        channelHandlerContext.fireChannelRead(anon.getRequest());
    }
}
