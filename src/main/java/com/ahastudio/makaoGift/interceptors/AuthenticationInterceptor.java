package com.ahastudio.makaoGift.interceptors;

import com.ahastudio.makaoGift.exceptions.AuthenticationError;
import com.ahastudio.makaoGift.models.MemberName;
import com.ahastudio.makaoGift.utils.JwtUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public AuthenticationInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return true;
        }

        String accessToken = authorization.substring("Bearer ".length());

        try {
            MemberName memberName = new MemberName(jwtUtil.decode(accessToken));
            request.setAttribute("memberName", memberName);

            return true;
        } catch (JWTDecodeException exception) {
            throw new AuthenticationError();
        }
    }
}
