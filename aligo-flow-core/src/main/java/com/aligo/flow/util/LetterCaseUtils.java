package com.aligo.flow.util;

/**
 * 首字母大小写转换
 * @author minghui.y
 * @create 2021-12-26 10:24 下午
 **/
public class LetterCaseUtils {

    /**
     * 首字母转换成小写
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        if (Character.isLowerCase( str.charAt( 0 ) )) {
            return str;
        }
        return Character.toLowerCase( str.charAt( 0 ) ) + str.substring( 1 );
    }

    /**
     * 首字母转换成大写
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        if (Character.isUpperCase( str.charAt( 0 ) )) {
            return str;
        }
        return Character.isUpperCase( str.charAt( 0 ) ) + str.substring( 1 );
    }
}
