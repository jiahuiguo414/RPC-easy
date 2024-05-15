package com.gjh;

import com.gjh.register.LocalRegister;
import com.gjh.server.HttpServer;
import com.gjh.server.VertxHttpServer;
import com.gjh.service.UserService;

public class ProviderApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //1.注册
        LocalRegister.register(UserService.class.getName(), UserService.class);
        //2.启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}