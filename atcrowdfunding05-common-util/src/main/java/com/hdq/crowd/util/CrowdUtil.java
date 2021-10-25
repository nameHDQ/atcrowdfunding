package com.hdq.crowd.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hdq
 * @create 2021-10-20 14:39
 */
public class CrowdUtil {

    /*
     * 判断当前请求是否为Ajax请求
     * @param request 请求对象
     * @return
     *      true：当前请求为Ajax请求
     *      false：当前请求不是Ajax请求
     */

    public static boolean judgeRequestType(HttpServletRequest request) {

        // 1. 获取请求头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Request-With");

        // 2. 判断,两个有一个就是ajax请求
        return (acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }

}
