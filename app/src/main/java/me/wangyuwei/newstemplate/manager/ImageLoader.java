package me.wangyuwei.newstemplate.manager;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import me.wangyuwei.newstemplate.AppContext;

/**
 * 作者： 巴掌 on 16/7/8 20:46
 */
public class ImageLoader {

    private ImageLoader() {
    }

    public static void load(String url, ImageView imgView) {
        Glide.with(AppContext.getContext()).load(url).centerCrop().crossFade().into(imgView);
    }

}
