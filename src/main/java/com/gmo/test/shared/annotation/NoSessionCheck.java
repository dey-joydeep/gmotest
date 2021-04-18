package com.gmo.test.shared.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * セッションチェック対象外とするための注釈
 * 
 * @author Joydeep Dey
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
/**
 * @author Joydeep Dey
 */
public @interface NoSessionCheck {

}
