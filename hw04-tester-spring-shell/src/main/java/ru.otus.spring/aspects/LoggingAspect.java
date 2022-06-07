package ru.otus.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

//    @Before("@target(ru.otus.spring.annotations.Loggable)")
    @Before("@annotation(ru.otus.spring.annotations.Loggable)")
    public void beforeLoggingAdvice(JoinPoint joinPoint) {

        var logger = LoggerFactory.getLogger(joinPoint.getClass());

        logger.warn(
                "Вызов метода: {} {}",
                joinPoint.getSignature(),
                Arrays.toString(joinPoint.getArgs()));
    }

}
