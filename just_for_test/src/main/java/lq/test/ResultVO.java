package lq.test;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ResultVO<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5120117266796448977L;

    /**
     * 是否成功标识
     */
    private boolean success;

    /**
     *
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 系统时间
     */
    private Date systemTime;

    /**
     * 具体返回VO对象
     */
    private T data;

    /**
     * 重定向链接
     */
    private String redirectUrl;

    /**
     * 构造方法
     */
    public ResultVO() {
    }

    /**
     * 构造方法
     *
     * @param success
     * @param data
     * @param code
     * @param message
     */
    public ResultVO(boolean success, T data, String code, String message) {
        this.code = code;
        this.success = success;
        this.data = data;
        this.message = message;
        this.systemTime = Calendar.getInstance().getTime();
    }

    /**
     * 构造方法
     *
     * @param success
     * @param code
     * @param redirectUrl
     */
    public ResultVO(boolean success, String code, String redirectUrl) {
        this.success = success;
        this.code = code;
        this.redirectUrl = redirectUrl;
    }

    /**
     * 创建
     *
     * @param isSuccess
     * @param data
     * @param errorCode
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultVO create(boolean isSuccess, T data, String errorCode, String message) {
        return new ResultVO<>(isSuccess, data, errorCode, message);
    }

    /**
     * 创建成功VO
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultVO createSuccessVO(T data, String message) {
        return new ResultVO<>(true, data, null, message);
    }

    /**
     * 创建失败VO
     *
     * @param errorCode
     * @param message
     * @return
     */
    public static ResultVO createFailVO(String errorCode, String message) {
        return new ResultVO<>(false, null, errorCode, message);
    }

    /**
     * 创建重定向VO
     *
     * @param redirectUrl
     * @return
     */
    public static ResultVO createRedirectUrlVO(String redirectUrl) {
        return new ResultVO<>(false, HttpStatus.MOVED_TEMPORARILY.value() + "", redirectUrl);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public Date getSystemTime() {
        if (this.systemTime == null) {
            return Calendar.getInstance().getTime();
        }
        return this.systemTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", systemTime=" + systemTime +
                ", data=" + data +
                ", redirectUrl='" + redirectUrl + '\'' +
                '}';
    }
}