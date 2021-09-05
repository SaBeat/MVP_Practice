package com.example.mvp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.R
import com.example.mvp.adapter.NewsAdapter
import com.example.mvp.interfaces.MainContract
import com.example.mvp.model.Article
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView,NewsAdapter.OnItemClickListener {
    lateinit var newsAdapter: NewsAdapter
    lateinit var presenter: MainContract.presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()

        presenter= MainPresenterImpl(this, GetNoticeIntractorImpl())
        presenter.requestDataFromServer()
    }

    fun initRecycler(){
        rv_main.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
        }
    }

    override fun showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    override fun hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    override fun setDataToRecyclerView(noticeArrayList: List<Article>) {
        newsAdapter= NewsAdapter(this)
        rv_main.adapter=newsAdapter
        newsAdapter.differ.submitList(noticeArrayList)
    }

    override fun onResponseFailure(throwable: Throwable?) {
        Toast.makeText(this@MainActivity,
            "Something went wrong...Error message: " + throwable?.message,
            Toast.LENGTH_LONG).show();
    }

    override fun onItemClickListener(article: Article) {
        Toast.makeText(this@MainActivity, "Clicked ${article.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refresh_icon -> {
                presenter.onRefreshButtonClick()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}