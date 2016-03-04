package com.yyw.fangkuaiyi.security.handlers;

import com.yyw.fangkuaiyi.codec.request.Authc;
import com.yyw.fangkuaiyi.codec.request.Jwt;
import com.yyw.fangkuaiyi.codec.request.Stateless;
import com.yyw.fangkuaiyi.security.mgt.FkyHandler;
import com.yyw.fangkuaiyi.security.utils.PathMatches;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

/**
 * Created by lins on 16-2-23.
 */
public class StatelessHandler extends SimpleChannelInboundHandler<Stateless> implements FkyHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Stateless stateless) throws Exception {
        String requestUri = stateless.getRequest().getUri().getMatchingPath();


        if (PathMatches.compare("/auth/login",requestUri)){
            Authc httpRequest = new Authc((NettyHttpRequest) stateless.getRequest());
            channelHandlerContext.fireChannelRead(httpRequest);
            return;
        }else {
            Jwt httpRequest = new Jwt((NettyHttpRequest) stateless.getRequest());
            channelHandlerContext.fireChannelRead(httpRequest);
            return;
        }
    }
}
