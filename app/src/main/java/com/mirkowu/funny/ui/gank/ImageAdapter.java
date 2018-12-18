package com.mirkowu.funny.ui.gank;

import com.mirkowu.funny.R;
import com.mirkowu.funny.bean.GankImageBean;
import com.mirkowu.funny.utils.ImageUtil;
import com.softgarden.baselibrary.base.BaseRVAdapter;
import com.softgarden.baselibrary.base.BaseRVHolder;

/**
 * @author by DELL
 * @date on 2018/12/14
 * @describe
 */
public class ImageAdapter extends BaseRVAdapter<GankImageBean> {
    public ImageAdapter() {
        super(R.layout.item_images);
    }

    @Override
    public void onBindVH(BaseRVHolder holder, GankImageBean data, int position) {
        ImageUtil.load(holder.getView(R.id.ivImage), data.getUrl());
        holder.addOnClickListener(R.id.ivImage);

//        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
//            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) lp;
//            flexboxLp.setFlexGrow(1.0f);
//        }
    }
}
