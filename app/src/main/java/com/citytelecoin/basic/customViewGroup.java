package com.citytelecoin.basic;

/**
 * Created by carlosp on 11/8/17.
 */

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by carlosp on 11/2/17.
 */



public class customViewGroup extends ViewGroup {

    public customViewGroup(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.v("customViewGroup", "**********Intercepted");
        return true;




    }





}