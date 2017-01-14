package kk.qisheng.talkrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kk.qisheng.talkrobot.mvp.contact.JuheContact;
import kk.qisheng.talkrobot.mvp.presenter.JuhePresenter;
import kk.qisheng.talkrobot.utils.LogUtils;

public class MainActivity extends AppCompatActivity {
    private JuhePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                talkToRobot();
            }
        });

        initPresenter();
    }

    private void initPresenter() {
        mPresenter = new JuhePresenter(new JuheContact() {
            @Override
            public void onGetRobotResponseSuccess(String info) {
                LogUtils.d("onGetRobotResponseSuccess: " + info);
            }

        });
    }

    private void talkToRobot() {
        mPresenter.getRobotResponse("中");
    }
}
