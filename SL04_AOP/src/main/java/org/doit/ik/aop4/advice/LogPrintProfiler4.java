package org.doit.ik.aop4.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
@Aspect
//<aop:aspect id="sampleAspect" ref="logPrintProfiler3">
public class LogPrintProfiler4 {

	//<aop:pointcut expression="execution(* org.doit.ik.aop3..*.*(*,*))" id="calcPointcut"/>

	@Pointcut("execution(* org.doit.ik.aop4..*.*(*,*))")
	private void calcPointcut() {}

	// after-returning advice 구현
	//<aop:after-returning method="afterReturning" pointcut-ref="calcPointcut" returning="result"/>
	@AfterReturning(pointcut = "calcPointcut()",returning = "result")
	public void afterReturning(JoinPoint joinpoint, Object result) {   
		String methodName = joinpoint.getSignature().getName();
		log.info("🎶🎶🎶 " + methodName +"() : LogPrintProfiler4.afterReturning() 호출됨..." + result );
	}

	// 217p Before Advice 구현
	//<aop:before method="before" pointcut-ref="calcPointcut"/>
	@Before("calcPointcut()")
	public void before(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		log.info(">>> " + methodName +"() : LogPrintProfiler4.before() 호출됨...");
	}


	// 222p
	// Around Advice 구현 X
	//<aop:around method="trace" pointcut-ref="calcPointcut"/>
	@Around("calcPointcut()")
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
