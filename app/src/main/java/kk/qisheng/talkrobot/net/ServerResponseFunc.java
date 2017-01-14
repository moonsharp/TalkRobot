package kk.qisheng.talkrobot.net;

import kk.qisheng.talkrobot.bean.JuheResponse;
import rx.functions.Func1;

/**
 * Created by KkQisheng on 2017/1/14.
 */
//拦截固定格式的公共数据类型Response<T>,判断里面的状态码
public class ServerResponseFunc<T> implements Func1<JuheResponse<T>, T> {
    @Override
    public T call(JuheResponse<T> reponse) {
        //对返回码进行判断，如果不是0，则证明服务器端返回错误信息了，便根据跟服务器约定好的错误码去解析异常
        if (reponse.getError_code() != 0) {
            //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
            throw new ExceptionEngine.ServerException(reponse.getError_code(),reponse.getReason());
        }
        //服务器请求数据成功，返回里面的数据实体
        return reponse.getResult();
    }

}
