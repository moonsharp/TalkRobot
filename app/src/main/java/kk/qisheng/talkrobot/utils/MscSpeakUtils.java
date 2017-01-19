package kk.qisheng.talkrobot.utils;

import android.content.Context;
import android.os.Bundle;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

import java.util.ArrayList;

import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.bean.MscSpeaker;
import kk.qisheng.talkrobot.bean.SpeakerType;

/**
 * Created by KkQisheng on 2017/1/13.
 */
public class MscSpeakUtils {

    public static void speak(Context context, final String msg) {
        LogUtils.d("MscSpeakUtils speak: " + msg);

        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(context, null);

        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, getSpeakerConfig(context));//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "60");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "100");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在SD卡需要在AndroidManifest.xml添加写SD卡权限//如果不需要保存合成音频，注释该行代码
        // mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");

        //3.开始合成
        mTts.startSpeaking(msg, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {
                LogUtils.d("onSpeakBegin: " + msg);
            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {
                LogUtils.d("onBufferProgress: " + i);
            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError speechError) {

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });

    }


    public static ArrayList<SpeakerType> initSpeaker(Context context) {

        ArrayList<MscSpeaker> cnList = new ArrayList<>();
        cnList.add(new MscSpeaker("普通话", "小燕", "xiaoyan", "青年女声", context.getResources().getDrawable(R.drawable.xiaoyan), "cn"));
        cnList.add(new MscSpeaker("普通话", "小峰", "xiaofeng", "青年男声", context.getResources().getDrawable(R.drawable.xiaofeng), "cn"));
        cnList.add(new MscSpeaker("普通话", "老孙", "vils", "中年男声", context.getResources().getDrawable(R.drawable.vils), "cn"));
        cnList.add(new MscSpeaker("普通话", "楠楠", "vinn", "女童声", context.getResources().getDrawable(R.drawable.vinn), "cn"));
        cnList.add(new MscSpeaker("普通话", "许小宝", "aisbabyxu", "男童声", context.getResources().getDrawable(R.drawable.aisbabyxu), "cn"));
        cnList.add(new MscSpeaker("普通话", "小丸子", "xiaowanzi", "卡通人物", context.getResources().getDrawable(R.drawable.xiaowanzi), "cn"));
        cnList.add(new MscSpeaker("普通话", "小新", "xiaoxin", "卡通人物", context.getResources().getDrawable(R.drawable.xiaoxin),"cn"));

        ArrayList<MscSpeaker> dialectList = new ArrayList<>();
        dialectList.add(new MscSpeaker("方言", "大龙", "dalong", "粤语", context.getResources().getDrawable(R.drawable.dalong), "cn"));
        dialectList.add(new MscSpeaker("方言", "小梅", "xiaomei", "粤语", context.getResources().getDrawable(R.drawable.xiaomei), "cn"));
        dialectList.add(new MscSpeaker("方言", "晓琳", "aisxlin", "台普", context.getResources().getDrawable(R.drawable.aisxlin), "cn"));
        dialectList.add(new MscSpeaker("方言", "晓倩", "xiaoqian", "东北话", context.getResources().getDrawable(R.drawable.xiaoqian), "cn"));
        dialectList.add(new MscSpeaker("方言", "小蓉", "aisxrong", "四川话", context.getResources().getDrawable(R.drawable.aisxrong), "cn"));
        dialectList.add(new MscSpeaker("方言", "小坤", "xiaokun", "河南话", context.getResources().getDrawable(R.drawable.xiaokun), "cn"));
        dialectList.add(new MscSpeaker("方言", "小强", "aisxqiang", "湖南话", context.getResources().getDrawable(R.drawable.aisxqiang), "cn"));
        dialectList.add(new MscSpeaker("方言", "小英", "aisxying", "陕西话", context.getResources().getDrawable(R.drawable.aisxying), "cn"));

        ArrayList<MscSpeaker> foreignList = new ArrayList<>();
        foreignList.add(new MscSpeaker("外语", "凯瑟琳", "aiscatherine", "英语", context.getResources().getDrawable(R.drawable.aiscatherine), "en"));
        foreignList.add(new MscSpeaker("外语", "亨利", "henry", "英语", context.getResources().getDrawable(R.drawable.henry), "en"));
        foreignList.add(new MscSpeaker("外语", "玛丽安", "mariane", "法语", context.getResources().getDrawable(R.drawable.mariane), "fra"));
        foreignList.add(new MscSpeaker("外语", "阿拉本", "allabent", "俄语", context.getResources().getDrawable(R.drawable.allabent), "ru"));
        foreignList.add(new MscSpeaker("外语", "加芙列拉", "gabriela", "西班牙语", context.getResources().getDrawable(R.drawable.gabriela), "spa"));
//        foreignList.add(new MscSpeaker("外语", "艾伯哈", "abha", "印地语", context.getResources().getDrawable(R.drawable.abha),"cn"));
        foreignList.add(new MscSpeaker("外语", "小云", "xiaoyun", "越南语", context.getResources().getDrawable(R.drawable.xiaoyun), "vie"));

        ArrayList<SpeakerType> allList = new ArrayList<>();
        allList.add(new SpeakerType("普通话", cnList));
        allList.add(new SpeakerType("方言", dialectList));
        allList.add(new SpeakerType("外语", foreignList));

        return allList;
    }

    public static String getSpeakerConfig(Context context) {
        return (String) SpfUtils.get(context, "current_speaker_config", "xiaoxin");
    }

    public static void setSpeakerConfig(Context context, String config) {
        SpfUtils.put(context, "current_speaker_config", config);
    }

    public static String getSpeakerName(Context context) {
        return (String) SpfUtils.get(context, "current_speaker_name", "小新");
    }

    public static void setSpeakerName(Context context, String name) {
        SpfUtils.put(context, "current_speaker_name", name);
    }

    public static String getSpeakerType(Context context) {
        return (String) SpfUtils.get(context, "current_speaker_type", "普通话");
    }

    public static void setSpeakerType(Context context, String type) {
        SpfUtils.put(context, "current_speaker_type", type);
    }


    public static int getTypeGroup(Context context) {
        String type = getSpeakerType(context);
        if (type.equals("方言")) {
            return 1;
        }
        if (type.equals("外语")) {
            return 2;
        }

        return 0;

    }

    public static String getSpeakerLanguage(Context context) {
        return (String) SpfUtils.get(context, "current_speaker_language", "cn");
    }

    public static void setSpeakerLanguage(Context context, String language) {
        SpfUtils.put(context, "current_speaker_language", language);
    }

    public static String getSpeakerDes(Context context) {
        return (String) SpfUtils.get(context, "current_speaker_des", "卡通人物");
    }

    public static void setSpeakerDes(Context context, String des) {
        SpfUtils.put(context, "current_speaker_des", des);
    }

}
