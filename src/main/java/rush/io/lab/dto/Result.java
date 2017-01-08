package rush.io.lab.dto;

/**
 * 封装json结果
 * 所有ajax请求放回类型，返回json结果
 *
 * @author cang
 * @create_time 2017-01-02 19:18
 */
public class Result<T> {

    // 请求是否成功
    private boolean success;

    private T data;

    private String error;

    public Result(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result(boolean success, T data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public Result(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
