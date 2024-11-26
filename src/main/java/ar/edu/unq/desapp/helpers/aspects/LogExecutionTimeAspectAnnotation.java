package ar.edu.unq.desapp.helpers.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Slf4j
@Component
public class LogExecutionTimeAspectAnnotation {

    /* This logging aspect works with @LogExecutionTime annotation, it means
    *  we need to mark every method/service/request to be audited. -LM
    * */

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceedResult;

        long start = System.currentTimeMillis();
        try {
            proceedResult = joinPoint.proceed(); // Execute the targeted method
        } catch (Throwable ex) {
            log.error("Exception occurred during method execution: ", ex);
            throw ex;
        }
        long executionTime = System.currentTimeMillis() - start;

        String user = getCurrentUser();
        String operation = joinPoint.getSignature().getName();
        String parameters = getMethodParameters(joinPoint.getArgs());

        String logMessage = String.format(
                "User: %s | Operation: [%s] | Params: %s | Elapsed time: %dms",
                user, operation, parameters, executionTime);
        log.info(logMessage);

        return proceedResult;
    }

    private String getCurrentUser() {
         return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private String getMethodParameters(Object[] args) {
        return args != null ? java.util.Arrays.toString(args) : "[]";
    }
}
