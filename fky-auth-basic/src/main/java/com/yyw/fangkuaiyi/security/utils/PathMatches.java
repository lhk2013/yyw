package com.yyw.fangkuaiyi.security.utils;

import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;

/**
 * Created by lins on 16-2-23.
 */
public class PathMatches {
    private static PatternMatcher pathMatcher = new AntPathMatcher();

    public static boolean compare(String pattern, String path) {
        PatternMatcher pathMatcher = getPathMatcher();
        return pathMatcher.matches(pattern, path);
    }

    public static PatternMatcher getPathMatcher() {
        return pathMatcher;
    }
}
