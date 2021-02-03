package com.project.daicuongbachkhoa.model;

public class News {

    private String
            imgNews,
            titleNews,
            dateNews,
            contentNews,
            linkNews;

    public News() {
    }

    public News(String imgNews, String titleNews, String dateNews, String contentNews, String linkNews) {
        this.imgNews = imgNews;
        this.titleNews = titleNews;
        this.dateNews = dateNews;
        this.contentNews = contentNews;
        this.linkNews = linkNews;
    }

    public String getImgNews() {
        return imgNews;
    }

    public void setImgNews(String imgNews) {
        this.imgNews = imgNews;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getDateNews() {
        return dateNews;
    }

    public void setDateNews(String dateNews) {
        this.dateNews = dateNews;
    }

    public String getContentNews() {
        return contentNews;
    }

    public void setContentNews(String contentNews) {
        this.contentNews = contentNews;
    }

    public String getLinkNews() {
        return linkNews;
    }

    public void setLinkNews(String linkNews) {
        this.linkNews = linkNews;
    }
}
