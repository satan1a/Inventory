package cn.db.inventory.until;

import java.text.SimpleDateFormat;

/**
 * Created by jiyun on 2016/6/19.
 */
public class DebugLog {

    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public static void Log(String log) {
        String date = sDateFormat.format(new java.util.Date());
        System.out.println("<" + date + ">" + log);
    }
}