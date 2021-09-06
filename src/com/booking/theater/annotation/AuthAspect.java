package com.booking.theater.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class AuthAspect {
    @Autowired
    private static AuthorizationImpl authBean;

    @Before("@annotation(com.booking.theater.annotation.Authorized) && args(request,..)")
    public static void before(HttpServletRequest request){
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("request should be HttpServletRequesttype");
        }
        if(authBean.authorize(request.getHeader("Authorization"))){
            System.out.println("auth OK!!");
        }else {
            throw new RuntimeException("auth error..!!!");
        }

    }
}
