package kk.qisheng.talkrobot.net;

import kk.qisheng.talkrobot.bean.BaseResponse;
import kk.qisheng.talkrobot.bean.JuheResult;
import kk.qisheng.talkrobot.bean.TransResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by KkQiSheng on 2017/1/11.
 */
public interface ApiService {

    /**
     * 获取机器人回复
     */
    @GET("robot/index")
    Observable<BaseResponse<JuheResult>> getRobotResponse(@Query("key") String key, @Query("info") String info);

    /**
     * 将外语翻译成中文
     */
    @GET("vip/translate")
    Observable<BaseResponse<TransResult>> getTransMsg(@Query("q") String q, @Query("from") String from, @Query("to") String to
            , @Query("appid") String appid, @Query("salt") int salt, @Query("sign") String sign);

}
