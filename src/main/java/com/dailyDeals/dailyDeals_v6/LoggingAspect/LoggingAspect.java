package com.dailyDeals.dailyDeals_v6.LoggingAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.DailyDeals.dailyDeals_v5.controllers.*.*(..))")
    public void controllerMethods(){
    }
    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        CustomLogger.setInfoMessage("Before Method: " + joinPoint.getSignature().getName());
    }
    // After Returning advice
    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        CustomLogger.setInfoMessage("After Returning: " + joinPoint.getSignature().getName() + " | Result: " + result);
    }

    // After Throwing advice
    @AfterThrowing(pointcut = "controllerMethods()", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        // Log the exception message
        CustomLogger.setErrorMessage("Exception caught in AOP: " + exception.getMessage(), exception);
    }
    // Around advice
    @Around("serviceMethods()")
    public Object logAround(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
        CustomLogger.setInfoMessage("Around Advice: Before method - " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed(); // Proceed with the method execution
        CustomLogger.setInfoMessage("Around Advice: After method - " + joinPoint.getSignature().getName());
        return result;
    }
public static class ErrorResponse {
    private String errorCode;
    private String message;

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
}
