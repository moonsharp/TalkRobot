package kk.qisheng.talkrobot.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by KkQisheng on 2017/1/14.
 */
public class MscSpeaker {

    public MscSpeaker(String type, String name, String config, String des, Drawable img, String language) {
        this.type = type;
        this.name = name;
        this.config = config;
        this.des = des;
        this.img = img;
        this.language = language;
    }

    private String type;

    private String name;

    private String config;

    private String des;

    private Drawable img;

    private String language;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
