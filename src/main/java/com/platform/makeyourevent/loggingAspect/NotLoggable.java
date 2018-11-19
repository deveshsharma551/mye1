package com.platform.makeyourevent.loggingAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // will be applicable on classes and if ElementType.Method then will be applicable on methods
public @interface NotLoggable {

}
