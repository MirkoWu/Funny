package com.mirkowu.funny.ui.gank;

import com.mirkowu.funny.api.HostUrl;
import com.mirkowu.funny.api.gank.GankClient;
import com.mirkowu.funny.api.gank.GankTransformer;
import com.mirkowu.funny.api.gank.RandomImageBean;
import com.mirkowu.funny.api.gank.RandomTransformer;
import com.mirkowu.funny.bean.GankImageBean;
import com.softgarden.baselibrary.base.BasePresenter;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by DELL
 * @date on 2018/12/14
 * @describe
 */
public class GankPresenter extends BasePresenter {

    public Observable<List<GankImageBean>> getImages(int page, int pageSize) {
        return GankClient.getGankService()
                .getImages(page, pageSize)
                .compose(new GankTransformer<>(this));
    }

    public Observable<RandomImageBean> getRandomImage() {
        return GankClient.getGankService()
                .getRandomImage(HostUrl.HOST_2ciyuan_random)
                .compose(new RandomTransformer<>(this));
    }

}
