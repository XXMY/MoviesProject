package com.cfw.movies.commons.vo;

/**
 * Created by Duskrain on 2017/1/17.
 */
public class RsaVO {
    private Integer v;
    private String key;
    private String data;

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RSABo [" +
                "v=" + v +
                ", key=" + key +
                ", data=" + data +
                "]";
    }
}
