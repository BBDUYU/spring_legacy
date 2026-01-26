package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

	// 1. 검색 조건에 맞는 공지사항의 갯수 반환하는 메서드 
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT "
				+ "   FROM NOTICES "
				+ "   WHERE "+field+" LIKE :query"; 
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("query","%"+query+"%" );
		
		return this.npJdbcTemplate.queryForObject(sql,paramSource, Integer.class );
	}

	// 2. 검색 조건에 맞는 공지사항 목록을 반환하는 메서드 
	public List<NoticeVO> getNotices(
			int page,	  // 현재 페이지 번호 
			String field, // 검색조건
			String query  // 검색어
			) throws ClassNotFoundException, SQLException
	{					

		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...

		String sql = "SELECT * "
		         + "  FROM ( "
		         + "                 SELECT ROWNUM NUM, N.* "
		         + "                 FROM ("
		         + "                          SELECT * "
		         + "                          FROM NOTICES "
		         + "                          WHERE "+field+" LIKE :query "
		         		+ "                   ORDER BY REGDATE DESC"
		         		+ "                ) N"
		         		+ "  ) "
               +  " WHERE NUM BETWEEN :srow AND :erow ";		
		
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("query", "%"+query+"%");
		paramMap.put("srow", srow);
		paramMap.put("erow", erow);
		
		return this.npJdbcTemplate.query(
				sql
				, paramMap
		        , new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class)
		        );
	}

	// 3. 공지사항 삭제 메서드 
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "DELETE NOTICES "
				+ "   WHERE SEQ = :seq";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq",seq );
		
		return this.npJdbcTemplate.update(sql, paramSource);
	}

	// 4. 공지사항 수정 메서드 
	public int update(
			NoticeVO noticeVO   // 수정할 공지사항 내용이 들어있는 VO 객체
			) throws ClassNotFoundException, SQLException{
		// 
		String sql = "UPDATE NOTICES "
				+ "   SET TITLE = :title, CONTENT = :content, FILESRC = :filesrc "
				+ "    WHERE SEQ = :seq "; 
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noticeVO);
		/*
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("title",noticeVO.getTitle() );
		paramSource.addValue("content",noticeVO.getContent() );
		paramSource.addValue("filesrc",noticeVO.getFilesrc() );
		paramSource.addValue("seq",noticeVO.getSeq() );
		*/
		return this.npJdbcTemplate.update(sql,paramSource);
	}

	// 5. 해당 글번호의 공지사항을 반환하는 메서드: 공지사항 보기
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
				+ "   FROM NOTICES "
				+ "   WHERE SEQ = :seq " ; 
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq",seq );
		
		return this.npJdbcTemplate.queryForObject(sql
		                     		, paramSource
		                            , new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class) );
	 
	}

	// 6. 공지사항 쓰기 메서드
	public int insert(NoticeVO noticeVO) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO NOTICES "
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
				+ " VALUES"
				+ "( "
				+ "   (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc"
				+ ")"; 
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(noticeVO);

		return this.npJdbcTemplate.update(sql,paramSource); 
	}

}
