package org.doit.ik.di4.test;


import org.doit.ik.di4.RecordViewImpl4;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {
		String [] resourceLocations = {"classpath:org/doit/ik/di4/application-context.xml"};
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		RecordViewImpl4 rvi = ctx.getBean("rvi",RecordViewImpl4.class);
		
		rvi.input();
		rvi.output();
		
		System.out.println(" - END - ");
		// 64p 3.2 예제에서 사용할 클래스 구성
		// 인터페이스, 클래스 선언
		// org.doit.ik.di5
		// org.doit.ik.di5.test
		//				Ex06.java
		
	}
	
}
