package cn.db.inventory.until;

import java.text.SimpleDateFormat;

/**
 * Created by Satan1a on 2018/8/24.
 */
public class DebugLog {

    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public static void Log(String log) {
        String date = sDateFormat.format(new java.util.Date());
        System.out.println("<" + date + ">" + log);
    }
}