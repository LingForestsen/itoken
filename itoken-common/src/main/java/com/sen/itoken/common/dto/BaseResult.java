package com.sen.itoken.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Sen
 * @Date: 2019/8/22 22:36
 * @Description: 自定义返回结果
 */
@Data
public class BaseResult implements Serializable {
    private static final String RESULT_SUCCESS = "ok";
    private static final String RESULT_FAIL = "not_ok";
    private static final String SUCCESS = "成功操作";

    private String result;  //装态只有两种ok和not_ok
    private Object data;
    private String success;
    private Cursor cursor;
    private List<Error> errors;

    /**
     * 操作成功
     * @return
     */
    public static BaseResult ok() {
        return Create(RESULT_SUCCESS, null, SUCCESS, null, null);
    }

    public static BaseResult ok(Object data) {
        return Create(RESULT_SUCCESS, data, SUCCESS, null, null);
    }

    public static BaseResult ok(String success) {
        return Create(RESULT_SUCCESS, null, success, null, null);
    }

    public static BaseResult ok(Object data, Cursor cursor) {
        return Create(RESULT_SUCCESS, data, SUCCESS, cursor, null);
    }

    /**
     * 操作失败
     * @return
     */
    public static BaseResult notOk(List<Error> errors) {
        return Create(RESULT_FAIL, null, "", null, errors);
    }


    /**
     * 用输入的信息创建BaseResult
     * @param result
     * @param data
     * @param success
     * @param cursor
     * @param errors
     */
    private static BaseResult Create(String result, Object data, String success, Cursor cursor, List<Error> errors) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccess(success);
        baseResult.setCursor(cursor);
        baseResult.setErrors(errors);
        return baseResult;
    }

    /**
     * 内部类
     */
    @Data
    public static class Cursor{
        private int total;
        private int offset;
        private int limit;
    }
    /**
     * 内部类
     */
    @Data
    @AllArgsConstructor
    public static class Error{
        private String field;
        private String message;
    }

}
