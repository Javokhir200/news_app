package uz.lee.news_app.aop;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermissions {
    String permission();
}
