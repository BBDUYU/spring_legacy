package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 공지사항: 목록, 상세보기 ...
// @Controller
@RequestMapping("/customer/*")
public class CustomerController2 {
	
	@Autowired
	private NoticeDao noticeDao;
	
	// [2] 공지사항 상세 보기 컨트롤러 메서드 선언
	@GetMapping("/noticeDetail.htm")
	public ModelAndView noticeDetail(HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		String pSeq = request.getParameter("seq");
		NoticeVO noticeVO = this.noticeDao.getNotice(pSeq);
		
		ModelAndView mav = new ModelAndView("noticeDetail.jsp");
		mav.addObject("noticeVO", noticeVO);
		
		return mav;
	}
	
	// [1] 공지사항 목록 컨트롤러 메서드 선언
	@GetMapping("/notice.htm")
	public ModelAndView notices(
			HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		// p282 ModelAndView
		ModelAndView mav = new ModelAndView();
		
		String pPage = request.getParameter("page");
		String pField = request.getParameter("field");
		String pQuery = request.getParameter("query");
		
		int page = 1;
		String field = "title";
		String query = "";
		
		if( pPage != null && !pPage.equals("") ) page = Integer.parseInt(pPage);
		if( pField != null && !pField.equals("") ) field = pField;
		if( pQuery != null && !pQuery.equals("") ) query = pQuery;
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		mav.addObject("list", list);
		
		mav.addObject("message", "Hello World!!!");
		mav.setViewName("notice.jsp");
		
		return mav;
	}

}
