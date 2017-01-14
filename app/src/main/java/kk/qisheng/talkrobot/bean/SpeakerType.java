package kk.qisheng.talkrobot.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KkQisheng on 2017/1/14.
 */
public class SpeakerType {

    public SpeakerType(String type, ArrayList<MscSpeaker> list) {
        this.type = type;
        this.list = list;
    }

    private String type;

    private ArrayList<MscSpeaker> list;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MscSpeaker> getList() {
        return list;
    }

    public void setList(ArrayList<MscSpeaker> list) {
        this.list = list;
    }
}
