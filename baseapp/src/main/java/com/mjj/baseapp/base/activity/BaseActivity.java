package com.mjj.baseapp.base.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mjj.baseapp.R;
import com.mjj.baseapp.base.dialog.DialogControl;
import com.mjj.baseapp.utils.ActivityUtil;
import com.mjj.baseapp.utils.DialogHelper;
import com.mjj.baseapp.utils.SharesUtils;
import com.mjj.baseapp.utils.StringUtil;
import com.mjj.baseapp.utils.SystemBarTintManager;
import com.mjj.baseapp.utils.ToastUtil;

/**
 * 文件描述：所有Activity基类
 * <p/>
 * Created by Mjj on 2016/8/12 0012.
 */
public abstract class BaseActivity extends AppCompatActivity implements DialogControl {

    private static final String TAG = BaseActivity.class.getSimpleName();
    public SharesUtils sharesUtils;
    public Context mContext;
    private SparseArray<View> mViews = new SparseArray<View>();

    // 加载界面出现Dialog
    private boolean _isVisible;
    private ProgressDialog _waitDialog;

    // 左边 中间 右边文字
    private TextView mLeftTitleTv, mCenterTitleTv, mRightTitleTv;

    //布局文件ID
    protected abstract int getContentViewId();

    //布局中装载Fragment的ID
    protected abstract int getFragmentContentId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 设置整个app竖屏模式
        ActivityUtil.getInstance().addActivity(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);//通知栏所需颜色
        }
        mContext = this;
        sharesUtils = new SharesUtils(mContext);
        _isVisible = true;
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        initView();
    }

    // 替代findViewById方法
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    // 设置状态栏透明状态
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 获取界面跳转所传递的Intent
     *
     * @param intent
     */
    protected void handleIntent(Intent intent) {
    }

    public void initView() {
        setContentView(getContentViewId());
//        mLeftTitleTv = getView(R.id.tv_left_title);
//        mCenterTitleTv = getView(R.id.tv_center_title);
//        mRightTitleTv = getView(R.id.tv_right_title);
//        mLeftTitleTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
        httpData();
    }

    // 网络请求方法
    protected void httpData() {
    }

    /**
     * 弹吐司方法     这样设置方便每个类调用，不用多次点击多次弹出
     */
    public void showToast(String text) {
        ToastUtil.showToast(getApplicationContext(), text);
    }

    /**
     * 弹吐司方法
     * resId 资源id
     */
    public void showToast(int resId) {
        ToastUtil.showToast(getApplicationContext(), resId);
    }

    // 打印Log方法
    public void LogE(String msg) {
        if (!StringUtil.isEmpty(msg)) {
            Log.e(TAG, msg);
        }
    }

//    public TextView getmRightTitleTv() {
//        return mRightTitleTv;
//    }

//    public TextView getmCenterTitleTv() {
//        return mCenterTitleTv;
//    }

//    public TextView getmLeftTitleTv() {
//        return mLeftTitleTv;
//    }

    @Override
    public ProgressDialog showWaitDialog() {
        return showWaitDialog("Loading...");
    }

    @Override
    public ProgressDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public ProgressDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelper.getWaitDialog(this, message);
            }
            if (_waitDialog != null) {
                _waitDialog.setMessage(message);
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }

    @Override
    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
