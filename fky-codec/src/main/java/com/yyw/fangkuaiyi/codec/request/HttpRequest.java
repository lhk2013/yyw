package com.yyw.fangkuaiyi.codec.request;


/**
 * Created by lins on 16-3-3.
 */
public abstract class HttpRequest implements Cloneable{

    public void setRequest(org.jboss.resteasy.spi.HttpRequest request) {
        this.request = request;
    }

    private org.jboss.resteasy.spi.HttpRequest request;
    public org.jboss.resteasy.spi.HttpRequest getRequest(){
        return request;
    }
    public HttpRequest(){}

    HttpRequest(org.jboss.resteasy.spi.HttpRequest httpRequest){
        request = httpRequest;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
