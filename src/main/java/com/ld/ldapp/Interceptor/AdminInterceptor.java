package com.ld.ldapp.Interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.service.impl.LogService;
import com.ld.ldapp.util.JwtToken;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


@Slf4j
public class AdminInterceptor implements HandlerInterceptor {



    public static ThreadLocal<JSONObject> localVar = new ThreadLocal<>();

    LogService logService;

    public AdminInterceptor(LogService logService){
        this.logService=logService;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("HandlerInterceptor");
        String token=request.getHeader("Authorization");
        Claims claims=JwtToken.getClaimByToken(token);

        if(claims==null) {
            String servletPath = request.getServletPath();
            response.setStatus(403);
            log.info("403 没有权限----{}",token);

            log.info("path----{}",servletPath);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null ;
            try{
                JSONObject res = new JSONObject();
                res.put("massage","没有权限");
                res.put("code",103);

                out = response.getWriter();
                out.append(res.toString());
                return false;
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    response.sendError(500);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                return false;
            }


        }else{

            String servletPath = request.getServletPath();
            String sub=claims.getSubject();
            JSONObject jsonObject=JSON.parseObject(sub);
            jsonObject.put("servletPath",servletPath);
            logService.addLog(jsonObject);
            localVar.set(jsonObject);

//            BufferedReader reader = null;
//            try {
//                reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//                String str = "";
//                String wholeStr = "";
//                while((str = reader.readLine()) != null){//一行一行的读取body体里面的内容；
//                    wholeStr += str;
//                }
//                JSONObject t=JSONObject.parseObject(wholeStr);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


        }


//        if(JwtToken.isTokenExpired(claims.getExpiration())){
//            String newToken=JwtToken.generateToken(sub);
//            response.setHeader("Access-Control-Expose-Headers", "Authorization");
//            response.setHeader("Authorization", newToken);
//
//        }

        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }
}
