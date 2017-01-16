package kk.qisheng.talkrobot.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.bean.MscSpeaker;
import kk.qisheng.talkrobot.bean.SpeakerType;
import kk.qisheng.talkrobot.utils.MscSpeakUtils;

/**
 * Created by KkQisheng on 2017/1/14.
 */
public class SpeakerListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<SpeakerType> mDatas;

    public SpeakerListAdapter(Context context, ArrayList<SpeakerType> list) {
        this.mContext = context;
        this.mDatas = list;
    }

    @Override
    public int getGroupCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mDatas == null || mDatas.size() < groupPosition) {
            return 0;
        }

        return mDatas.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDatas.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null) {
            holder = new GroupHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_speaker_group, parent, false);
            holder.tvType = (TextView) convertView.findViewById(R.id.tv_type);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        String type = mDatas.get(groupPosition).getType();
        holder.tvType.setText(type);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder = null;
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_speaker_child, parent, false);
            holder.ivImg = (ImageView) convertView.findViewById(R.id.iv_img);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvDes = (TextView) convertView.findViewById(R.id.tv_des);
            holder.ivSelected = (ImageView) convertView.findViewById(R.id.iv_selected);

            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        final MscSpeaker speaker = mDatas.get(groupPosition).getList().get(childPosition);

        holder.ivImg.setImageDrawable(speaker.getImg());
        holder.tvName.setText(speaker.getName());
        holder.tvDes.setText(speaker.getDes());

        String config = speaker.getConfig();
        holder.ivSelected.setVisibility(config.equals(MscSpeakUtils.getSpeakerConfig(mContext)) ? View.VISIBLE : View.GONE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MscSpeakUtils.setSpeakerConfig(mContext, speaker.getConfig());
                MscSpeakUtils.setSpeakerName(mContext, speaker.getName());
                MscSpeakUtils.setSpeakerType(mContext, speaker.getType());
                MscSpeakUtils.setSpeakerLanguage(mContext, speaker.getLanguage());
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupHolder {
        private TextView tvType;
    }

    private class ChildHolder {
        private TextView tvType, tvName, tvDes;
        private ImageView ivImg, ivSelected;
    }


}
