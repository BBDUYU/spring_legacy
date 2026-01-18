package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardService {
	// [1] 페이징 처리 X
	List<BoardVO> getList();
	// [2] 페이징 처리 O	
	List<BoardVO> getListWithPaging( Criteria criteria); // 페이징처리
	int getTotal( Criteria criteria );
	
	
	void register(BoardVO boardVO);
	BoardVO get(Long bno);	
	boolean modify(BoardVO boardVO);
	boolean remove(Long bno);

}
