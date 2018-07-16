package com.lazy.ant.config;

import java.io.Serializable;

/**
 * <p>
 *     抽象配置基类
 * </p>
 *
 * @author laizhiyuan
 * @date 2018/6/28.
 */
public abstract class AbstractConfig implements Serializable {

    protected String id;

    public String getId() {
        return id;
    }

    public AbstractConfig setId(String id) {
        this.id = id;
        return this;
    }
}
