package kk.qisheng.talkrobot.bean;

public class JuheResponse<T> {
    private String reason;
    private int error_code;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    private T result;

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


}
