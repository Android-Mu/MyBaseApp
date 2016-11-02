package com.mjj.baseapp.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mjj.baseapp.base.activity.BaseActivity;
import com.mjj.baseapp.utils.SharesUtils;

/**
 * Fragment封装
 *
 * Created by Mjj on 2016/7/4 0004.
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    protected BaseActivity mActivity; // 宿主Activity
    private SparseArray<View> mViews = new SparseArray<View>();
    protected View frView;
    public SharesUtils sharesUtils;

    // 初始化视图方法
    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取布局文件ID
    protected abstract int getLayoutId();

    public View getFrView() {
        return frView;
    }

    public void setFrView(View frView) {
        this.frView = frView;
    }

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
        this.sharesUtils = new SharesUtils(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        frView = inflater.inflate(getLayoutId(), container, false);
        initView(frView, savedInstanceState);
        setFrView(frView);
        return frView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = frView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    // 网络请求方法
    protected void httpData() {
    }

}
