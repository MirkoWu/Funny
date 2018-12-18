package com.mirkowu.funny.api.gank;


import com.softgarden.baselibrary.base.IBaseDisplay;
import com.softgarden.baselibrary.network.ApiException;
import com.softgarden.baselibrary.network.RxJava2NullException;
import com.softgarden.baselibrary.utils.NetworkUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * RxJava2 转换器 用于网络加载数据 已实现功能有：
 * <p>
 * 1.检测有无网络
 * 2.加载网络时显示加载框 结束是隐藏
 * 3.控制RxJava生命周期，防止内存泄漏
 */
public class RandomTransformer<T> implements ObservableTransformer<T, T> {
    private IBaseDisplay mView;
    private boolean showLoading;

    public RandomTransformer(IBaseDisplay mView) {
        this(mView, true);
    }

    public RandomTransformer(IBaseDisplay mView, boolean showLoading) {
        if (mView == null) throw new RuntimeException("IBaseDisplay is not NULL");
        this.mView = mView;
        this.showLoading = showLoading;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    //请求前检测网络
                    if (!NetworkUtil.isConnected(mView.getContext())) {
                        NetworkUtil.showNoNetWorkDialog(mView.getContext());
                        mView.onRequestFinish();
                        disposable.dispose();
                    } else {
                        if (showLoading) mView.showProgressDialog();
                    }
                })
                .doFinally(() -> {
                    if (showLoading) mView.hideProgressDialog();
                    mView.onRequestFinish();
                })
                .doOnError(throwable -> {
                    //RxJava2NullException 交给RxCallback处理
                    if (!(throwable instanceof RxJava2NullException)) {
                        mView.showError(throwable);
                    }
                })
                .compose(mView.bindToLifecycle());

    }
    /**
     * 过滤异常
     *
     * @return
     */
    public Function<? super GankBaseBean<T>, GankBaseBean<T>> filterData() {
        return (Function<GankBaseBean<T>, GankBaseBean<T>>) baseBean -> {
            if (!baseBean.error) {
                return baseBean;
            } else {
//                if (baseBean.status == -1) {
//                    mView.showReLoginDialog();
//                }
                throw new ApiException(-1, "error!");
            }
        };
    }

}
