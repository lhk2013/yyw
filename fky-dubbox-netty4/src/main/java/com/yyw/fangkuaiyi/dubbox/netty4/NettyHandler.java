
package com.yyw.fangkuaiyi.dubbox.netty4;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by linchao@111.com.cn on 2015/10/19.
 */
@io.netty.channel.ChannelHandler.Sharable
public class NettyHandler extends ChannelInboundHandlerAdapter {

    private final Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>(); // <ip:port, channel>
    private final URL url;
    private final ChannelHandler handler;
    
    public NettyHandler(URL url, ChannelHandler handler){
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }
        if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        this.url = url;
        this.handler = handler;
    }

    public Map<String, Channel> getChannels() {
        return channels;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel channel = com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel.getOrAddChannel(ctx.channel(), url, handler);
        try {
            if (channel != null) {
                channels.put(NetUtils.toAddressString((InetSocketAddress) ctx.channel().remoteAddress()), channel);
            }
            handler.connected(channel);
        } finally {
            com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel channel = com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel.getOrAddChannel(ctx.channel(), url, handler);
        try {
            channels.remove(NetUtils.toAddressString((InetSocketAddress) ctx.channel().remoteAddress()));
            handler.disconnected(channel);
        } finally {
            com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel channel = com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel.getOrAddChannel(ctx.channel(), url, handler);
        try {
            handler.received(channel, msg);
        } finally {
            com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel channel = com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel.getOrAddChannel(ctx.channel(), url, handler);
        try {
            handler.caught(channel, cause);
        } finally {
            com.yyw.fangkuaiyi.dubbox.netty4.NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }
}