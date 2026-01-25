package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 1. 회원ID를 매개변수로 회원 정보를 반환하는 메서드
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
				+ "   FROM MEMBER "
				+ "   WHERE id = ?";

		return this.jdbcTemplate.queryForObject(sql, new Object[] { id }
		, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
	}

	// 2. 회원 가입 
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException
	{
		String sql = "INSERT INTO MEMBER "
				+ "( id, pwd, name, gender, birth, is_lunar, cphone, email, habit, regdate) "
				+ " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		return this.jdbcTemplate.update(sql 
					, member.getId() 
					, member.getPwd() 
					, member.getName() 
					, member.getGender() 
					, member.getBirth() 
					, member.getIs_lunar() 
					, member.getCphone() 
					, member.getEmail() 
					, member.getHabit()
				);

	}

}





