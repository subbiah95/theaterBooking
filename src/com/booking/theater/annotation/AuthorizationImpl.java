package com.booking.theater.annotation;

import org.springframework.stereotype.Component;

@Component
public class AuthorizationImpl {
    private static final String AdminToken = "4Z123";
    public boolean authorize(String token) {
        return AdminToken.equals(token);
    }
}
