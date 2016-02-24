package com.yyw.fangkuaiyi.security.handlers;

import com.yyw.fangkuaiyi.codec.request.StatelessHttpRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by lins on 16-2-23.
 */
public class StatelessHandler extends SimpleChannelInboundHandler<StatelessHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, StatelessHttpRequest stateless) throws Exception {
        channelHandlerContext.fireChannelRead(stateless.getRequest());
    }
}
