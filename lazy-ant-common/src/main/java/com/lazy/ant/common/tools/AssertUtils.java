package com.lazy.ant.common.tools;

import java.util.Collection;

/**
 * @author laizhiyuan
 * @date 2018/1/12.
 * <p>断言工具类</p>
 */
public abstract class AssertUtils {

    /**
     * 断言对象不为空时，操作不合法
     * @param object 断言对象
     * @param msg 断言消息
     */
    public static void isNotNullThrowOperIllegal(Object object, String msg){
        if (object != null){
            throw new RuntimeException(msg);
        }
    }

    /**
     * 断言范围
     * @param value 值
     * @param begin 开始数
     * @param end 结束数
     * @param msg 消息
     */
    public static void isRange(Long value, Long begin, Long end, String msg){
        isNull(value, "value is null");
        isNull(begin, "begin is null");
        isNull(end, "end is null");
        if (value < begin || value > end){
            throw new RuntimeException(msg);
        }
    }

    /**
     * 断言CharSequence子类是否为空或Null
     * @param cs java.lang.CharSequence
     * @param msg 满足条件时的描述
     */
    public static void isBlank(CharSequence cs, String msg){
        if (StringUtils.isBlank(cs)){
            throw new RuntimeException(msg);
        }
    }

    /**
     * 断言Object子类是否为Null
     * @param object java.lang.Object
     * @param msg 满足条件时的描述
     */
    public static void isNull(Object object, String msg){
        if (object == null){
            throw new RuntimeException(msg);
        }
    }

    /**
     * 断言不支持为空
     * @param object 对象
     * @param msg 满足条件时的描述
     */
    public static void notSupportEmpty(Object object, String msg){
        if (object == null){
            throw new RuntimeException(msg);
        }
    }

    /**
     * 断言为空时，没有找到资源
     * @param object 资源对象
     * @param msg 满足条件时的描述
     */
    public static void notFound(Object object, String msg){
        if (object == null){
            throw new RuntimeException(msg);
        }
    }

    /**
     * 断言为空集合时则不合法
     * @param collection 集合
     * @param msg 满足条件时的描述
     */
    public static void isILlegal(Collection collection, String msg){
        if (collection.size() < 1){
            throw new RuntimeException(msg);
        }
    }

    /**
     * 断言为空时则不合法
     * @param object 对象
     * @param msg 满足条件时的描述
     */
    public static void isILlegal(Object object, String msg){
        if (object == null){
            throw new RuntimeException(msg);
        }
    }

    /**
     * 断言Object子类是否不为Null
     * @param object java.lang.Object
     * @param msg 满足条件时的描述
     */
    public static void isNotNull(Object object, String msg){
        if (object != null){
            throw new RuntimeException(msg);
        }
    }

    /**
     * 断言是否设置id
     * @param id java.lang.long
     * @param msg 满足条件时的描述
     */
    public static void hasId(long id, String msg){
        if (id == 0L){
            throw new RuntimeException(msg);
        }
    }

}
