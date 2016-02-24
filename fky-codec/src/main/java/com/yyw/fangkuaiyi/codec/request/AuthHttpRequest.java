package com.yyw.fangkuaiyi.codec.request;

import org.jboss.resteasy.spi.HttpRequest;

/**
 * Created by lins on 16-2-23.
 */
public class AuthHttpRequest {

    private HttpRequest request;

    public AuthHttpRequest(HttpRequest fullHttpRequest){
        this.request = fullHttpRequest;
    }

    public HttpRequest getRequest() {
        return request;
    }

}
