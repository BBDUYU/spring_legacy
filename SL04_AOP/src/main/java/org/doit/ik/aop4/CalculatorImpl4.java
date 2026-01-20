package org.doit.ik.aop4;

import org.springframework.stereotype.Component;

@Component("calc4")
public class CalculatorImpl4 implements Calculator4{

	@Override
	public int add(int x, int y) {
		int result = x+y;
		return result;
	}

	@Override
	public int sub(int x, int y) {
		int result = x-y;
		return result;
	}

	@Override
	public int mul(int x, int y) {
		int result = x*y;
		return result;
	}

	@Override
	public int div(int x, int y) {
		int result = x/y;
		return result;
	}
	
	
}
