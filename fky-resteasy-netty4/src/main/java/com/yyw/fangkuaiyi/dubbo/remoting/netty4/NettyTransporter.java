package com.yyw.fangkuaiyi.dubbo.remoting.netty4;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.*;

/**
 * Created by linchao@111.com.cn on 2015/10/19.
 */
public class NettyTransporter implements Transporter {

    public static final String NAME = "netty";
    
    public Server bind(URL url, ChannelHandler listener) throws RemotingException {
        return new com.yyw.fangkuaiyi.dubbo.remoting.netty4.NettyServer(url, listener);
    }

    public Client connect(URL url, ChannelHandler listener) throws RemotingException {
        return new NettyClient(url, listener);
    }

}