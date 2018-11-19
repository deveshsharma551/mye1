package com.platform.makeyourevent.loggingAspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Order(value=2)
public class AspectLogger {
	
	private static final Logger logger = LoggerFactory.getLogger(AspectLogger.class);

	
	@Pointcut("execution(* com.platform.makeyourevent.dataaccessDao..*(..))")
	public void allMethods()
	{
		
	}
	
	@Pointcut("within(@com.platform.makeyourevent.loggingAspect.NotLoggable *)")
	public void notLoggable()
	{
		
	}
	
	@Around("allMethods() && !notLoggable()")
	public Object logEntryExit(ProceedingJoinPoint jp) throws Throwable
	{
		System.out.println("inside aspect");
		logger.debug("debug level check");
		logger.error("error level check");
		Object retVal = null;
		StopWatch sw = new StopWatch();
		sw.start();
		System.out.println(sw.getTotalTimeSeconds());
	    retVal=jp.proceed();
	    System.out.println(sw.getTotalTimeMillis());
	    sw.stop();
		return retVal;
	    
	}
	
	
}
