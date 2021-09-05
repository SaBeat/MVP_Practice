package com.example.mvp.ui

import com.example.mvp.interfaces.MainContract
import com.example.mvp.model.Article

class MainPresenterImpl(private var mainView:MainContract.MainView, private var getNoticeIntractor:MainContract.GetNoticeIntractor)
    :MainContract.presenter, MainContract.GetNoticeIntractor.OnFinishedListener{

    override fun onDestroy() {
        mainView==null
    }

    override fun onRefreshButtonClick() {
        if(mainView!=null){
            mainView.showProgress()
        }
        getNoticeIntractor.getNoticeArrayList(this)
    }

    override fun requestDataFromServer() {
        getNoticeIntractor.getNoticeArrayList(this)
    }

    override fun onFinished(noticeArrayList: List<Article>) {
        if(mainView!=null){
            mainView.setDataToRecyclerView(noticeArrayList)
            mainView.hideProgress()
        }
    }

    override fun onFailure(t: Throwable?) {
        mainView.onResponseFailure(t)
        mainView.hideProgress()

    }
}