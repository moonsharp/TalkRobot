package kk.qisheng.talkrobot.config;

/**
 * Created by KkQiSheng on 2017/1/11.
 */
public interface AppConfig {

    String JUHE_APP_KEY = "7db7816b3996f157e7e62cad275f767f";

    String MSC_APP_KEY = "5878a903";

    String BAIDU_TRANSLATE_APP_ID = "20170116000035992";
    String BAIDU_TRANSLATE_APP_SECRET = "etEKyxs4ZOeK1wJN8VwS";

    int TALK_WHO_ME = 1;
    int TALK_WHO_ROBOT = 2;

    int LOADING_TIME_OUT = 10 * 1000;

    String ERROR_RESPONSE_NET = "你的网络开小差了，检查一下吧...";
    String ERROR_RESPONSE_API = "这个问题我无法回答哟~";  //接口返回错误时，统一让机器人如此回答

}
