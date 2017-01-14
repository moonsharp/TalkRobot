package kk.qisheng.talkrobot.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.bean.TalkMsg;
import kk.qisheng.talkrobot.config.AppConfig;

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

        if (!TextUtils.isEmpty(talkMsg.getMsg())) {
            holder.tvMsg.setText(talkMsg.getMsg());
        }
        return convertView;
    }

    class Holder {
        ImageView ivHeader;
        TextView tvMsg;
    }

}
