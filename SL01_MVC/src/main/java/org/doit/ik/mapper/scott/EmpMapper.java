package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;

public interface EmpMapper {
	// 모든 사원정보를 반환하는 메서드
	ArrayList<EmpDTO> selectEmp();
	ArrayList<EmpDTO> selectEmpByDeptno(int deptno);

}
