package kk.qisheng.talkrobot.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by KkQiSheng on 2017/1/11.
 */
public class OkHttpClientHelper {
    private static OkHttpClientHelper instance;
    private OkHttpClient mClient;

    public static OkHttpClientHelper getInstance() {
        if (instance == null) {
            synchronized (OkHttpClientHelper.class) {
                if (instance == null) {
                    instance = new OkHttpClientHelper();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getClient() {
        if (mClient == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mClient = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
        }

        return mClient;
    }

}