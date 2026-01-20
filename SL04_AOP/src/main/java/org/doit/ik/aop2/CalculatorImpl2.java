package org.doit.ik.aop2;

import org.springframework.stereotype.Component;

@Component("calc")
public class CalculatorImpl2 implements Calculator2{

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
