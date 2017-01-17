package kk.qisheng.talkrobot.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.ui.adapter.SpeakerListAdapter;
import kk.qisheng.talkrobot.utils.MscSpeakUtils;

/**
 * Created by KkQiSheng on 2017/1/12.
 */
public class SpeakerListActivity extends AppCompatActivity {

    private ExpandableListView eLvSpeaker;

    private SpeakerListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_list);
        initData();
        initView();
    }

    private void initData() {
        mAdapter = new SpeakerListAdapter(this, MscSpeakUtils.initSpeaker(this));
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initView() {
        eLvSpeaker = (ExpandableListView) findViewById(R.id.el_speaker);
        eLvSpeaker.setAdapter(mAdapter);
        eLvSpeaker.expandGroup(MscSpeakUtils.getTypeGroup(this));
    }


}
