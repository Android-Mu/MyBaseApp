package com.mjj.baseapp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mjj.baseapp.utils.bean.BeDownFile;

import java.io.File;

/**
 * 自定义android Intent类，可用于获取打开以下文件的intent
 * <p/>
 * PDF,PPT,WORD,EXCEL,CHM,HTML,TEXT,AUDIO,VIDEO
 * <p/>
 * Created by Administrator on 2016/4/9.
 */

public class OpFileUtil {

    public static void openFile(Context context, BeDownFile file) {
        if ("".equals(file.getLocaUrl()) || file.getLocaUrl() == null) {
            ToastUtil.showToast(context, "解析异常");
            return;
        }
        Intent intent = null;
        String path = file.getLocaUrl();
        switch (file.getFileType()) {
            // 注：file_word等是静态常量类中的一些文件格式定义
            case 1://Constant.file_word:
                intent = getWordFileIntent(path);
                break;
//            case Constant.file_ppt:
//                intent = getPptFileIntent(path);
//                break;
//            case Constant.file_excel:
//                intent = getExcelFileIntent(path);
//                break;
//            case Constant.file_pdf:
//                intent = getPdfFileIntent(path);
//                break;
//            case Constant.file_mp3:
//                intent = getAudioFileIntent(path);
//                break;
//            case Constant.file_video:
//                intent = getVideoFileIntent(path);
//                break;
//            case Constant.file_img:
//                intent = getImageFileIntent(path);
//                break;
//            case Constant.file_wps:
//                intent = getWpsFileIntent(path);
//                break;
//            default:
//                ToastUtil.showToast(context, R.string.toast_open_file_tools);
//                break;
        }
    }
//
//    //android获取一个用于打开HTML文件的intent
//    public static Intent getHtmlFileIntent(String param) {
//        Uri uri = Uri.parse(param).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param).build();
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.setDataAndType(uri, "text/html");
//        return intent;
//    }

//android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    //android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    //android获取一个用于打开音频文件的intent
    public static Intent getAudioFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    //android获取一个用于打开视频文件的intent
    public static Intent getVideoFileIntent(String param) {
        Intent it = new Intent();
        it.setAction(Intent.ACTION_VIEW);
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri uri = Uri.parse(param);//此url就是流媒体文件的下载地址
        it.setDataAndType(uri, "video/*");//type的值是 "video/*"或者 "audio/*"
        return it;
    }
//
//    public static Intent getVideoNetFileIntent(String url) {
//        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
//        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
//        Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
//        mediaIntent.setDataAndType(Uri.parse(url), mimeType);
//        return mediaIntent;
//    }
/*

    //android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }*/


    //android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(String param) {
        Intent intent = null;
        try {
            intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "application/msword");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intent;
    }

    //android获取一个用于打开Word文件的intent
    public static Intent getWpsFileIntent(String param) {
        Intent intent = null;
        try {
            intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(new File(param));
            intent.setDataAndType(uri, "application/vnd.ms-works");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intent;
    }

    //android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.parse(param);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    //android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }
}