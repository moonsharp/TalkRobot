package kk.qisheng.talkrobot.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.config.AppConfig;
import kk.qisheng.talkrobot.db.TalkMsg;
import kk.qisheng.talkrobot.ui.view.GlideCircleTransform;
import kk.qisheng.talkrobot.utils.HeaderUtils;

/**
 * Created by KkQisheng on 2017/1/13.
 */
public class TalkListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<TalkMsg> mData = new ArrayList<>();


    public TalkListAdapter(Context context, ArrayList<TalkMsg> list) {
        super();
        this.mContext = context;
        this.mData = list;
    }

    public void refreshData(ArrayList<TalkMsg> list) {
        mData = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();

        TalkMsg talkMsg = mData.get(position);

        convertView = LayoutInflater.from(mContext).inflate(talkMsg.getWho() == AppConfig.TALK_WHO_ME ? R.layout.item_msg_me : R.layout.item_msg_robot, null);
        holder.tvMsg = (TextView) convertView.findViewById(R.id.tv_talk_msg);
        holder.ivHeader = (ImageView) convertView.findViewById(R.id.iv_header);

        if (!TextUtils.isEmpty(talkMsg.getMsg())) {
            holder.tvMsg.setText(talkMsg.getMsg());
        }

        loadHeader(talkMsg.getWho(), holder.ivHeader);

        return convertView;
    }

    private void loadHeader(int who, ImageView iv) {
        File myHeader = HeaderUtils.getMyHeader(mContext);
        if (who == AppConfig.TALK_WHO_ME && myHeader != null) {
            Glide.with(mContext).load(myHeader).transform(new GlideCircleTransform(mContext)).into(iv);
        }

        File robotHeader = HeaderUtils.getRobotHeader(mContext);
        if (who == AppConfig.TALK_WHO_ROBOT && robotHeader != null) {
            Glide.with(mContext).load(robotHeader).transform(new GlideCircleTransform(mContext)).into(iv);
        }
    }

    class Holder {
        ImageView ivHeader;
        TextView tvMsg;
    }

}
