package org.doit.ik.di2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//application-context.xml 파일과 동일한 역할을 하는 자바 클래스
@Configuration
public class Config {
	 // 1. RecordImpl record = new RecordImpl(); 
	 // <bean id="record" class="org.doit.ik.di.RecordImpl"></bean>
	@Bean
	public RecordImpl record() {
		return new RecordImpl();
	}
	
//	<bean id="rvi" class="org.doit.ik.di.RecordViewImpl">
//		<property name="record" ref="record"></property>
//	</bean>
	@Bean(name = "rvi")
	public RecordViewImpl getRecordViewImpl() {
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record());
		return rvi;
	}
	
}
