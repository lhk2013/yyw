package com.yyw.fangkuaiyi.account;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

/**
 * Created by lins on 16-2-18.
 */
//@ImportResource("classpath:config/dubbo.xml")
public class AccountServiceMain {

    public static void main(String[] args)
            throws Exception {

        ApplicationContext ac = new ClassPathXmlApplicationContext("root-context.xml","config/dubbo.xml");
        Assert.notNull(ac);
        com.alibaba.dubbo.container.Main.main(args);
//        Assert.notNull(ac.getBean(HomeController.class));

    }
}
