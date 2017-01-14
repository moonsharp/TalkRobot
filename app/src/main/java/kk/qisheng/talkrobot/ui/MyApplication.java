package kk.qisheng.talkrobot.ui;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by KkQisheng on 2017/1/13.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=5878a903");
    }
}
