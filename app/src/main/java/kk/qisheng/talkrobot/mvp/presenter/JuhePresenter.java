package kk.qisheng.talkrobot.mvp.presenter;

import android.text.TextUtils;

import kk.qisheng.talkrobot.bean.JuheResult;
import kk.qisheng.talkrobot.mvp.contact.JuheContact;
import kk.qisheng.talkrobot.net.ExceptionEngine;
import kk.qisheng.talkrobot.net.JuheSubscriber;
import kk.qisheng.talkrobot.net.RetrofitHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KkQiSheng on 2017/1/11.
 */
public class JuhePresenter {

    private JuheContact mContact;

    public JuhePresenter(JuheContact contact) {
        this.mContact = contact;
    }

    public void getRobotResponse(String info) {
        RetrofitHelper.getInstance()
                .getRobotResponse(info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new JuheSubscriber<JuheResult>() {
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
                        String info = "这个问题我无法回答哟~";
                        if (juheResult != null && !TextUtils.isEmpty(juheResult.getText())) {
                            info = juheResult.getText();
                        }

                        if (mContact != null) {
                            mContact.onGetRobotResponseSuccess(info);
                        }
                    }
                });

    }


}
