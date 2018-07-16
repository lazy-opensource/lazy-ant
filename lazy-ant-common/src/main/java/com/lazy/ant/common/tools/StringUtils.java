package com.lazy.ant.common.tools;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author laizhiyuan
 * @date 2017/12/29.
 * <p>字符串工具类</p>
 */
@SuppressWarnings("unchecked")
public final class StringUtils {

    public static String camelToSplitName(String camelName, String split) {
        if (camelName == null || camelName.length() == 0) {
            return camelName;
        }
        StringBuilder buf = null;
        for (int i = 0; i < camelName.length(); i++) {
            char ch = camelName.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                if (buf == null) {
                    buf = new StringBuilder();
                    if (i > 0) {
                        buf.append(camelName.substring(0, i));
                    }
                }
                if (i > 0) {
                    buf.append(split);
                }
                buf.append(Character.toLowerCase(ch));
            } else if (buf != null) {
                buf.append(ch);
            }
        }
        return buf == null ? camelName : buf.toString();
    }

    /**
     * 转换为驼峰命名
     * @param sourceStr 源字符串
     * @param split 分割符
     * @return 转换后的字符串
     */
    public static String toCame(String sourceStr, String split){
        if (isBlank(sourceStr)){
            return sourceStr;
        }
        if (isBlank(split)){
            split = "_";
        }
        String[] strArr = sourceStr.split(split);
        int notSplit = 2;
        if (strArr.length < notSplit){
            return strArr[0];
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++){
            if (i == 0){
                builder.append(strArr[i]);
            }else {
                builder.append(toUpperCaseFirstOne(strArr[i]));
            }
        }
        return builder.toString();
    }

    /**
     * 判断字符串是否为空或null
     * @param cs 字符串
     * @return 布尔值
     */
    public static boolean isBlank(CharSequence cs){
        return cs == null || "".equals(cs);
    }

    /**
     * 判断字符串是否不为空或null
     * @param cs 字符串
     * @return 布尔值
     */
    public static boolean isNotBlank(CharSequence cs){
        return !isBlank(cs);
    }

    /**
     * 首字母转大写
     * @param str 字符串
     * @return 首字母大写字符串
     */
    public static String toUpperCaseFirstOne(String str){
        if(Character.isUpperCase(str.charAt(0))){
            return str;
        }else {
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * 首字母转小写
     * @param str 字符串
     * @return 首字母小写字符串
     */
    public static String toLowerCaseFirstOne(String str){
        if(Character.isLowerCase(str.charAt(0))){
            return str;
        }else {
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * List or Set to String by join
     * @param iterable
     * @param join
     * @param <E>
     * @return
     */
    public static <E extends Serializable> String listJoinToStr(Iterable<E> iterable, String join){
        if (iterable == null) {
            return "";
        }
        if (isBlank(join)){
            join = ",";
        }

        Iterator<E> iterator = iterable.iterator();
        StringBuffer stringBuffer = new StringBuffer();
        Object obj;
        if (!iterator.hasNext()){
            return null;
        }
        while (iterator.hasNext()) {
            obj = iterator.next();
            if (obj == null) {
                continue;
            }
            stringBuffer.append(String.valueOf(obj)).append(join);
        }
        stringBuffer = new StringBuffer(stringBuffer.toString().substring(0, stringBuffer.toString().length() -1));
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(toCame("app_ip", "_"));
        System.out.println(toCame("system", null));
//        List list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("3");
//        System.out.println(listJoinToStr(list, null));
    }
}
