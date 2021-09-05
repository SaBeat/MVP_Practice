package com.example.mvp.ui

import com.example.mvp.api.RetrofitClient
import com.example.mvp.interfaces.MainContract
import com.example.mvp.model.NewsResponse
import com.example.mvp.utils.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetNoticeIntractorImpl: MainContract.GetNoticeIntractor {
    override fun getNoticeArrayList(onFinishedListener: MainContract.GetNoticeIntractor.OnFinishedListener?) {

        val call=RetrofitClient.getApi.
                 getHeadlines(Util.API_KEY,Util.country,Util.category)

        call.enqueue(object:Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                onFinishedListener?.onFinished(response.body()!!.articles)
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                onFinishedListener?.onFailure(t)
            }

        })
    }
}