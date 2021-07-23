package com.seven.domain;

public class Notice {
    private int id;
    private int userId;
    private String authorName;
    private String title;
    private String content;
    private String newestTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public String getNewestTime() {
        return newestTime;
    }

    public void setNewestTime(String newestTime) {
        this.newestTime = newestTime;
    }
}
