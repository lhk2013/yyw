package com.yyw.fangkuaiyi.security.handlers;

import com.yyw.fangkuaiyi.codec.request.AuthHttpRequest;
import com.yyw.fangkuaiyi.codec.request.JwtHttpRequest;
import com.yyw.fangkuaiyi.codec.request.StatelessHttpRequest;
import com.yyw.fangkuaiyi.security.utils.PathMatches;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

/**
 * Created by lins on 16-2-23.
 */
public class ShiroHandler extends SimpleChannelInboundHandler<NettyHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NettyHttpRequest netty) throws Exception {
        String requestUri = netty.getUri().getMatchingPath();

        if (PathMatches.compare("/index/**",requestUri)){
            StatelessHttpRequest httpRequest = new StatelessHttpRequest(netty);
            channelHandlerContext.fireChannelRead(httpRequest);
            return;
        }else if (PathMatches.compare("/auth/login",requestUri)){
            AuthHttpRequest httpRequest = new AuthHttpRequest(netty);
            channelHandlerContext.fireChannelRead(httpRequest);
            return;
        }else {
            JwtHttpRequest httpRequest = new JwtHttpRequest(netty);
            channelHandlerContext.fireChannelRead(httpRequest);
            return;
        }
    }
}
