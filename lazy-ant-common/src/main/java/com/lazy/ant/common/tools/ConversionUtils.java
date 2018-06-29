package com.lazy.ant.common.tools;

import java.io.*;

/**
 * <p>转换工具类</p>
 *
 * @author laizhiyuan
 * @date 2018/4/23.
 */
public class ConversionUtils {

    /**
     * 获取项目分割符
     * @return 分割符
     */
    public static String getSeparator(){
        String separator = File.separator;
        String reg = "\\";
        if (separator.equals(reg)){
            return "\\\\";
        }
        return separator;
    }

    /**
     * File ->  Byte
     * @param file
     * @return
     */
    public static byte[] file2Byte(File file) throws IOException {
        byte[] buffer = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                fis.close();
            }
            if (bos != null){
                bos.close();
            }
        }
        return buffer;
    }

    /**
     * 文件路径 -> 流 -> 字节
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public static byte[] inputStream2ByteArray(String filePath) throws IOException {
        InputStream in = new FileInputStream(filePath);
        try {
            byte[] data = toByteArray(in);
            return data;
        }finally {
            in.close();
        }
    }

    /**
     * 流 -> 字节
     * @param in 输入流
     * @return 字节
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024 * 4];
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            byte[] result = out.toByteArray();
            return result;
        }finally {
            out.close();
        }
    }
}
