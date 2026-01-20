package org.doit.ik.aop3;

import org.springframework.stereotype.Component;

@Component("calc3")
public class CalculatorImpl3 implements Calculator3{

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
