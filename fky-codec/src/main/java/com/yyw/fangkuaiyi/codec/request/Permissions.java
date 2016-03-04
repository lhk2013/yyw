package com.yyw.fangkuaiyi.codec.request;

import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

/**
 * Created by lins on 16-3-3.
 */
public class Permissions extends HttpRequest {

    public Permissions(){}
    public Permissions(NettyHttpRequest httpRequest) {
        super(httpRequest);
    }
}
