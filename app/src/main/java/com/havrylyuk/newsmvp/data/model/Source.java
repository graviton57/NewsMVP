package com.havrylyuk.newsmvp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class Source {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("category")
    private String category;
    @SerializedName("url")
    private String url;
    @SerializedName("urlsToLogos")
    private UrlsToLogos urlsToLogos;
    @SerializedName("sortBysAvailable")
    private List<String> sortBysAvailable;

    public Source() {
    }

    public Source(String id, String name, String description, UrlsToLogos urlsToLogos) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlsToLogos = urlsToLogos;
    }

    public Source(String id, String name, UrlsToLogos urlsToLogos) {
        this.id = id;
        this.name = name;
        this.urlsToLogos=urlsToLogos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UrlsToLogos getUrlsToLogos() {
        return urlsToLogos;
    }

    public void setUrlsToLogos(UrlsToLogos urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
    }

    public List<String> getSortBysAvailable() {
        return sortBysAvailable;
    }

    public void setSortBysAvailable(List<String> sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
    }

    /*sources": [
            -
    {
        "id": "abc-news-au",
            "name": "ABC News (AU)",
            "description": "Australia's most truste ... the latest business, sport, weather and more.",
            "url": "http://www.abc.net.au/news",
            "category": "general",
            "language": "en",
            "country": "au",
            -
                    "urlsToLogos": {
        "small": "http://i.newsapi.org/abc-news-au-s.png",
                "medium": "http://i.newsapi.org/abc-news-au-m.png",
                "large": "http://i.newsapi.org/abc-news-au-l.png"
    },
        -
                "sortBysAvailable": [
        "top"
]
    }
*/

}
