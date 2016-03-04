package com.yyw.fangkuaiyi.codec.request;

import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

/**
 * Created by lins on 16-2-23.
 */
public class Jwt extends HttpRequest {

    public Jwt(){}
    public Jwt(NettyHttpRequest httpRequest) {
        super(httpRequest);
    }
}
