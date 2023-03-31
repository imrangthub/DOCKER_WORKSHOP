//package com.imranmadbar.config;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
// 
//@WebFilter(urlPatterns = "/*")
//public class WebFilterServlet implements Filter {
//	
//	
//	// Add @ServletComponentScan on Main method for registrade it
// 
//    @Override
//    public void doFilter(ServletRequest request,
//                        ServletResponse response,
//                        FilterChain chain) throws IOException, ServletException
//    {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
// 
//        System.out.println("Request Servlet URI is: " + req.getRequestURI());
//        chain.doFilter(request, response);
//        System.out.println("Response Status Code is: " + res.getStatus());
//    }
//}
