package kk.qisheng.talkrobot.net;


import kk.qisheng.talkrobot.bean.JuheResult;
import kk.qisheng.talkrobot.config.AppConfig;
import kk.qisheng.talkrobot.config.UrlConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by KkQiSheng on 2017/1/11.
 */
public class RetrofitHelper {

    private static RetrofitHelper mRetrofitHelper;
    private JuheService mService;

    public RetrofitHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConfig.JUHE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpClientHelper.getInstance().getClient())
                .build();
        mService = retrofit.create(JuheService.class);
    }


    public static RetrofitHelper getInstance() {
        if (mRetrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (mRetrofitHelper == null) {
                    mRetrofitHelper = new RetrofitHelper();
                }
            }
        }
        return mRetrofitHelper;
    }


    /**
     * 获取机器人回复
     */
    public Observable<JuheResult> getRobotResponse(String info) {
        if (mService == null) {
            new RetrofitHelper();
        }
        return mService.getRobotResponse(AppConfig.JUHE_APP_KEY,info)
                .map(new ServerResponseFunc<JuheResult>())
                .onErrorResumeNext(new HttpResponseFunc<JuheResult>());
    }

}
