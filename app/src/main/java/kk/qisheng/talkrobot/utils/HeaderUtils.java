package kk.qisheng.talkrobot.utils;

import android.content.Context;

import java.io.File;

/**
 * Created by KkQisheng on 2017/1/16.
 */
public class HeaderUtils {

    public static void setMyHeader(Context context, String path) {
        SpfUtils.put(context, "header_me", path);
    }

    public static File getMyHeader(Context context) {
        String path = (String) SpfUtils.get(context, "header_me", "");
        File file = new File(path);
        if(file.exists()){
            return file;
        }
        return null;
    }

    public static void setRobotHeader(Context context, String path) {
        SpfUtils.put(context, "header_robot", path);
    }

    public static File getRobotHeader(Context context) {
        String path = (String) SpfUtils.get(context, "header_robot", "");
        File file = new File(path);
        if(file.exists()){
            return file;
        }
        return null;
    }

}
