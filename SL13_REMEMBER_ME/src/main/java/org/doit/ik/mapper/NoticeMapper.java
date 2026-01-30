package org.doit.ik.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.NoticeVO;

public interface NoticeMapper {
	
	public int getCount(@Param("field")String field, @Param("query")String query) throws ClassNotFoundException, SQLException;
	
	public List<NoticeVO> getNotices(
			@Param("page")int page,	  
			@Param("field")String field, 
			@Param("query")String query  
			) throws ClassNotFoundException, SQLException;
	
	public int delete(@Param("seq")String seq) throws ClassNotFoundException, SQLException;
	
	public int update(
			NoticeVO noticeVO   
			) throws ClassNotFoundException, SQLException;

	public NoticeVO getNotice(@Param("seq")String seq) throws ClassNotFoundException, SQLException;
	
	public int insert(NoticeVO noticeVO) throws ClassNotFoundException, SQLException ;
	//추상 메서드 작성자 포인트 + 1 선언
	public int updateMemberPoint(@Param("id") String id)throws ClassNotFoundException, SQLException ;
	
//	public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException;
	   
	public void hitUp(@Param("seq")String seq) throws ClassNotFoundException, SQLException;
	public int getHit(@Param("seq")String seq) throws ClassNotFoundException, SQLException;
}
