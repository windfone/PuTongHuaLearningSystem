package com.hlxyedu.putonghualearningsystem.model.bean;

public class CommentVO {


    /**
     * comId : 109
     * userId : 2
     * comCotent : 李四
     * createTime : 2019-09-03 11:19:37.0
     */

    private int comId;
    private int userId;
    private String comCotent;
    private String createTime;

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComCotent() {
        return comCotent;
    }

    public void setComCotent(String comCotent) {
        this.comCotent = comCotent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
