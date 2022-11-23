package com.ssafit.interceptor;

import com.ssafit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    // header 정보
    private static final String HEADER_AUTH = "access-token";

    @Autowired
    private JwtUtil jwtUtil;

    // controller 오기 이전 실행


//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        final String token = request.getHeader(HEADER_AUTH);
//        System.out.println(token);
//
//        // 토큰이 있다면
//        if (token != null) {
//            jwtUtil.valid(token);
//
//        } else
//            throw new Exception("유효하지 않은 접근이다.");
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        final String token = request.getHeader(HEADER_AUTH);
        System.out.println(token);

        // 토큰이 있다면
        if (token != null) {
            jwtUtil.valid(token);
            return true;
        }
        throw new Exception("유효하지 않은 접근이다.");
    }
}
