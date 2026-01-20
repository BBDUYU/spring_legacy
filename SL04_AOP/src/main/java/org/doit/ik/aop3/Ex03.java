package org.doit.ik.aop3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop3/application-context.xml");
		Calculator3 calc = ctx.getBean("calc3",Calculator3.class);
		System.out.println(calc.add(4, 2));
		
		System.out.println(" END ");
		
		
	}
}
