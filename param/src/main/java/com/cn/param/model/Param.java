package com.cn.param.model;

public class Param {

    private Integer dataType;
    private Object data;

    public Integer getDataType() {
        return this.dataType;
    }
    public Object getData() {
        return this.data;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Param{" +
                "dataType=" + dataType +
                ", data=" + data +
                '}';
    }
}
