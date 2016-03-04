package com.yyw.fangkuaiyi.codec.request;

import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

/**
 * Created by lins on 16-2-23.
 */
public class Authc extends HttpRequest {

    public Authc(){}
    public Authc(NettyHttpRequest httpRequest) {
        super(httpRequest);
    }
}
