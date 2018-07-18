package com.lazy.ant.common.exceptions;

/**
 * <p>
 *     NOT FOUND EXCEPT
 * </p>
 *
 * @author laizhiyuan
 * @date 2018/7/18.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
