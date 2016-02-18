package com.yyw.fangkuaiyi;

import com.yyw.fangkuaiyi.resteasy.NettyServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

/**
 * Created by lins on 16-2-18.
 */
public class FkyRestApiMain {

    public static void main(String[] args)
            throws Exception {

        ApplicationContext ac = new ClassPathXmlApplicationContext("root-context.xml");
        Assert.notNull(ac);
//        Assert.notNull(ac.getBean(HomeController.class));

        NettyServer netty = ac.getBean(NettyServer.class);

//		netty.setPort(8086);
        netty.start();

    }
}
