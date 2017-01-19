package kk.qisheng.talkrobot.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import kk.qisheng.library.PhotoChooser;
import kk.qisheng.library.bean.PhotoResult;
import kk.qisheng.library.callback.OnGetPhotoCallback;
import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.config.AppConfig;
import kk.qisheng.talkrobot.db.DaoHelper;
import kk.qisheng.talkrobot.db.TalkMsg;
import kk.qisheng.talkrobot.ui.view.GlideCircleTransform;
import kk.qisheng.talkrobot.utils.HeaderUtils;
import kk.qisheng.talkrobot.utils.MscSpeakUtils;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSpaker;
    private ImageView ivMyHeader, ivRobotHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        loadHeader();
    }

    private void initView() {
        tvSpaker = (TextView) findViewById(R.id.tv_speaker);
        ivMyHeader = (ImageView) findViewById(R.id.iv_my_header);
        ivRobotHeader = (ImageView) findViewById(R.id.iv_robot_header);

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.rl_change_speaker).setOnClickListener(this);
        findViewById(R.id.rl_my_hdader).setOnClickListener(this);
        findViewById(R.id.rl_robot_hdader).setOnClickListener(this);
        findViewById(R.id.tv_clear).setOnClickListener(this);
    }

    private void loadHeader() {
        File myHeader = HeaderUtils.getMyHeader(this);
        if (myHeader != null) {
            Glide.with(SettingActivity.this).load(myHeader).transform(new GlideCircleTransform(SettingActivity.this)).into(ivMyHeader);
        }
        File robotHeader = HeaderUtils.getRobotHeader(this);
        if (robotHeader != null) {
            Glide.with(SettingActivity.this).load(robotHeader).transform(new GlideCircleTransform(SettingActivity.this)).into(ivRobotHeader);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.rl_change_speaker:
                goSpeakerListActivity();
                break;

            case R.id.rl_my_hdader:
                getHeader(AppConfig.TALK_WHO_ME);
                break;

            case R.id.rl_robot_hdader:
                getHeader(AppConfig.TALK_WHO_ROBOT);
                break;

            case R.id.tv_clear:
                showDelDialog();
                break;
        }
    }


    private void goSpeakerListActivity() {
        startActivity(new Intent(this, SpeakerListActivity.class));
    }

    private void getHeader(final int who) {
        File headerFile = HeaderUtils.getRobotHeader(this);
        if (who == AppConfig.TALK_WHO_ME) {
            headerFile = HeaderUtils.getMyHeader(this);
        }
        String extraPath = "";
        if (headerFile != null) {
            extraPath = headerFile.getAbsolutePath();
        }

        PhotoChooser.getInstance().getPhotos(this, extraPath, new OnGetPhotoCallback() {
            @Override
            public void onGetPhotos(ArrayList<PhotoResult> list) {
                if (list != null && list.size() > 0) {
                    String path = list.get(0).getThumbnailPath();
                    File file = new File(path);
                    if (!file.exists()) return;

                    switch (who) {
                        case AppConfig.TALK_WHO_ME:
                            HeaderUtils.setMyHeader(SettingActivity.this, path);
                            Glide.with(SettingActivity.this).load(file).transform(new GlideCircleTransform(SettingActivity.this)).into(ivMyHeader);
                            break;

                        case AppConfig.TALK_WHO_ROBOT:
                            HeaderUtils.setRobotHeader(SettingActivity.this, path);
                            Glide.with(SettingActivity.this).load(file).transform(new GlideCircleTransform(SettingActivity.this)).into(ivRobotHeader);
                            break;
                    }
                }
            }
        });

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


    @Override
    protected void onResume() {
        super.onResume();
        tvSpaker.setText(MscSpeakUtils.getSpeakerName(this) + " (" + MscSpeakUtils.getSpeakerDes(this) + ")");
    }

}
