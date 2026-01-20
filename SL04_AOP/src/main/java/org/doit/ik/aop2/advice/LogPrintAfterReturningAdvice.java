package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;
@Component("logPrintAfterReturningAdvice")
@Log4j
public class LogPrintAfterReturningAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(
				Object returnValue, 	// 핵심기능의 실행결과값
				Method method, 			// add()
				Object[] args, 			// 4, 2
				Object target			// 핵심기능 객체
				) throws Throwable {
		String methodName = method.getName();
		log.info("▣ ▣ ▣ ▣ "+methodName+"() : LogPrintAfterReturningAdvice 호출됨");
	}
	
}
