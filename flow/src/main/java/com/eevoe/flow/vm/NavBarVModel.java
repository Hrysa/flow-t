package com.eevoe.flow.vm;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.res.ColorStateList;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.eevoe.flow.BR;
import com.eevoe.flow.FlowActivity;
import com.eevoe.flow.R;

public class NavBarVModel extends AndroidViewModel {
    final public ObservableField<Drawable> leftIcon = new ObservableField<>();
    final public ObservableField<Drawable> rightIcon = new ObservableField<>();
    final public ObservableField<String> title = new ObservableField<>();
    final public ObservableField<String> leftTitle = new ObservableField<>();
    final public ObservableField<String> rightTitle = new ObservableField<>();
    final public ObservableField<Integer> titleGravity = new ObservableField<>(Gravity.LEFT);

    public View.OnClickListener leftOnClick;

    public View.OnClickListener rightOnClick;

//    @BindingAdapter("onTouch")
//    public void setTouchListener(View view, boolean value){
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                // Check if the button is PRESSED
//                if (event.getAction() == MotionEvent.ACTION_DOWN){
//                    //do some thing
//                    Log.wtf("?????", "onTouch: ????", );
//                }// Check if the button is RELEASED
//                else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    //do some thing
//                }
//                return false;
//            }
//        });
//    }

//    View.OnTouchListener onTouchListener;

    public void init(final Activity activity) {
        setLeftIcon(activity.getResources().getDrawable(R.drawable.back));
        setRightIcon(activity.getResources().getDrawable(R.drawable.more));
        leftOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        };
    }

    public NavBarVModel(@NonNull Application application) {
        super(application);
    }

    public void setLeftIcon(Drawable dw) {
        leftIcon.set(dw);
    }

    public void setRightIcon(Drawable dw) {
        rightIcon.set(dw);
    }

    public void setTitle(String s) {
        title.set(s);
    }

    public void setLeftTitle(String s) {
        leftTitle.set(s);
    }
}


//    imgShowLoginPwd.setOnTouchListener(new View.OnTouchListener() {
//@Override
//public boolean onTouch(View v, MotionEvent event) {
//        switch (event.getAction()) {
//        case MotionEvent.ACTION_UP://松开事件发生后执行代码的区域
//        Log.e(TAG,"密码不可见");
//        imgShowLoginPwd.setImageResource(R.drawable.icon_pwd_hind);
//        edtLoginPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
//        break;
//        case MotionEvent.ACTION_DOWN://按住事件发生后执行代码的区域
//        Log.e(TAG,"密码可见");
//        imgShowLoginPwd.setImageResource(R.drawable.icon_pwd_show);
//        edtLoginPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//        break;
//default:
//        break;
//        }
//        return true;
//        }
//
//        ---------------------
//        作者：公子不歌
//        来源：CSDN
//        原文：https://blog.csdn.net/z14581/article/details/72876668
//        版权声明：本文为博主原创文章，转载请附上博文链接！