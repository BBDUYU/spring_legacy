package org.doit.ik.service;

import org.doit.ik.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor

public class EmpServiceImpl implements EmpService{
	private EmpMapper empMapper;
	
	@Override
	public boolean isEmpnoAvailable(int empno) {
		return this.empMapper.selectUserByEmpno(empno)==null;
	}
}
