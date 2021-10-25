package com.hdq.crowd.mvc.config;

import com.google.gson.Gson;
import com.hdq.crowd.util.CrowdConstant;
import com.hdq.crowd.util.CrowdUtil;
import com.hdq.crowd.exception.LoginFailedException;
import com.hdq.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hdq
 * @create 2021-10-20 14:47
 */
//ControllerAdvice表示当前是一个异常处理注解类
@ControllerAdvice
public class CrowdExceptionResolver {

    // 登陆失败异常 ExceptionHandler把处理方法与一个具体的异常类型绑定起来
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String viewName =  "admin-login";
        return commonResolve(viewName,exception,request,response);
    }
    // 空指针异常 ExceptionHandler把处理方法与一个具体的异常类型绑定起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(NullPointerException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String viewName =  "system-error";
        return commonResolve(viewName,exception,request,response);
    }

    // 创建通用方法
    private ModelAndView commonResolve(String viewName, Exception exception, HttpServletRequest request, HttpServletResponse response ) throws IOException {
        // 1. 判断当前请求类型
        boolean judgeResult = CrowdUtil.judgeRequestType(request);
        // 2. 如果为Ajax请求
        if (judgeResult) {
            // 3. 创建 ResultEntity 对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            // 4. 创建Gson对象,将数据转换成JSON
            Gson gson = new Gson();
            // 5. 将ResultEntity对象转换为JSON字符串
            String json = gson.toJson(resultEntity);
            // 6. 将JSON字符作为响应体返回给浏览器
            response.getWriter().write(json);
            // 7. 上面已经通过原生response对象返回了响应，因此不再提供ModelAndView对象
            return null;
        }
        // 8. 如果不是Ajax请求，则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        // 9. 将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
        // 10. 设置对应的视图名称
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
