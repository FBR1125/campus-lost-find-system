package com.example.lostandfound.common;

import java.util.HashMap;
import java.util.Map;

// 加上泛型，支持返回任意类型的数据
public class Result<T> {
    private int code;
    private String message;
    private T data;

    // 私有构造
    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应：只返回消息
    public static <T> Result<T> success(String message) {
        return new Result<>(200, message, null);
    }

    // 成功响应：返回消息 + 数据（对象/列表）
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }


    // 成功：只返回数据（给我的失物记录用）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 成功：空参数
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 失败响应
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    // 链式添加数据（兼容之前的put方法）
    public Result<T> put(String key, Object value) {
        if (this.data instanceof Map) {
            ((Map<String, Object>) this.data).put(key, value);
        }
        return this;
    }

    // Getter/Setter
    public int getCode() { return code; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}