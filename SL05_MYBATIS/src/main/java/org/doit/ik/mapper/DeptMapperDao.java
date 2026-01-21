package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.DeptDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//@Repository
public class DeptMapperDao implements DeptMapper{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<DeptDTO> selectDept() {
		List<DeptDTO> list = this.sqlSessionTemplate.selectList("org.doit.ik.mapper.DeptMapper.selectDept");
		return list;
	}

	@Override
	public int insertDept(DeptDTO dto) {
		return 0;
	}

	@Override
	public int deleteDept(int deptno) {
		return 0;
	}
	
}
