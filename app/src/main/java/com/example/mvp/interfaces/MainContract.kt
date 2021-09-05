package com.example.mvp.interfaces

import com.example.mvp.model.Article

interface MainContract {

     interface presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

     interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(noticeArrayList: List<Article>)
        fun onResponseFailure(throwable: Throwable?)
    }

     interface GetNoticeIntractor {
        interface OnFinishedListener {
            fun onFinished(noticeArrayList: List<Article>)
            fun onFailure(t: Throwable?)
        }

        fun getNoticeArrayList(onFinishedListener: OnFinishedListener?)
    }

}