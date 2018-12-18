package com.mirkowu.funny.ui.gank

import android.net.Uri
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.ielse.imagewatcher.ImageWatcherHelper
import com.mirkowu.basetoolbar.BaseToolbar
import com.mirkowu.funny.R
import com.mirkowu.funny.api.gank.RandomImageBean
import com.mirkowu.funny.base.RefreshActivity
import com.mirkowu.funny.bean.GankImageBean
import com.mirkowu.funny.utils.ImageUtil
import com.softgarden.baselibrary.network.RxCallback
import com.softgarden.baselibrary.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_image.*


class ImageActivity : RefreshActivity<GankPresenter>(), BaseQuickAdapter.OnItemChildClickListener {


    lateinit var iwHelper: ImageWatcherHelper
    lateinit var mAdapter: ImageAdapter
    var position: Int = 0
    override fun getLayoutId(): Int {
        return R.layout.activity_image
    }

    override fun setToolbar(builder: BaseToolbar.Builder): BaseToolbar.Builder? {
        return builder.setTitle(R.string.app_name)
    }

    override fun initialize() {
        initRefreshLayout()
        initRecyclerView()
        mRecyclerView.layoutManager = GridLayoutManager(context, 3)
        mRecyclerView.addItemDecoration(SpaceItemDecoration(context).setSpace(10).setEdgeSpace(10))
        mAdapter = ImageAdapter()
        mRecyclerView.adapter = mAdapter
        mAdapter.onItemChildClickListener = this

        val view = layoutInflater.inflate(R.layout.layout_preview, null)
        val tvDownload = view.findViewById<TextView>(R.id.tvDownload)
        iwHelper = ImageWatcherHelper.with(this, SimpleLoader())
                .setOtherView(view)
                .addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                    }

                    override fun onPageSelected(position: Int) {
                        this@ImageActivity.position = position
                    }
                }) // SimpleLoader demo中有简单实现
        tvDownload.setOnClickListener { ImageUtil.loadImageToSDCard(activity, mAdapter.getItem(position)!!.url) }

        loadRandomImage()
        loadData()

    }

    private fun loadRandomImage() {
        presenter.randomImage.subscribe(object : RxCallback<RandomImageBean>() {
            override fun onSuccess(data: RandomImageBean?) {
                if (data!!.code == 200) {
                    ImageUtil.load(ivBackground, data.acgurl)
                }
            }
        })
    }

    override fun loadData() {
        presenter.getImages(mPage, PAGE_COUNT).subscribe(object : RxCallback<List<GankImageBean>>() {
            override fun onSuccess(data: List<GankImageBean>?) {
                setLoadMore(mAdapter, data)
            }
        })
    }

    override fun onRefresh() {
        super.onRefresh()
        loadRandomImage()
    }


    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val mapping = SparseArray<ImageView>() // 这个请自行理解，
        mapping.put(position, view as ImageView?)

        val dataList = ArrayList<Uri>()
        for (bean in mAdapter.data) {
            dataList.add(Uri.parse(bean.url))
        }

        iwHelper.show(view, mapping, dataList)//带共享元素
        //iwHelper.show(dataList, position)//不带共享元素
    }

    override fun onBackPressed() {
        // super.onBackPressed()
        if (!iwHelper.handleBackPressed()) {
            super.onBackPressed()
        }
    }
}
