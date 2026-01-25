package org.doit.ik.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 공지사항: 목록, 상세보기 ...
@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	// [6] 공지사항 삭제
	// <a class="btn-del button" href="noticeDel.htm?seq=${ noticeVO.seq }">삭제</a>
	@GetMapping("/noticeDel.htm")
	public String noticeDel(
			@RequestParam("seq") String seq ,
			RedirectAttributes rttr
			) throws Exception {
		int rowCount = this.noticeDao.delete(seq); 	 
		rttr.addFlashAttribute("result", rowCount);
		return "redirect:notice.htm"; // 리다이렉트
	}
	
	// [5-2]  공지사항 수정   + POST   
	// HTTP 상태 405 – 허용되지 않는 메소드 
	// 메시지 Request method 'POST' not supported
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(
			NoticeVO noticeVO   // 커맨드 객체     (수정된 공지사항)
			, RedirectAttributes rttr
			) throws ClassNotFoundException, SQLException { 
		
		int rowCount = this.noticeDao.update(noticeVO);		
		// rttr.addFlashAttribute(null, rttr)
		rttr.addAttribute("result", rowCount); // 
		rttr.addAttribute("seq", noticeVO.getSeq()); // ?seq=5
		return "redirect:noticeDetail.htm"; // 스프링 리다이렉트 (redirect: 접두사)
	}
	
	// [5] 공지사항 수정   + GET
	// <a class="btn-edit button" href="noticeEdit.htm">수정</a>
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(
			@RequestParam("seq") String seq
			, Model model
			) throws Exception {
		NoticeVO noticeVO = this.noticeDao.getNotice(seq); 	 
		model.addAttribute("noticeVO", noticeVO);
		return "noticeEdit.jsp"; // 포워딩
	}
	
	// [4-2] 공지사항 글쓰기
	@PostMapping("/noticeReg.htm")
	public String noticeReg(
			NoticeVO noticeVO   // 커맨드 객체 
			, RedirectAttributes rttr
			) throws ClassNotFoundException, SQLException { 
		
		int rowCount = this.noticeDao.insert(noticeVO);
		// rttr.addFlashAttribute(null, rttr)
		rttr.addAttribute("result", rowCount); // ?result=1
		return "redirect:notice.htm"; // 스프링 리다이렉트 (redirect: 접두사)
	}
	
	// [4] 공지사항 글쓰기 + db처리 + 공지사항 목록으로 리다이렉트 하는 
	//    컨트롤러 메서드 선언
	// <form action="" method="post">
	/*
	@PostMapping("/noticeReg.htm")
	public String noticeReg(
			@RequestParam(value = "title" ) String title , 
			@RequestParam(value = "content" ) String content  
			) throws ClassNotFoundException, SQLException {
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setTitle(title);
		noticeVO.setContent(content);
		
		int rowCount = this.noticeDao.insert(noticeVO);
		
		return "redirect:notice.htm"; // 스프링 리다이렉트 (redirect: 접두사)
	}
	*/
	
	// [3] 공지사항 글쓰기 컨트롤러 메서드 선언
	// <a class="btn-write button" href="noticeReg.htm">글쓰기</a>
	@GetMapping("/noticeReg.htm")
	public String noticeReg( ) throws Exception {
		return "noticeReg.jsp"; // 포워딩
	}
	
	// [2] 공지사항 상세 보기 컨트롤러 메서드 선언
	@GetMapping("/noticeDetail.htm")
	public String noticeDetail(
			Model model
			, @RequestParam("seq") String seq
			) throws Exception {
		 
		NoticeVO noticeVO = this.noticeDao.getNotice(seq); 	 
		model.addAttribute("noticeVO", noticeVO);
		
		return "noticeDetail.jsp";
	}
	
	// p356
	// [1] 공지사항 목록 컨트롤러 메서드 선언
	@GetMapping("/notice.htm")
	public String notices(
			Model model , 
			@RequestParam(value = "page", defaultValue = "1") int page , 
			@RequestParam(value = "field", defaultValue = "title") String field , 
			@RequestParam(value = "query", defaultValue = "") String query  
			) throws Exception {
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		model.addAttribute("list", list);		
		model.addAttribute("message", "🤩 Hello World"); 
		
		return "notice.jsp";
	}

}
