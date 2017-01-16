package kk.qisheng.talkrobot.net;

import rx.Subscriber;

/**
 * Created by KkQisheng on 2017/1/14.
 */
public abstract class ApiSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        if(e instanceof ExceptionEngine.ApiException){
            onError((ExceptionEngine.ApiException) e);
        }else{
            onError(new ExceptionEngine.ApiException(e,123));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ExceptionEngine.ApiException ex);

}
