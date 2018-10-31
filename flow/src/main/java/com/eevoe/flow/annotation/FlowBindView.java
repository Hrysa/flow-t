package com.eevoe.flow.annotation;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FlowBindView {
    @LayoutRes int view() default 0;
    boolean hideNav() default false;
    String title() default "";
    @StringRes int titleId() default 0;
}
