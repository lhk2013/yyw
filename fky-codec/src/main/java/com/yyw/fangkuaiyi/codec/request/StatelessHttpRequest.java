package com.yyw.fangkuaiyi.codec.request;

import org.jboss.resteasy.spi.HttpRequest;

/**
 * Created by lins on 16-2-23.
 */
public class StatelessHttpRequest {

    private HttpRequest request;

    public StatelessHttpRequest(HttpRequest fullHttpRequest){
        this.request = fullHttpRequest;
    }

    public HttpRequest getRequest() {
        return request;
    }

}
