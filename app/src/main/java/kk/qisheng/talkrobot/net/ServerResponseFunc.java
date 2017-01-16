package kk.qisheng.talkrobot.net;

import android.text.TextUtils;

import kk.qisheng.talkrobot.bean.BaseResponse;
import rx.functions.Func1;

/**
 * Created by KkQisheng on 2017/1/14.
 */
//拦截固定格式的公共数据类型Response<T>,判断里面的状态码
public class ServerResponseFunc<T> implements Func1<BaseResponse<T>, T> {
    @Override
    public T call(BaseResponse<T> reponse) {
        //对返回码进行判断，如果不是0，则证明服务器端返回错误信息了，便根据跟服务器约定好的错误码去解析异常
        if (reponse.getError_code() != 0) {
            //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
            String msg = TextUtils.isEmpty(reponse.getReason()) ? reponse.getError_msg() : reponse.getReason();
            throw new ExceptionEngine.ServerException(reponse.getError_code(), msg);
        }
        //服务器请求数据成功，返回里面的数据实体
        return reponse.getResult();
    }

}
