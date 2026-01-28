package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberShipServiceImpl implements MemberShipService{

	@Autowired
	private NoticeMapper noticeDao;
	
	@Transactional
	@Override
	public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException {
		this.noticeDao.insert(noticeVO);
		this.noticeDao.updateMemberPoint(id);
//		noticeVO.setTitle(noticeVO.getTitle()+"-02");
//		this.noticeDao.insert(noticeVO);
	}
}
