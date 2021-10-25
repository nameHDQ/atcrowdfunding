package com.hdq.crowd.exception;

/**没有登陆就访问资源的异常
 * @author hdq
 * @create 2021-10-22 15:40
 */
public class AccessForbiddenException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
