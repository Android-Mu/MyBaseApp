package com.mjj.baseapp.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具
 */
public class StringUtil {

    /**
     * 判断给定字符串是否空白串, 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)) {
            return true;
        }
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\n' && c != '\r') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断TextView是否有值
     */
    public static boolean isEmptyTv(TextView tv) {
        return isEmpty(tv.getText().toString().trim());
    }

    /**
     * 字符串拆分
     *
     * @param string   需要拆分的字符串
     * @param splitter 分割字符集"@"
     */
    public static List<String> getStringList(String string, String splitter) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(string, splitter);
        while (st.hasMoreElements()) {
            list.add(st.nextToken());
        }
        return list;
    }

    /**
     * 截取一个字符串中最后一个splitter之前的所有字符串
     *
     * @param splitter
     */
    public static String getStrBeforeSplitter(String oriStr, String splitter) {
        if (!isEmpty(oriStr)) {
            return oriStr.substring(0, oriStr.lastIndexOf(splitter));
        }
        return "";
    }

    /**
     * 截取一个字符串中最后一个aplitter之后的所有字符串
     *
     * @param oriStr   需要截取的字符串
     * @param splitter 指定的字符
     * @return 截取后的新串
     */
    public static String getStrLeSplitter(String oriStr, String splitter) {
        if (!isEmpty(oriStr)) {
            return oriStr.substring(oriStr.lastIndexOf(splitter), oriStr.length());
        }
        return "";
    }

    /**
     * 删除String最后一个字符
     *
     * @param text
     * @return
     */
    public static String trimLast(String text) {
        String result = "";
        result = text.substring(0, text.length() - 1);
        return result;
    }

    /**
     * 两个文本值比较
     *
     * @param one
     * @param two
     * @return 如果有null 则直接返回false，成功返回true
     */
    public static boolean sameStr(String one, String two) {
        return one == null || two == null ? false : one.equals(two);
    }

    private static final String moblie1 = "^(0\\d{2,3}\\d{7,8})|(((13[0-9])|(15([0-3]|[5-9]))|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8})$";
    private static final String moblie2 = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8})$";

    /**
     * 验证手机号是否匹配
     *
     * @param phone
     * @param isPhone：是否支持固话验证
     * @return ：true 手机号是正确的，false 手机号不正确
     */
    public static boolean mobilePhone(String phone, boolean isPhone) {
        String regStr;
        if (isPhone == true) {
            regStr = moblie1;
        } else {
            regStr = moblie2;
        }
        return phone == null || phone.trim().equals("") ? false
                : phone.replaceAll(regStr, "").equals("");
    }

    /**
     * 手机验证
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(14[57])|(17[0])|(17[7])|(18[0,0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 验证密码是否有中文
     *
     * @param pwd
     * @return true 有  false 没有
     */
    public static boolean hasChinese(String pwd) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(pwd);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 密码长度验证
     *
     * @param pwd
     * @return
     */
    public static boolean isPwd(String pwd) {
        int length = pwd.length();
        boolean falg = false;
        if (length >= 6 && length <= 18) {
            falg = true;
        } else {
            falg = false;
        }
        return falg;
    }

    /**
     * 纯数字
     *
     * @param str
     * @return
     */

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 纯字母
     *
     * @param
     * @return
     */
    public static boolean isChar(String data) {
        boolean flag = false;
        for (int i = data.length(); --i >= 0; ) {
            char c = data.charAt(i);
            if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 数字+字母
     */
    public static boolean ispsd(String psd) {
        Pattern p = Pattern
                .compile("^([A-Za-z]|[0-9])+$");
        Matcher m = p.matcher(psd);

        return m.matches();
    }

    /**
     * 验证邮政编码
     *
     * @param post
     * @return
     */
    public static boolean checkPost(String post) {
        if (post.matches("[1-9]\\d{5}(?!\\d)")) {
            return true;
        } else {
            return false;
        }
    }

    private static final String url = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";

    /**
     * 判断是否属于标准的网址
     *
     * @return
     */
    public static boolean isStandardHttpUrl(String httpUrl) {
        return isEmpty(httpUrl) ? false : httpUrl.replaceAll(url, "").equals("");
    }

    public static final String imageType = ".*(.jpg|.jpeg|.png|.gif|.bmp)$";
    public static final String videoType = ".*(.avi|.mp4|.mpeg|.mpg|.mov|.flv|.swf|.rmvb)$";
    public static final String audioType = ".*(.mp3|.wma)$";
    public static final String pdfType = ".*(.pdf)$";
    public static final String wordType = ".*(.doc|.docx)$";
    public static final String pptType = ".*(.ppt|.pptx|.dps)$";
    public static final String excleType = ".*(.xls|.xlsx)$";
    public static final String wpsType = ".*(.wps)$";
    public static final String txtType = ".*(.txt)$";

    /**
     * 判断文件是否为指定的格式
     */
    public static boolean isFileType(String file, String fileType) {
        if (file == null)
            return false;
        return Pattern.matches(fileType, file.toLowerCase());
    }

    public static String isImageUrl(String imageUrl) {
        return imageUrl.replaceAll("#[0-9#]+$", "");
    }

    /**
     * 提取一个字符串中的所有数字
     *
     * @param dayofmonth
     */
    public static List<String> getNumbersFromStr(String dayofmonth) {
        List<String> digitList = new ArrayList<String>();
        String[] nums = dayofmonth.split("\\D+");
        for (String num : nums) {
            digitList.add(num);
        }
        return digitList;
    }

    /**
     * 周历中显示日期
     *
     * @param start
     * @param end
     * @param month
     * @return
     */
    public static List<Integer> getDayOfMonth(int start, int end, int month) {
        List<Integer> days = new ArrayList<Integer>();
        if (start == end) {
            return days;
        }

        if (start - end < 0) {
            for (int i = start; i < end + 1; i++) {
                days.add(i);
            }
            return days;
        } else {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                //31天
                for (int i = start; i <= end + 31; i++) {
                    int j = i;
                    if (i > 31) {
                        j = i - 31;
                    }
                    days.add(j);
                }
                return days;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                //30天
                for (int i = start; i <= end + 30; i++) {
                    int j = i;
                    if (i > 30) {
                        j = i - 30;
                    }
                    days.add(j);
                }
                return days;
            } else {
                //2月
                if (end + 29 - start == 6) {
                    //29天
                    for (int i = start; i <= end + 29; i++) {
                        int j = i;
                        if (i > 29) {
                            j = i - 29;
                        }
                        days.add(j);
                    }
                    return days;
                } else if (end + 28 - start == 6) {
                    //28天
                    for (int i = start; i <= end + 28; i++) {
                        int j = i;
                        if (i > 28) {
                            j = i - 28;
                        }
                        days.add(j);
                    }
                    return days;
                }
            }
        }
        return days;
    }

    private static final String email = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (isEmpty(email))
            return false;
        Pattern p = Pattern.compile(StringUtil.email);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * URL检查<br>
     * <br>
     *
     * @param pInput 要检查的字符串<br>
     * @return boolean   返回检查结果<br>
     */
    public static boolean isUrl(String pInput) {
        if (pInput == null) {
            return false;
        }
        Pattern p = Pattern.compile(url);
        Matcher matcher = p.matcher(pInput);
        return matcher.matches();
    }

    // 保存图片
    public static String saveBitmap(Context context, String path) {
        String compressdPicPath = "";
      /*  如果不压缩直接从path获取bitmap，这个bitmap会很大，下面在压缩文件到100kb时，会循环很多次，
        而且会因为迟迟达不到100k，options一直在递减为负数，直接报错
        即使原图不是太大，options不会递减为负数，也会循环多次，UI会卡顿，所以不推荐不经过压缩，直接获取到bitmap
        Bitmap bitmap=BitmapFactory.decodeFile(path);*/

//        建议先将图片压缩到控件所显示的尺寸大小后，再压缩图片质量
//        首先得到手机屏幕的高宽，根据此来压缩图片，当然想要获取跟精确的控件显示的高宽（更加节约内存）,可以使用getImageViewSize();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;  // 屏幕宽度（像素）
        int height = displayMetrics.heightPixels;  // 屏幕高度（像素）
//        获取按照屏幕高宽压缩比压缩后的bitmap
        Bitmap bitmap = decodeSampledBitmapFromPath(path, width, height);

        String oldName = path.substring(path.lastIndexOf("/"), path.lastIndexOf("."));
        String name = oldName + "_compress.jpg";//★很奇怪oldName之前不能拼接字符串，只能拼接在后面，否则图片保存失败
        String saveDir = Environment.getExternalStorageDirectory()
                + "/compress";
        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(saveDir, name);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // options表示 如果不压缩是100，表示压缩率为0。如果是70，就表示压缩率是70，表示压缩30%;
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        while (baos.toByteArray().length / 1024 > 500) {
            // 循环判断如果压缩后图片是否大于500kb继续压缩
            baos.reset();
            options -= 10;
            if (options < 11) {//为了防止图片大小一直达不到500kb，options一直在递减，当options<0时，下面的方法会报错
                // 也就是说即使达不到500kb，也就压缩到10了
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                break;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(baos.toByteArray());
            out.flush();
            out.close();
            compressdPicPath = file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compressdPicPath;
    }

    private static Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {
//      获取图片的宽和高，并不把他加载到内存当中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = caculateInSampleSize(options, width, height);
//      使用获取到的inSampleSize再次解析图片(此时options里已经含有压缩比 options.inSampleSize，再次解析会得到压缩后的图片，不会oom了 )
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /**
     * 根据需求的宽和高以及图片实际的宽和高计算SampleSize
     *
     * @param options
     * @param reqWidth  要显示的imageview的宽度
     * @param reqHeight 要显示的imageview的高度
     * @return
     * @compressExpand 这个值是为了像预览图片这样的需求，他要比所要显示的imageview高宽要大一点，放大才能清晰
     */
    private static int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width >= reqWidth || height >= reqHeight) {
            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(width * 1.0f / reqHeight);
            inSampleSize = Math.max(widthRadio, heightRadio);
        }
        return inSampleSize;
    }

    // 根据uri获取图片地址
    public static String getPhotoPathFromContentUri(Context context, Uri uri) {
        String photoPath = "";
        if (context == null || uri == null) {
            return photoPath;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if (isExternalStorageDocument(uri)) {
                String[] split = docId.split(":");
                if (split.length >= 2) {
                    String type = split[0];
                    if ("primary".equalsIgnoreCase(type)) {
                        photoPath = Environment.getExternalStorageDirectory() + "/" + split[1];
                    }
                }
            } else if (isDownloadsDocument(uri)) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                photoPath = getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                String[] split = docId.split(":");
                if (split.length >= 2) {
                    String type = split[0];
                    Uri contentUris = null;
                    if ("image".equals(type)) {
                        contentUris = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUris = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUris = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    String selection = MediaStore.Images.Media._ID + "=?";
                    String[] selectionArgs = new String[]{split[1]};
                    photoPath = getDataColumn(context, contentUris, selection, selectionArgs);
                }
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            photoPath = uri.getPath();
        } else {
            photoPath = getDataColumn(context, uri, null, null);
        }
        return photoPath;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return null;
    }

    /**
     * 计算金额
     *
     * @param argStr
     * @return
     */
    public static String getFloatDotStr(String argStr) {
        float arg = Float.valueOf(argStr);
        DecimalFormat fnum = new DecimalFormat("##0.00");
        return fnum.format(arg);
    }


}
