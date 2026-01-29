package org.doit.ik.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.doit.ik.service.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 공지사항: 목록, 상세보기 ...
@Controller
@RequestMapping("/customer/*")
public class CustomerController {

	@Autowired
	private NoticeMapper noticeDao;
	
	@Autowired
	private MemberShipService memberShipService;

	// [6] 공지사항 삭제
	// <a class="btn-del button" href="noticeDel.htm?seq=${ noticeVO.seq }">삭제</a>
	@GetMapping("/noticeDel.htm")
	public String noticeDel(
			@RequestParam("seq") String seq ,
			@RequestParam("filesrc") String filesrc ,
			RedirectAttributes rttr,
			HttpServletRequest request
			) throws Exception {
		// 첨부파일이 있는 경우에는 첨부파일도 삭제
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		File delFilesrc = new File(uploadRealPath, filesrc);
		if( delFilesrc.exists() && delFilesrc.isFile() ) delFilesrc.delete();

		// 테이블만 삭제
		int rowCount = this.noticeDao.delete(seq); 	 
		rttr.addFlashAttribute("result", rowCount);
		return "redirect:notice.htm"; // 리다이렉트
	}

	// [5-3]  공지사항 수정   + POST   
	//          ㄴ 첨부파일 처리
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(
			NoticeVO noticeVO   // 커맨드 객체     (수정된 공지사항)
			, RedirectAttributes rttr
			, @RequestParam("o_filesrc") String ofilesrc
			, HttpServletRequest request
			) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		// 1.
		String uploadRealPath = null;
		CommonsMultipartFile attach = noticeVO.getFile();
		if (!attach.isEmpty()) {  // 수정할 때 새로 첨부파일 추가
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("😘 uploadRealPath : " + uploadRealPath);
			// A. 이전에 첨부된 파일 있을 경우에 삭제
			File delFile = new File(uploadRealPath, ofilesrc);
			if (  delFile.exists()  && delFile.isFile()  ) {
				delFile.delete();
			} // if
			
			// B. 수정할 때 새로 추가된 첨부파일을 저장하는 코딩.
			String originalFilename = attach.getOriginalFilename();
			String fileSystemName = getFileNameCheck(uploadRealPath, originalFilename);
			File dest = new File(uploadRealPath, fileSystemName);
			attach.transferTo(dest);  // 파일 서버 저장
			noticeVO.setFilesrc(fileSystemName);
		} else { // 새로 수정할 첨부파일이 없는 경우
			noticeVO.setFilesrc(ofilesrc);			
		}

		noticeVO.setWriter("user");

		// 2. 
		int rowCount = this.noticeDao.update(noticeVO);	 
		rttr.addAttribute("result", rowCount);  
		rttr.addAttribute("seq", noticeVO.getSeq());  
		return "redirect:noticeDetail.htm";  
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
		return "customer.noticeEdit"; // 포워딩
	}


	// [4-3] 공지사항 글쓰기 + 첨부파일 처지 
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;		
		while( true ) {			
			File f = new File(uploadRealPath, originalFilename);			
			if( !f.exists() ) return originalFilename;	 
			String fileName = originalFilename.substring(0, originalFilename.length() - 4 ); 
			String ext =  originalFilename.substring(originalFilename.length() - 4 ); 
			originalFilename = fileName+"-"+(index)+ext;
			index++;
		} // while 
	}

	@PostMapping("/noticeReg.htm")
	public String noticeReg(
			NoticeVO noticeVO   // 커맨드 객체 
			, RedirectAttributes rttr
			, HttpServletRequest request
			, Principal principal
	         // , @AuthenticationPrincipal UserDetails user
			) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		// 1. 
		String uploadRealPath = null;
		CommonsMultipartFile attach = noticeVO.getFile();
		if (!attach.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("😘 uploadRealPath : " + uploadRealPath);
			String originalFilename = attach.getOriginalFilename();
			String fileSystemName = getFileNameCheck(uploadRealPath, originalFilename);

			File dest = new File(uploadRealPath, fileSystemName);
			attach.transferTo(dest);  // 파일 서버 저장

			noticeVO.setFilesrc(fileSystemName);
		} // if

		noticeVO.setWriter(principal.getName());
//		this.noticeDao.insertAndPointUpOfMember(noticeVO,"user");
		this.memberShipService.insertAndPointUpOfMember(noticeVO,principal.getName());
		return "redirect:notice.htm";
		
	} 

	

	// [3] 공지사항 글쓰기 컨트롤러 메서드 선언
	// <a class="btn-write button" href="noticeReg.htm">글쓰기</a>
	@GetMapping("/noticeReg.htm")
	public String noticeReg( ) throws Exception {
		return "customer.noticeReg"; // 포워딩
	}

	// [2] 공지사항 상세 보기 컨트롤러 메서드 선언
	@GetMapping("/noticeDetail.htm")
	public String noticeDetail(
			Model model
			, @RequestParam("seq") String seq
			) throws Exception {
		
		// 1. 조회수 증가
		this.noticeDao.hitUp(seq);
		// 2. 
		NoticeVO noticeVO = this.noticeDao.getNotice(seq); 	 
		model.addAttribute("noticeVO", noticeVO);

		return "customer.noticeDetail";
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

		return "customer.notice";
	}
	
	
}
