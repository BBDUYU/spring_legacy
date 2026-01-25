package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDao {

	// 1. 검색 조건에 맞는 공지사항의 갯수 반환하는 메서드 
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT "
				+ "   FROM NOTICES "
				+ "   WHERE "+field+" LIKE ?";
		// 0.  
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1.  
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2.  
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");

		// 3.  
		ResultSet rs = st.executeQuery();
		rs.next();

		int cnt = rs.getInt("cnt");

		rs.close();
		st.close();
		con.close();

		return cnt;
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
		         + "                          WHERE "+field+" LIKE ? "
		         		+ "                   ORDER BY REGDATE DESC"
		         		+ "                ) N"
		         		+ "  ) "
               +  " WHERE NUM BETWEEN ? AND ? ";
		
		// 0.  
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1.  
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2.  
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, srow);
		st.setInt(3, erow);
		// 3.  
		ResultSet rs = st.executeQuery();

		List<NoticeVO> list = new ArrayList<NoticeVO>();

		while(rs.next()){
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setSeq(rs.getString("seq"));
			noticeVO.setTitle(rs.getString("title"));
			noticeVO.setWriter(rs.getString("writer"));
			noticeVO.setRegdate(rs.getDate("regdate"));
			noticeVO.setHit(rs.getInt("hit"));
			noticeVO.setContent(rs.getString("content"));
			noticeVO.setFilesrc(rs.getString("filesrc"));

			list.add(noticeVO);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}

	// 3. 공지사항 삭제 메서드 
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "DELETE NOTICES "
				+ "   WHERE SEQ = ?";
		// 0.  
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1.  
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2.  
		PreparedStatement st = con.prepareStatement(sql);	
		st.setString(1, seq);

		int af = st.executeUpdate();

		return af;
	}

	// 4. 공지사항 수정 메서드 
	public int update(
			NoticeVO noticeVO   // 수정할 공지사항 내용이 들어있는 VO 객체
			) throws ClassNotFoundException, SQLException{
		// 
		String sql = "UPDATE NOTICES "
				+ "   SET TITLE = ?, CONTENT = ?, FILESRC = ? "
				+ "    WHERE SEQ = ? ";
		// 0.  
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1.  
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2.  
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, noticeVO.getTitle());
		st.setString(2, noticeVO.getContent());
		st.setString(3, noticeVO.getFilesrc());
		st.setString(4, noticeVO.getSeq());		

		int af = st.executeUpdate();

		return af;
	}

	// 5. 해당 글번호의 공지사항을 반환하는 메서드: 공지사항 보기
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
				+ "   FROM NOTICES "
				+ "   WHERE SEQ = " + seq ;
		// 0.  
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1.  
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2. 
		Statement st = con.createStatement();
		// 3.  
		ResultSet rs = st.executeQuery(sql);
		rs.next();

		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setSeq(rs.getString("seq"));
		noticeVO.setTitle(rs.getString("title"));
		noticeVO.setWriter(rs.getString("writer"));
		noticeVO.setRegdate(rs.getDate("regdate"));
		noticeVO.setHit(rs.getInt("hit"));
		noticeVO.setContent(rs.getString("content"));
		noticeVO.setFilesrc(rs.getString("filesrc"));

		rs.close();
		st.close();
		con.close();

		return noticeVO;
	}

	// 6. 공지사항 쓰기 메서드
	public int insert(NoticeVO noticeVO) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO NOTICES "
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
				+ " VALUES"
				+ "( "
				+ "   (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), ?, ?, 'kenik', SYSDATE, 0, ?"
				+ ")";
		// 0.  
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1.  
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2. 
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, noticeVO.getTitle());
		st.setString(2, noticeVO.getContent());
		st.setString(3, noticeVO.getFilesrc());

		int af = st.executeUpdate();

		st.close();
		con.close();

		return af;
	}

}
