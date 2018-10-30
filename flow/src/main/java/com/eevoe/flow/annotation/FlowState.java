package com.eevoe.flow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FlowState {
}


//@Retention(CLASS) @Target(FIELD)
//public @interface BindView {
//    /** View ID to which the field will be bound. */
//    @IdRes int value();
//}
