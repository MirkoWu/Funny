package com.mirkowu.funny.api.gank;

import com.mirkowu.funny.api.HostUrl;
import com.mirkowu.funny.bean.GankImageBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

import java.util.List;

/**
 * @author by DELL
 * @date on 2018/12/14
 * @describe
 */
public interface GankService {
    /**
     * gank 图片列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GET(HostUrl.HOST_Gank_Image)
    Observable<GankBaseBean<List<GankImageBean>>> getImages(@Path("page") int page, @Path("pageSize") int pageSize);

    /**
     * 随机获取2次元图片
     *
     * @param url
     * @return
     */
    @GET
    Observable<RandomImageBean> getRandomImage(@Url String url);


}
