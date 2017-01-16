package kk.qisheng.talkrobot.db;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by KkQisheng on 2017/1/13.
 */

@Entity
public class TalkMsg {
    private int time;

    private int who;

    private String msg;

    @Generated(hash = 281069531)
    public TalkMsg(int time, int who, String msg) {
        this.time = time;
        this.who = who;
        this.msg = msg;
    }

    @Generated(hash = 1397166698)
    public TalkMsg() {
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getWho() {
        return this.who;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
}
