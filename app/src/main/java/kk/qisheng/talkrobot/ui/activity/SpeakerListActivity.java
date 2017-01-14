package kk.qisheng.talkrobot.ui.activity;

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
    }


    private void initView() {
        eLvSpeaker = (ExpandableListView) findViewById(R.id.el_speaker);
        eLvSpeaker.setAdapter(mAdapter);
        eLvSpeaker.expandGroup(MscSpeakUtils.getTypeGroup(this));
    }


}
