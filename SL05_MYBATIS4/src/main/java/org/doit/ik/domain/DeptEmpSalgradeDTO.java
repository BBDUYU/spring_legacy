package org.doit.ik.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("DeptEmpSalgradeDTO")
public class DeptEmpSalgradeDTO {
	
	// 부서 1:1 연관관계
	private DeptDTO deptDTO;
	
	// 사원 1:N 연관관계
//	private List<EmpDTO> empList;
	private EmpDTO empDTO;

}
