package com.sen.itoken.common.constans;

/**
 * @Auther: Sen
 * @Date: 2019/8/23 05:35
 * @Description:
 */
public enum HttpStatusContants {
    BAD_GATEWAY(502,"上层服务器请求失败");
    private int status;
    private String message;

     HttpStatusContants(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
