package kk.qisheng.talkrobot.ui.activity;

import android.view.View;
import android.widget.ExpandableListView;

import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.ui.adapter.SpeakerListAdapter;
import kk.qisheng.talkrobot.utils.MscSpeakUtils;

/**
 * Created by KkQiSheng on 2017/1/12.
 */
public class SpeakerListActivity extends BaseActivity {

    private ExpandableListView eLvSpeaker;

    private SpeakerListAdapter mAdapter;

    @Override
    public void setContentLayout() {
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
