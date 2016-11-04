package com.leanit.subway.common.bean;

/**
 * Created by admin on 2016/8/18.
 */
public class Result {
    public enum ReturnCode {
        success("success"),
        error("error");
        private String desc;

        ReturnCode(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public Result() {
        this.code = ReturnCode.success.getDesc();
    }

    public Result(String msg) {
        this.code = ReturnCode.error.getDesc();
        this.msg = msg;
    }

    String code;
    String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
