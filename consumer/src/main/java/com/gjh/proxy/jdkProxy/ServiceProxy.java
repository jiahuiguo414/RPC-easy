package com.gjh.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.gjh.model.RpcRequest;
import com.gjh.model.RpcResponse;
import com.gjh.serializer.JdkSerializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理
 */
public class ServiceProxy implements InvocationHandler {

    /**
     *  代理调用
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1. 创建序列化器
        JdkSerializer serializer = new JdkSerializer();
        //2. 构造情趣
        RpcRequest request =
                RpcRequest.builder()
                        .serviceName(method.getName())
                        .paramTypes(method.getParameterTypes())
                        .args(args)
                        .build();
        try{
            //序列化
            byte[] bodyBytes = serializer.serialize(request);
            HttpResponse httpResponse = HttpRequest
                    .post("http://8080")
                    .body(bodyBytes)
                    .execute();
            byte[] result = httpResponse.bodyBytes();
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return rpcResponse.getData();

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
