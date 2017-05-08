package com.cfw.movies.commons.enums;

/**
 * Created by Duskrain on 2017/5/8.
 */
public enum AccountTypeEnum {

    MOVIE("10","本网"),
    WEIBO("20","WEIBO"),
    QQ("30","QQ"),
    WECHAT("40","WECHAT")
    ;

    public String type;
    public String desc;

    AccountTypeEnum(String type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
