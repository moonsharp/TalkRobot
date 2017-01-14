package kk.qisheng.talkrobot.net;

import kk.qisheng.talkrobot.bean.JuheResult;
import kk.qisheng.talkrobot.bean.JuheResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by KkQiSheng on 2017/1/11.
 */
public interface JuheService {

    /**
     * 获取机器人回复
     */
    @GET("robot/index")
    Observable<JuheResponse<JuheResult>> getRobotResponse(@Query("key") String key, @Query("info") String info);

}
