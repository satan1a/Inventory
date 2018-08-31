package com.satan1a.inventory.until;

import java.text.SimpleDateFormat;

/**
 * 【日志信息】
 *  功能：在控制台输出程序运行时的 Debug 信息
 */
public class DebugLog {
    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public static void Log(String log) {
        String date = sDateFormat.format(new java.util.Date());
        System.out.println("<" + date + ">" + log);
    }
}