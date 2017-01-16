package kk.qisheng.talkrobot.bean;

import java.util.List;

public class BaseResponse<T> {
    private String reason;
    private int error_code;
    private T result;

    private String from;
    private String to;
    private String error_msg;
    private List<T> trans_result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<T> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<T> trans_result) {
        this.trans_result = trans_result;
    }
}
