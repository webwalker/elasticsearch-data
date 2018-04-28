package com.dayainfo.ssp.exception;

/**
 * @Author: longrui
 * @Date: 2018/3/14 10:35
 */
public class JdbcTemplateException extends Exception{

    public JdbcTemplateException() {
        super();
    }

    public JdbcTemplateException(String message) {
        super(message);
    }

    public JdbcTemplateException(String message, Throwable cause) {
        super(message, cause);
    }
}
