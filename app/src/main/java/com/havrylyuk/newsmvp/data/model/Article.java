package com.havrylyuk.newsmvp.data.model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */
public class Article {

    @SerializedName("author")
    private String author;
    @SerializedName("urlToImage")
    private String imageUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String decr;
    @SerializedName("url")
    private String url;
    @SerializedName("publishedAt")
    private String publishedAt;

    public Article() {
    }

    public Article(String title, String decr , String imageUrl, String author, String url) {
        this.title=title;
        this.decr=decr;
        this.imageUrl=imageUrl;
        this.url=url;
        this.author=author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecr() {
        return decr;
    }

    public void setDecr(String decr) {
        this.decr = decr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /*{
        "author": "Rachel Kaser",
            "title": "Kim Kardashian’s new bot wants to solve puzzles with you",
            "description": "If you’ve ever wanted to  …",
            "url": "https://thenextweb.com/apps/2017/03/23/kim-kardashians-new-bot-wants-solve-puzzles/",
            "urlToImage": "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/03/KKbot-Header.jpg",
            "publishedAt": "2017-03-23T20:19:07Z"
    },*/
}
