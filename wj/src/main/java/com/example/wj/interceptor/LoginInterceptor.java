package com.example.wj.interceptor;

import com.example.wj.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{"index"};
        String uri = request.getRequestURI();
        String page = StringUtils.remove(uri,contextPath+"/");
        if (beginWith(page, requireAuthPages)) {
            User user = (User) session.getAttribute("user");
            if(user==null){
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean beginWith(String page, String[] requireAuthPages) {
        boolean ret = false;
        for (String authPage:requireAuthPages) {
            if (StringUtils.startsWith(page,authPage)){
                ret = true;
                break;
            }
        }
        return ret;
    }
}
