package kk.qisheng.talkrobot.db;


import java.util.List;

/**
 * Created by KkQisheng on 2017/1/16.
 */
public class DaoHelper<T> {

    public boolean insert(T t) {
        return GreenDaoManager.getInstance().getmDaoSession().insert(t) != -1 ? true : false;
    }

    public List<T> listAll(Class cls) {
        return GreenDaoManager.getInstance().getmDaoSession().loadAll(cls);
    }

    public void deleteAll(Class cls) {
        GreenDaoManager.getInstance().getmDaoSession().deleteAll(cls);
    }

}
