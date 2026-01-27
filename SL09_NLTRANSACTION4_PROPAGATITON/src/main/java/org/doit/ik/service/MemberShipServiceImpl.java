package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberShipServiceImpl implements MemberShipService{

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public void insertAndPointUpOfMember(NoticeVO noticeVO, String id) throws ClassNotFoundException, SQLException {
		this.noticeDao.insert(noticeVO);
		noticeVO.setTitle(noticeVO.getTitle()+"-02");
		this.noticeDao.insert(noticeVO);
	}
}
