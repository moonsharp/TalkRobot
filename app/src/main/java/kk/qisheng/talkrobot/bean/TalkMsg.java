package kk.qisheng.talkrobot.bean;


/**
 * Created by KkQisheng on 2017/1/13.
 */
public class TalkMsg {
    private long id;

    private int time;

    //消息来源，1，表示我方。2，表示机器人。
    private int who;

    private String msg;

    public TalkMsg() {
    }

    public TalkMsg(long id, int time, int who, String msg) {
        this.id = id;
        this.time = time;
        this.who = who;
        this.msg = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "TalkMsg{" +
                "time=" + time +
                ", who=" + who +
                ", msg='" + msg + '\'' +
                '}';
    }
}
