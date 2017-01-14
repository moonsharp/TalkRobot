package kk.qisheng.talkrobot.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import kk.qisheng.talkrobot.R;


/**
 * 加载对话框
 *
 * @author Administrator
 */
public class LoadingDialog extends Dialog {

    private ImageView mIvProgress;
    private AnimationDrawable mProgressAnimationDrawable;
    private String mMsg;
    private TextView mTvMessage;

    private Handler mHandler;
    private TimeoutRunnable mRunnable;

    public LoadingDialog(Context context, String msg) {
        super(context, R.style.TransparentDialog);
        this.mMsg = msg;
        createDailog();
    }

    private void createDailog() {
        setContentView(R.layout.dialog_loading);
        initView();
    }

    private void initView() {
        mTvMessage = (TextView) findViewById(R.id.tv_message);
        setMessage(mMsg);

        mIvProgress = (ImageView) findViewById(R.id.iv_progress);
    }

    public void setMessage(int resId) {
        mTvMessage.setText(resId);
    }

    public void setMessage(String message) {
        mTvMessage.setText(message);
    }

    @Override
    public void dismiss() {
        super.dismiss();

        if (mProgressAnimationDrawable != null) {
            mProgressAnimationDrawable.stop();
        }

        if (mHandler != null && mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    /**
     * 外部方法，显示对话框
     *
     * @param timeout 超时时间
     */
    public void show(int timeout) {
        if (mHandler == null) {
            mHandler = new Handler();
        }

        if (mRunnable == null) {
            mRunnable = new TimeoutRunnable();
        }

        if (timeout > 0) {
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(mRunnable, timeout);
        }

        show();

    }

    /**
     * 外部方法，显示对话框
     */
    @Override
    public void show() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        getWindow().setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        super.show();

        mProgressAnimationDrawable = (AnimationDrawable) mIvProgress.getDrawable();
        mProgressAnimationDrawable.start();
    }

    /**
     * 超时任务
     */
    public class TimeoutRunnable implements Runnable {

        @Override
        public void run() {
            dismiss();
        }
    }


}
