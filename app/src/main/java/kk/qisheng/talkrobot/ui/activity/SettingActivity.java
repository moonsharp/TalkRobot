package kk.qisheng.talkrobot.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.utils.MscSpeakUtils;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvSpaker;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        tvSpaker = (TextView) findViewById(R.id.tv_speaker);

        findViewById(R.id.rl_change_speaker).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_change_speaker:
                goSpeakerListActivity();
                break;
        }
    }

    private void goSpeakerListActivity() {
        startActivity(new Intent(this, SpeakerListActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvSpaker.setText(MscSpeakUtils.getSpeakerName(this));
    }

}
