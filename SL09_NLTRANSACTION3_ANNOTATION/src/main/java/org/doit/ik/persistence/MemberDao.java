package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

public interface MemberDao {

	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException;
	
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	

}





