package kk.qisheng.talkrobot.mvp.presenter;

import android.text.TextUtils;

import kk.qisheng.talkrobot.bean.BaseResponse;
import kk.qisheng.talkrobot.bean.JuheResult;
import kk.qisheng.talkrobot.bean.TransResult;
import kk.qisheng.talkrobot.config.AppConfig;
import kk.qisheng.talkrobot.config.UrlConfig;
import kk.qisheng.talkrobot.mvp.contact.TalkViewContact;
import kk.qisheng.talkrobot.net.ApiSubscriber;
import kk.qisheng.talkrobot.net.ExceptionEngine;
import kk.qisheng.talkrobot.net.RetrofitHelper;
import kk.qisheng.talkrobot.utils.LogUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KkQiSheng on 2017/1/11.
 */
public class ApiPresenter {

    private TalkViewContact mContact;

    public ApiPresenter(TalkViewContact contact) {
        this.mContact = contact;
    }

    public void getRobotResponse(final String info) {
        RetrofitHelper.getInstance(UrlConfig.JUHE_BASE_URL)
                .getRobotResponse(info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<JuheResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(ExceptionEngine.ApiException e) {
                        if (mContact != null) {
                            mContact.onGetRobotResponseSuccess(e.message);
                        }
                    }

                    @Override
                    public void onNext(JuheResult juheResult) {
                        String result = AppConfig.ERROR_RESPONSE_API;
                        if (juheResult != null && !TextUtils.isEmpty(juheResult.getText())) {
                            result = juheResult.getText();
                        }

                        if (mContact != null) {
                            mContact.onGetRobotResponseSuccess(result);
                        }
                    }
                });

    }

    public void getTransMsg(final String info, String to) {
        RetrofitHelper.getInstance(UrlConfig.BAIDU_TRANSLATE_BASE_URL)
                .getTransMsg(info, to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<BaseResponse<TransResult>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(ExceptionEngine.ApiException e) {
                        LogUtils.d("getTransMsg onError: " + e.getMessage());
                        if (mContact != null) {
                            mContact.onTransMsgSuccess(info);
                        }
                    }

                    @Override
                    public void onNext(BaseResponse<TransResult> baseResponse) {
                        String result;
                        try {
                            result = baseResponse.getTrans_result().get(0).dst;
                        } catch (Exception e) {
                            result = "I can't answer this question";
                        }
                        if (mContact != null) {
                            mContact.onTransMsgSuccess(result);
                        }

                    }
                });
    }


}
