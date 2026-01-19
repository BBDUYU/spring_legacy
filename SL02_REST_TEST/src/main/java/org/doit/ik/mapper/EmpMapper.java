package org.doit.ik.mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.doit.ik.domain.EmpDTO;

public interface EmpMapper {
	EmpDTO selectUserByEmpno(@Param("empno")int empno);
}
