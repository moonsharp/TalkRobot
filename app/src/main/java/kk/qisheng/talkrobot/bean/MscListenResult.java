package kk.qisheng.talkrobot.bean;

import java.util.List;

/**
 * Created by KkQisheng on 2017/1/14.
 */
public class MscListenResult {

    public String bg;

    public int ed;

    public boolean ls;

    public int sn;

    public List<Ws> ws;

    public class Ws {
        public int bg;

        public List<Cw> cw;
    }

    public class Cw {
        public float sc;

        public String w;
    }


}
