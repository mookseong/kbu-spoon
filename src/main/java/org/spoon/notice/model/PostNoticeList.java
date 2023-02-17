package org.spoon.notice.model;

public class PostNoticeList {
    private final String pageNum;
    private final String title;
    private final String author;
    private final String date;
    private final String url;

    public String getUrl() {
        return this.url;
    }

    public String getPageNum() {
        return this.pageNum;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getDate() {
        return this.date;
    }

    public PostNoticeList(String pageNum, String title, String author, String date, String url) {
        this.pageNum = pageNum;
        this.title = title;
        this.author = author;
        this.date = date;
        this.url = url;
    }
}
