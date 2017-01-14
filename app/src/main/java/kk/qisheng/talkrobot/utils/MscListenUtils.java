package kk.qisheng.talkrobot.utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechRecognizer;

import java.util.List;

import kk.qisheng.talkrobot.bean.MscListenResult;

/**
 * Created by KkQisheng on 2017/1/14.
 */
public class MscListenUtils {

    public static void listen(Context context, RecognizerListener listener) {
        //1.创建SpeechRecognizer对象，第二个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(context, null);
        //2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        mIat.startListening(listener);
    }

    public static String getListenMsg(String result) {
        return getListenMsg(result, true);
    }

    public static String getListenMsg(String result, boolean isFilter) {
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder("");
        try {
            MscListenResult listenResult = gson.fromJson(result, MscListenResult.class);
            List<MscListenResult.Ws> wsList = listenResult.ws;

            for (MscListenResult.Ws ws : wsList) {
                String word = ws.cw.get(0).w;
                if (!TextUtils.isEmpty(word)) {
                    sb.append(word);
                }
            }

        } catch (Exception e) {
            LogUtils.d("getListenMsg Exception: " + e.getMessage());
        }

        String msg = sb.toString();
        if (isFilter && !TextUtils.isEmpty(msg) && msg.length() > 30) {
            msg = msg.substring(0, 30);
        }
        LogUtils.d("getListenMsg: " + msg);
        return msg;
    }

}
