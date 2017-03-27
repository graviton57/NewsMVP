package com.havrylyuk.newsmvp.data.source.remote;



import com.havrylyuk.newsmvp.data.model.ArticleResponse;
import com.havrylyuk.newsmvp.data.model.SourceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public interface ApiService {

    @GET("articles")
    Call<ArticleResponse> getArticle(
            @Query("apiKey") String apiKey,
            @Query("source") String sources,
            @Query("sortBy") String sortBy) ; //top, latest, popular

    //Possible category options: business, entertainment, gaming, general, music, science-and-nature, sport, technology.
    //Possible language options: en, de, fr.
    //Possible country options: au, de, gb, in, it, us.
    @GET("sources")
    Call<SourceResponse> getSource(
            @Query("language") String countryCode);

}
