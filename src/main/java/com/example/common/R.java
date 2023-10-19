package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private Integer code;
    private String msg;
    private Object data;

    R(String msg) {
        this.msg = msg;
    }

    public static R error(String msg) {
        R r = new R();
        r.code = 0;
        r.msg = msg;
        return r;
    }

    public static R success(Object object) {
        R r = new R();
        r.code = 1;
        r.data = object;
        return r;
    }

}
