package com.example.mvp

import com.example.mvp.api.RetrofitClient
import com.example.mvp.interfaces.MainContract
import com.example.mvp.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetNoticeIntractorImpl: MainContract.GetNoticeIntractor {
    override fun getNoticeArrayList(onFinishedListener: MainContract.GetNoticeIntractor.OnFinishedListener?) {

        val call=RetrofitClient.getApi.
                 getHeadlines("964eeec67d2848e287bdbdc05bf00d35","us","business")

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