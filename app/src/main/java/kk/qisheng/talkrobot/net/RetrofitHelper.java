package kk.qisheng.talkrobot.net;


import java.util.Random;

import kk.qisheng.talkrobot.bean.BaseResponse;
import kk.qisheng.talkrobot.bean.JuheResult;
import kk.qisheng.talkrobot.bean.TransResult;
import kk.qisheng.talkrobot.config.AppConfig;
import kk.qisheng.talkrobot.config.UrlConfig;
import kk.qisheng.talkrobot.utils.MD5Utils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by KkQiSheng on 2017/1/11.
 */
public class RetrofitHelper {

    private ApiService mService;

    private RetrofitHelper(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpClientHelper.getInstance().getClient())
                .build();
        mService = retrofit.create(ApiService.class);
    }


    public static RetrofitHelper getInstance(String url) {
//        if (mRetrofitHelper == null) {
//            synchronized (RetrofitHelper.class) {
//                if (mRetrofitHelper == null) {
//                    mRetrofitHelper = new RetrofitHelper(url);
//                }
//            }
//        }
        return new RetrofitHelper(url);
    }


    /**
     * 获取机器人回复
     */
    public Observable<JuheResult> getRobotResponse(String info) {
        if (mService == null) {
            new RetrofitHelper(UrlConfig.JUHE_BASE_URL);
        }
        return mService.getRobotResponse(AppConfig.JUHE_APP_KEY, info)
                .map(new ServerResponseFunc<JuheResult>())
                .onErrorResumeNext(new HttpResponseFunc<JuheResult>());
    }

    /**
     * 将外语翻译成中文
     */
    public Observable<BaseResponse<TransResult>> getTransMsg(String info, String to) {
        if (mService == null) {
            new RetrofitHelper(UrlConfig.BAIDU_TRANSLATE_BASE_URL);
        }

        int salt = new Random().nextInt(10) + 1;
        String sign = getTransSign(info, salt);

        return mService.getTransMsg(info, "auto", to, AppConfig.BAIDU_TRANSLATE_APP_ID, salt, sign);
    }

    //拼接appid=2015063000000001+q=apple+salt=1435660288+密钥=12345678
    private String getTransSign(String info, int salt) {
        String source = AppConfig.BAIDU_TRANSLATE_APP_ID + info + salt + AppConfig.BAIDU_TRANSLATE_APP_SECRET;
        String md5 = MD5Utils.getMd5Value(source);
        return md5;
    }

}
