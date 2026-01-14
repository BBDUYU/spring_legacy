package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptDTO;

public interface DeptMapper {
	// 모든 부서정보를 반환하는 메서드
	ArrayList<DeptDTO> selectDept();
	// 부서추가 메서드
	int insertDept(DeptDTO dto);
	// 부서수정
	// 부서삭제
	int deleteDept(@Param("deptno")int deptno);
}
