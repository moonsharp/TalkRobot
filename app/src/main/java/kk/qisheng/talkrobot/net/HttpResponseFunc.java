package kk.qisheng.talkrobot.net;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by KkQisheng on 2017/1/14.
 */
public class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>> {
    @Override
    public Observable<T> call(Throwable throwable) {
        //ExceptionEngine为处理异常的驱动器
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
