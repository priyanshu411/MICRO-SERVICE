package com.priyanshu.monitor_service.anotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MonitoredScheduler {

    String jobName() default "NA";
}