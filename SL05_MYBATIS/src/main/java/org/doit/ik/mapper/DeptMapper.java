package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptDTO;
public interface DeptMapper {
	
	List<DeptDTO> selectDept();
	int insertDept(DeptDTO dto);
	int deleteDept(@Param("deptno")int deptno);
	
	
}
