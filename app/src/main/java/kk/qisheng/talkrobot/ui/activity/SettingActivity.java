package kk.qisheng.talkrobot.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.db.DaoHelper;
import kk.qisheng.talkrobot.db.TalkMsg;
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
        findViewById(R.id.tv_clear).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_change_speaker:
                goSpeakerListActivity();
                break;

            case R.id.tv_clear:
                showDelDialog();
                break;
        }
    }

    private void showDelDialog() {
        new AlertDialog.Builder(this)
                .setMessage("确定要清空所有的聊天记录吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DaoHelper<TalkMsg>().deleteAll(TalkMsg.class);
                        setResult(Activity.RESULT_OK);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
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
