package com.tony.scala.domain;

import java.io.Serializable;

public class UserResponseBody implements Serializable {
    private int code;
    private Object message;

    public UserResponseBody(int code, Object message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserResponseBody{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }
}
