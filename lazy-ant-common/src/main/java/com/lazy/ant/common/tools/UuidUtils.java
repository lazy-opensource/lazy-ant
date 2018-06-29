package com.lazy.ant.common.tools;

import java.util.UUID;

/**
 * @author laizhiyuan
 * @date 2018/1/4.
 * @desc <p>uuid 工具类</p>
 */
public abstract class UuidUtils {

    /**
     * 获取一个UUID
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUuid());
    }
}
