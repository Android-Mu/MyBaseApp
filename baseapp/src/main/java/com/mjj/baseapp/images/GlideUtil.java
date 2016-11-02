package com.mjj.baseapp.images;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mjj.baseapp.constant.Constant;
import com.mjj.baseapp.utils.StringUtil;

/**
 * @Description: 异步图片加载显示工具类
 */
public class GlideUtil {
    /**
     * 显示圆形图片
     *
     * @param context
     * @param url
     * @param placeholder
     */
    public static void showRoundImage(Context context, String url, ImageView imageView, int placeholder) {
        if (!showNullImage(context, url, imageView, placeholder)) return;
        Glide.with(context)
                .load(StringUtil.isImageUrl(url))
                .asBitmap()
//                .placeholder(placeholder)
                .error(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .transform(new TfRoundCorner(context))
                .into(imageView);
    }

    /**
     * 显示 圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param placeholder
     */
    public static void showFilletImage(Context context, String url, ImageView imageView, int placeholder) {
        if (!showNullImage(context, url, imageView, placeholder)) return;
        Glide.with(context)
                .load(StringUtil.isImageUrl(url))
                .asBitmap()
                .placeholder(placeholder)
                .error(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .transform(new TfRoundCornerAngle(context, Constant.IMAGE_DEFAULT_RATION))
                .into(imageView);
    }

    private static boolean showNullImage(Context context, String url, ImageView imageView, int placeholder) {
        if (context == null || imageView == null )
            return false;
        if (StringUtil.isEmpty(url)) {
            Glide.with(context)
                    .load(placeholder).into(imageView);
            return false;
        }
        return true;
    }

    //不变形
    public static void showNoneImage(Context context, String url, ImageView imageView, int placeholder, boolean isresize) {
        if (!showNullImage(context, url, imageView, placeholder)) return;
        Glide.with(context)
                .load(StringUtil.isImageUrl(url))
                .asBitmap()
                .placeholder(placeholder)
                .error(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


    //显示本地图片
    public static void showImageFile(Context context, String filePath, ImageView imageView, int placeholder) {
        if (!showNullImage(context, filePath, imageView, placeholder)) return;
        Glide.with(context)
                .load(filePath)
                .asBitmap()
                .placeholder(placeholder)
                .error(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(imageView);
    }

    //显示Gif动画
    public static void showGifImage(Activity activity, String url, ImageView image, int placeholder) {
        if (!showNullImage(activity, url, image, placeholder)) return;
        Glide.with(activity)
                .load(StringUtil.isImageUrl(url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholder)
                .error(placeholder)
                .into(image);
    }

}


