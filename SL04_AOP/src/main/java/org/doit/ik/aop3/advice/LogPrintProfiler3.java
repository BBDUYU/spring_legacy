package org.doit.ik.aop3.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class LogPrintProfiler3 {
	
	// after-returning advice 구현
	   public void afterReturning(JoinPoint joinpoint, Object result) {   
		      String methodName = joinpoint.getSignature().getName();
		      log.info("🎶🎶🎶 " + methodName +"() : LogPrintProfiler3.afterReturning() 호출됨..." + result );
		   }
	
	// 217p Before Advice 구현
	public void before(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
	    log.info(">>> " + methodName +"() : LogPrintProfiler3.before() 호출됨...");
	}
	
	
	// 222p
	// Around Advice 구현 X
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		StopWatch sw = new StopWatch();
		sw.start();

		String methodName = joinPoint.getSignature().getName(); // add, sub, mul, div
		log.info(">>> " + methodName + "() start.");

		Object result = joinPoint.proceed(); // target

		log.info(">>> " + methodName + "() stop.");
		sw.stop();

		log.info(">>> " + methodName + "() 처리 시간 : " + sw.getTotalTimeMillis()+"ms");

		return result;
	}
}
