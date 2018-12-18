package com.mirkowu.funny.api.zhihu

import io.reactivex.Observable

/**
 * @author by DELL
 * @date on 2018/12/14
 * @describe
 */
interface ZhiHuService {
    val getLasetNews: Observable<String>
}
