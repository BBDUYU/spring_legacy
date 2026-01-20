package org.doit.ik.aop2.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

// 스프링 AOP : 프록시 기반, 메서드 호출
@Component("logPrintAroundAdvice")
@Log4j
public class LogPrintAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {

		StopWatch sw = new StopWatch();
		sw.start();

		String methodName = method.getMethod().getName(); // add, sub, mul, div
		log.info("> " + methodName + "() start.");

		Object result = method.proceed(); // target

		log.info("> " + methodName + "() stop.");
		sw.stop();

		log.info("> " + methodName + "() 처리 시간 : " + sw.getTotalTimeMillis()+"ms");

		return result;
	}

}
