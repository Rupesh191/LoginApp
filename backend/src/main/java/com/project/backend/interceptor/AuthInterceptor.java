package com.project.backend.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.project.backend.AuthUtils;
import com.project.backend.UserService;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	
@Autowired
AuthUtils auth;

@Autowired
UserService service;

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws IOException {
    System.out.println("prehandle done");
    System.out.println("........" + request.getRequestURI());
    boolean flag = true;
    try {
        if (request.getRequestURI().equals("/user/login") || request.getRequestURI().equals("/user/register")|| request.getRequestURI().equals("/user")) {
            return true;
        }
        String str = request.getHeader("Authorization");
        String token = str.substring(7);
        System.out.println("Token......." + token);

       System.out.println("Response............." + auth.validateJwtToken(token));

         flag=auth.validateJwtToken(token);



       if (flag == false) {
            response.sendError(401);
            return false;
        }
        return true;



   } catch (Exception e) {
        response.sendError(401);
        e.printStackTrace();
        System.out.println("Unauthorised Access");
        return false;
    }



   // return auth.validateJwtToken(token);



}
}

 

