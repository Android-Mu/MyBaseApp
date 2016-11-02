package com.mjj.baseapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 自定义ListView,避免测量
 * <p>
 * Created by Administrator on 2016/9/11.
 */

public class MylistView extends ListView {

    public MylistView(Context context) {
        super(context);
    }

    public MylistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MylistView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
