package com.example.appquanlichitieu.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    private boolean enable;
    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.enable = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(this.enable){
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.enable){
            return super.onTouchEvent(ev);
        }
        return false;
    }
    public void setPadingEnable(boolean enable){
        this.enable = enable;
    }
}
