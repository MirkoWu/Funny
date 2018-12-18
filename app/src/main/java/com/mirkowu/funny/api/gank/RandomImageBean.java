package com.mirkowu.funny.api.gank;

/**
 * @author by DELL
 * @date on 2018/12/18
 * @describe
 */
public class RandomImageBean {

    /**
     * code : 200
     * acgurl : https://ws1.sinaimg.cn/large/0072Vf1pgy1foxkfy08umj31kw0w0nng.jpg
     * width : 2048
     * height : 1152
     */

    private int code;
    private String acgurl;
    private String width;
    private String height;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAcgurl() {
        return acgurl;
    }

    public void setAcgurl(String acgurl) {
        this.acgurl = acgurl;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
