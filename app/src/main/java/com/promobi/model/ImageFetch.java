package com.promobi.model;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.promobi.R;

/**
 * Created by Rajesh Kumar on 16-05-2018.
 */
public class ImageFetch {
    public static ImageFetch imageFetch;

    public static ImageFetch getInstance(){
        if(null==imageFetch){
            imageFetch = new ImageFetch();
        }

        return imageFetch;
    }
    public  void loadImage(Context context,ImageView view, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .error(R.mipmap.ic_launcher)
                .into(view);
    }
}
