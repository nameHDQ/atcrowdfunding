package com.hdq.crowd.mvc.interceptor;

import com.hdq.crowd.entity.Admin;
import com.hdq.crowd.util.CrowdConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**登录拦截
 * @author hdq
 * @create 2021-10-22 14:21
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //会话中是否存在用户ID
        Admin admin = (Admin) request.getSession().getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        if (admin == null){
            throw new RuntimeException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        }
        //如果登录，放行
        return true;
    }
}
