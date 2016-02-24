package com.yyw.fangkuaiyi.codec.response;

import com.yyw.fangkuaiyi.util.JsonMapper;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import javax.ws.rs.core.MediaType;
import java.nio.charset.Charset;

/**
 * Created by lins on 16-2-24.
 */
public class StandardResponse {
    private final static String CONTENT_LENGTH = "Content-Length";
    private final static String CONTENT_TYPE = "Content-Type";
    private final static JsonMapper JSON = JsonMapper.nonDefaultMapper();

    public static void write(ChannelHandlerContext ctx, Object result){
        String json = JSON.toJson(result);

        HttpResponse httpResponse = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(json,
                        Charset.defaultCharset()));
//        httpResponse.headers().set(CONTENT_LENGTH, json.length());
        httpResponse.headers().set(CONTENT_TYPE, MediaType.APPLICATION_JSON);
        ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
    }
}
