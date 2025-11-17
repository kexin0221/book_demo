package com.bite.book_demo.interceptor;

import com.bite.book_demo.constant.Constants;
import com.bite.book_demo.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle目标方法执行前...");
        // true -放行   false -拦截
        HttpSession session = request.getSession(false);
        if (!checkUser(session)) {
            response.setContentType("type/html;charset=utf-8");
            response.setStatus(401);
            String msg = "用户未登录";
            response.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));
            return false;
        }
        return true;
    }

    public boolean checkUser(HttpSession session) {
        if (session == null || session.getAttribute(Constants.SESSION_USER_KEY) == null) {
            log.warn("用户未登录");
            return false;
        }
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER_KEY);
        // 用户未登录
        if (userInfo == null || userInfo.getId() <= 0) {
            log.warn("用户未登录");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        log.info("afterCompletion...");
    }
}
