package com.lmy.rabbitretry.model;

/**
 * @author lmy
 * @data 2021-08-09 10:22
 */
public class RetryDTO {
    private long id;

    private String title;

    private String msg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RetryDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
