package com;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {
     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         Object userName = request.getSession().getAttribute("userName");

         if (null == userName || !(userName instanceof String)) {
             response.sendRedirect("/login");
             return false;
         }
         return true;
     }

}