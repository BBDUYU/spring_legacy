package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;

public class Ex01 {

	public static void main(String[] args) {
		RecordImpl record = new RecordImpl();
		
		//RecordViewImpl rvi = new RecordViewImpl(record);
		// 1. 생성자 DI
		
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record);
		// 2. setter DI
		
		
		rvi.input();
		rvi.output();
		System.out.println(" - END - ");
	}

}
