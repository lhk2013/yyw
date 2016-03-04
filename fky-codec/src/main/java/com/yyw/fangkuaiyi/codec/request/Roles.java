package com.yyw.fangkuaiyi.codec.request;

import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

/**
 * Created by lins on 16-3-3.
 */
public class Roles extends HttpRequest {

    public Roles(){}
    public Roles(NettyHttpRequest httpRequest) {
        super(httpRequest);
    }
}
