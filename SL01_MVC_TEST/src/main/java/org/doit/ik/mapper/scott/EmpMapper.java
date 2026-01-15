package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;

public interface EmpMapper {
	// 모든 사원정보를 반환하는 메서드
	ArrayList<EmpDTO> selectEmp();
	// 특정 사원정보
	ArrayList<EmpDTO> selectEmp(int deptnoArr[]);
	
	ArrayList<EmpDTO> selectEmpDept(@Param("deptno") int deptno);
	

}
