package org.doit.ik.di.test;

import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {
		// 스프링 - 객체 생성 + 조립(DI) --> new 객체 X
		// 1. java 설정파일 or ???-context.xml 설정파일
		// 2. 공장(ApplicationContext)에서 객체 생성 + 조립
		String [] resourceLocations = {"classpath:org/doit/ik/di/application-context.xml"};
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
//		RecordViewImpl rvi =(RecordViewImpl) ctx.getBean("rvi");
		RecordViewImpl rvi = ctx.getBean("rvi",RecordViewImpl.class);
		rvi.input();
		rvi.output();
		System.out.println(" - END - ");
	}

}
