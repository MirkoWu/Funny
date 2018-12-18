package com.mirkowu.funny.ui.gank;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.ielse.imagewatcher.ImageWatcher;

/**
 * @author by DELL
 * @date on 2018/12/17
 * @describe
 */
public class SimpleLoader implements ImageWatcher.Loader {
    @Override
    public void load(Context context, Uri uri, ImageWatcher.LoadCallback loadCallback) {
        Glide.with(context).load(uri)
                .into(new SimpleTarget<Drawable>() {

                    @Override

                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                        loadCallback.onResourceReady(resource);

                    }


                    @Override

                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                        loadCallback.onLoadFailed(errorDrawable);

                    }


                    @Override

                    public void onLoadStarted(@Nullable Drawable placeholder) {

                        loadCallback.onLoadStarted(placeholder);

                    }

                });
    }
}
