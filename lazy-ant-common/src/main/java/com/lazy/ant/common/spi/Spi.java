package com.lazy.ant.common.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * <p>
 *     Spi 注解
 * </p>
 *
 * @author laizhiyuan
 * @date 2018/7/18.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={TYPE})
public @interface Spi {

    String value();
}
