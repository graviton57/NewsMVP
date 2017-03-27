package com.havrylyuk.newsmvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class UrlsToLogos {

    @SerializedName("small")
    private String small;
    @SerializedName("medium")
    private String medium;
    @SerializedName("large")
    private String large;

    public UrlsToLogos() {
    }

    public UrlsToLogos(String small) {
        this.small = small;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
