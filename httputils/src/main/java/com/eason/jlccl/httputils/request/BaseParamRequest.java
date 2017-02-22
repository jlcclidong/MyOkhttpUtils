package com.eason.jlccl.httputils.request;

import com.eason.jlccl.httputils.Ok;
import com.eason.jlccl.httputils.headerparams.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cclej on 2017/2/21.
 */

public abstract class BaseParamRequest<T> extends BaseRequest<BaseParamRequest<T>> {
    protected List<Param> params;

    public BaseParamRequest() {
        params = new ArrayList<>();
        if (Ok.getCommonParams() != null) {
            for (Param param : params) {
                param(param.getKey(), param.getValue());
            }
        }
    }

    public T param(String key, String value) {
        params.add(new Param(key, value));
        return (T) this;
    }

    public T param(String key, boolean value) {
        params.add(new Param(key, String.valueOf(value)));
        return (T) this;
    }

    public T param(String key, int value) {
        params.add(new Param(key, String.valueOf(value)));
        return (T) this;
    }

    public T param(String key, long value) {
        params.add(new Param(key, String.valueOf(value)));
        return (T) this;
    }

    public T param(String key, float value) {
        params.add(new Param(key, String.valueOf(value)));
        return (T) this;
    }

    public T param(String key, double value) {
        params.add(new Param(key, String.valueOf(value)));
        return (T) this;
    }
}
