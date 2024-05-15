package com.gjh;

import com.gjh.model.User;
import com.gjh.proxy.UserServiceProxy;
import com.gjh.proxy.jdkProxy.ServiceProxyFactory;
import com.gjh.service.UserService;

public class ConsumerApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //获取UserService服务 ——静态代理
        UserService userService = new UserServiceProxy();
        //动态代理
        UserService userServiceProxy = ServiceProxyFactory.getProxy(UserService.class);
        User user = userService.getUser(1L);
        if(user == null) System.out.println("调用失败");
        else System.out.println(user);

    }
}