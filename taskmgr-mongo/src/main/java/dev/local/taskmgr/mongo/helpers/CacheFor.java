package dev.local.taskmgr.mongo.helpers;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface CacheFor {
    long amount();
    TimeUnit unit() default TimeUnit.SECONDS;
}
