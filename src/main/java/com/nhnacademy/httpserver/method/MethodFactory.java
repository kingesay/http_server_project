package com.nhnacademy.httpserver.method;

import com.nhnacademy.httpserver.parser.header.Header;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class MethodFactory {
    @Autowired
    private BeanFactory factory;

    public Method getMethod(Header header, @Nullable String clientBody) {
        String methodType = header.getMethod();
        Method method = factory.getBean(methodType.toLowerCase() + "Method", Method.class);
        if(method instanceof PostMethod){
            method.createBody(header, clientBody);
        }
        if(method instanceof GetMethod){
            method.createBody(header);
        }
        return method;
    }
}
