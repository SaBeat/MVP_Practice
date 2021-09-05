package com.example.mvp.api

import com.example.mvp.model.NewsResponse
import com.example.mvp.utils.Util
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(Util.END_POINT)
    fun getHeadlines(
        @Query("apiKey") apiKey:String,
        @Query("country") country:String,
        @Query("category") category:String
    ): Call<NewsResponse>
}