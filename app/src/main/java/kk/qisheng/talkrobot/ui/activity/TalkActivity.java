package kk.qisheng.talkrobot.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import kk.qisheng.talkrobot.R;
import kk.qisheng.talkrobot.bean.TalkMsg;
import kk.qisheng.talkrobot.config.AppConfig;
import kk.qisheng.talkrobot.mvp.contact.JuheContact;
import kk.qisheng.talkrobot.mvp.presenter.JuhePresenter;
import kk.qisheng.talkrobot.ui.adapter.TalkListAdapter;
import kk.qisheng.talkrobot.ui.view.LoadingDialog;
import kk.qisheng.talkrobot.utils.LogUtils;
import kk.qisheng.talkrobot.utils.MscListenUtils;
import kk.qisheng.talkrobot.utils.MscSpeakUtils;
import kk.qisheng.talkrobot.utils.NetUtils;
import kk.qisheng.talkrobot.utils.ToastUtil;

public class TalkActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private ListView lvTalk;
    private EditText etInput;
    private TextView tvSend;
    private ImageView ivSpeak;

    private TalkListAdapter mAdapter;
    private ArrayList<TalkMsg> mTalkList = new ArrayList<>();

    private String mMyMsg;
    private long mFirstTime;
    private LoadingDialog mLoading;
    private JuhePresenter mPresenter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_talk);

        initData();
        initView();
        setData();
        initPresenter();
    }

    private void initData() {
        mAdapter = new TalkListAdapter(this, mTalkList);
    }

    private void initView() {
        mLoading = new LoadingDialog(this, "机器人思考中...");

        lvTalk = (ListView) findViewById(R.id.lv_talk);
        etInput = (EditText) findViewById(R.id.et_input_msg);
        tvSend = (TextView) findViewById(R.id.tv_send);
        ivSpeak = (ImageView) findViewById(R.id.iv_speak);

        etInput.addTextChangedListener(this);
        tvSend.setOnClickListener(this);
        ivSpeak.setOnClickListener(this);
        findViewById(R.id.iv_setting).setOnClickListener(this);

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private void setData() {
        lvTalk.setAdapter(mAdapter);
    }

    private void initPresenter() {
        mPresenter = new JuhePresenter(new JuheContact() {
            @Override
            public void onGetRobotResponseSuccess(String info) {
                toggleLoading();
                addMsg(AppConfig.TALK_WHO_ROBOT, info);
                etInput.setText("");
                MscSpeakUtils.speak(TalkActivity.this, info);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send:
                sendMsg(mMyMsg);
                break;
            case R.id.iv_speak:
                showSpeakDialog();
                break;
            case R.id.iv_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;

        }
    }

    /**
     * 向机器人发送消息
     */
    private void sendMsg(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        addMsg(AppConfig.TALK_WHO_ME, msg);
        toggleLoading();
        mPresenter.getRobotResponse(msg);
    }


    /**
     * 语音输入dialog
     */
    private void showSpeakDialog() {
        if (!NetUtils.isNetAvailable(this)) {
            ToastUtil.show(this, "网络未连接");
            return;
        }

        //1.创建SpeechRecognizer对象，第二个参数：本地听写时传InitListener
        RecognizerDialog iatDialog = new RecognizerDialog(this, null);
        //2.设置听写参数
        //3.设置回调接口
        iatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                LogUtils.d("speak onResult: " + recognizerResult.getResultString());
                String msg = MscListenUtils.getListenMsg(recognizerResult.getResultString());
                if (TextUtils.isEmpty(msg)) {
                    ToastUtil.show(TalkActivity.this, "没有听清你说的话哦~");
                } else {
                    if (msg.length() > 1) {
                        sendMsg(msg);
                    }
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                LogUtils.d("speak onError: " + speechError.getErrorDescription());
            }
        });
        //4.开始听写
        iatDialog.show();
    }


    private void addMsg(int who, String msg) {
        TalkMsg talkMsg = new TalkMsg();
        talkMsg.setWho(who);
        talkMsg.setMsg(msg);
        talkMsg.setTime((int) (System.currentTimeMillis() / 1000));

        mTalkList.add(talkMsg);
        notifyDataChange(mTalkList);
        lvTalk.setSelection(mTalkList.size() - 1);
    }

    private void notifyDataChange(ArrayList<TalkMsg> list) {
        Collections.sort(list, new Comparator<TalkMsg>() {
            @Override
            public int compare(TalkMsg lhs, TalkMsg rhs) {
                return lhs.getTime() - rhs.getTime();
            }
        });
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        mMyMsg = etInput.getText().toString().trim();
        tvSend.setEnabled(!TextUtils.isEmpty(mMyMsg));
    }

    private void toggleLoading() {
        if (mLoading == null) return;
        if (mLoading.isShowing()) {
            mLoading.dismiss();
        } else {
            mLoading.show(AppConfig.LOADING_TIME_OUT);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (0.0 != mFirstTime && secondTime - mFirstTime < 2000) {
                System.exit(0);
            } else {
                ToastUtil.show(TalkActivity.this, "再按一次退出程序");
                mFirstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}