package org.doit.ik.di2.test;


import org.doit.ik.di2.RecordViewImpl;
import org.doit.ik.di2.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
		// application-context.xml X => Config.java O
		AnnotationConfigApplicationContext ctx = new
				AnnotationConfigApplicationContext(Config.class);
		
		RecordViewImpl rvi = ctx.getBean("rvi",RecordViewImpl.class);
		
		rvi.input();
		rvi.output();
		
		System.out.println(" - END - ");
	}

}
