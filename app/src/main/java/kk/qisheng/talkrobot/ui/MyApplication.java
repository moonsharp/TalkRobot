package kk.qisheng.talkrobot.ui;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import kk.qisheng.talkrobot.db.GreenDaoManager;

/**
 * Created by KkQisheng on 2017/1/13.
 */
public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=5878a903");
    }

    public static Context getContext() {
        return context;
    }

}
