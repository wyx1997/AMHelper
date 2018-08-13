package com.am.example.amtest.entity;

public class LuntanDongtaiRvEntity {

    private String title;

    private String content;

    private String likeNum;

    private String commentNum;

    public LuntanDongtaiRvEntity(String title, String content, String likeNum, String commentNum) {
        this.title = title;
        this.content = content;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }
}
