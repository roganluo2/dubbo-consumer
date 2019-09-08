package com.my.dubbo;

import com.my.dubbo.service.LoginService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboClient {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application.xml"});
        LoginService loginService = context.getBean("loginService", LoginService.class);
        String admin = loginService.login("admin", "123456");
        System.out.println( "调用成功：" + admin);

    }

}
