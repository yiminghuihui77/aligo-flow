package com.aligo.flow.util;

/**
 * 流程匹配 工具类
 *
 * source : BIKE:inbound:hands_on
 * pattern: BIKE:inbound:*
 * @author minghui.y
 * @create 2022-01-03 4:37 下午
 **/
public class WildcardUtils {

    public static boolean isMatch(String source, String pattern) {
        char[] sourceArr = source.toCharArray();
        char[] patternArr = pattern.toCharArray();
        int i = 0, j = 0, iStar = -1, jStar = -1, m = source.length(), n = pattern.length();

        while (i < m) {
            if (j < n && (sourceArr[i] == patternArr[j] || patternArr[j] == '?')) {
                i++;
                j++;
            } else if (j < n && patternArr[j] == '*') {
                iStar = i;
                jStar = j++;
            } else if (iStar >= 0) {
                i = ++iStar;
                j = jStar + 1;
            } else {
                return false;
            }
        }

        while (j < n && patternArr[j] == '*') {
            j++;
        }

        return j == n;
    }

}
