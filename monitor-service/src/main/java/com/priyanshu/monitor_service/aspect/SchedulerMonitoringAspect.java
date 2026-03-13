package com.priyanshu.monitor_service.aspect;

import com.priyanshu.monitor_service.anotation.MonitoredScheduler;
import io.micrometer.core.instrument.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SchedulerMonitoringAspect {

    private final MeterRegistry meterRegistry;

    public SchedulerMonitoringAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Around("@annotation(monitoredScheduler)")
    public Object monitorScheduler(ProceedingJoinPoint joinPoint,
                                   MonitoredScheduler monitoredScheduler) throws Throwable {

        String jobName = monitoredScheduler.jobName();

        if (jobName.isEmpty()) {
            jobName = joinPoint.getSignature().getName();
        }

        Counter runCounter = Counter.builder("scheduler_job_runs_total")
                .tag("job_name", jobName)
                .register(meterRegistry);

        Counter failureCounter = Counter.builder("scheduler_job_failures_total")
                .tag("job_name", jobName)
                .register(meterRegistry);

        Timer timer = Timer.builder("scheduler_job_duration_seconds")
                .tag("job_name", jobName)
                .publishPercentileHistogram()
                .register(meterRegistry);

        Timer.Sample sample = Timer.start(meterRegistry);

        runCounter.increment();

        try {

            Object result = joinPoint.proceed();

            return result;

        } catch (Exception ex) {

            failureCounter.increment();

            Counter.builder("scheduler_job_errors_total")
                    .tag("job_name", jobName)
                    .tag("exception", ex.getClass().getSimpleName())
                    .register(meterRegistry)
                    .increment();

            throw ex;

        } finally {
            log.info("job run :",jobName);
            sample.stop(timer);

        }
    }
}