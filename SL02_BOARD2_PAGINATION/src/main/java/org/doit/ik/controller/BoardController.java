package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.HomeController;
import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.PageDTO;
import org.doit.ik.mapper.BoardMapper;
import org.doit.ik.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	/* [1] Controller -> Service ->  Mapper X
	// private static final Logger logger = LoggerFactory.getLogger(BoardController.class);	
	private BoardMapper boardMapper;

	@GetMapping(value = "/board/list")
	public void list(Model model) {
		log.info("> 👌👌👌 BoardController.list()...");

		List<BoardVO> list = this.boardMapper.getList();
		model.addAttribute("list", list);
	}
	 */

	private BoardService boardService;

	// [1] 페이징 처리 X - 글 목록 보기
	/*
	@GetMapping(value = "/list")
	public void list(Model model) {
		log.info("> 👌👌👌 BoardController.list()..."); 
		model.addAttribute("list", this.boardService.getList());
	}
	 */

	// [1-2] 페이징 처리 O - 글 목록 보기
	// http://locahost/board/list         1       10           
	// http://locahost/board/list?pageNum=3&amout=10 
	@GetMapping(value = "/list")
	public void list(Model model, Criteria criteria) {
		log.info("> 👌👌👌 BoardController.list()..."); 
		
		model.addAttribute("list", this.boardService.getListWithPaging(criteria));		
		int total = this.boardService.getTotal(criteria); // 총 게시글 수
		
		//    [1] 2 3 4 5 6 7 8 9 10 >
		model.addAttribute("pageMaker",  new PageDTO(criteria, total) ); 
		
	}


	// 글쓰기 -> 입력폼을 응답 컨트롤러 메서드 
	@GetMapping(value = "/register")
	public void register(Model model) {
		log.info("> 👌👌👌 BoardController.register()... GET");		
	}

	// 글쓰기 입력폼에 입력한 후 저장을 처리하는 컨트롤러 메서드  
	/* [1]
	@PostMapping(value = "/register")
	public void register(Model model, HttpServletRequest request) {
		log.info("> 👌👌👌 BoardController.register()..."); 

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		BoardVO vo = new BoardVO();

		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
	}
	 */

	// [2] 커맨드 객체 파라미터
	@PostMapping(value = "/register")
	public String register(Model model, BoardVO boardVO
			, RedirectAttributes rttr) {
		log.info("> 👌👌👌 BoardController.register()... POST");
		this.boardService.register(boardVO);
		// boardVO.getBno() 글번호
		// rttr.addAttribute("result", boardVO.getBno());   // 쿼리 스트링  ?result=3
		rttr.addFlashAttribute("result", boardVO.getBno());   // 세션 일회성...
		// return "/board/list"; // 포워딩
		return "redirect:/board/list"; // 스프링 리다이렉트
	}

	//   /board/get?bno=5
	//   /board/modify?bno=5
	// http://localhost/board/get?pageNum=2&amount=3&bno=4
	@GetMapping(value = { "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model
			,@ModelAttribute("criteria") Criteria criteria ) {
		log.info("> 🤩🤩🤩 BoardController.get()... GET");
		BoardVO boardVO = this.boardService.get(bno);
		model.addAttribute("boardVO", boardVO);
		// model.addAttribute("criteria", criteria);
	}

	@PostMapping(value = "/modify")
	public String modify(BoardVO boardVO, RedirectAttributes rttr
			,@ModelAttribute("criteria") Criteria criteria) {
		log.info("> 🤩🤩🤩 BoardController.modify()... POST");

		if(  this.boardService.modify(boardVO)  ) {
			// 수정 성공하면 
			rttr.addFlashAttribute("result", "SUCCESS");
		}

		//        /board/get?bno=6&pageNum=2&amout=3
		rttr.addAttribute("bno", boardVO.getBno());
		
		rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
		return "redirect:/board/get";
	}


	// GET + /board/remove?bno=6&title=<b>오늘은+금용일...<%2Fb>&content=<b>오늘은+금용일...<%2Fb>&writer=문종범
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") Long bno
			, RedirectAttributes rttr) {
		log.info("> 👍👍👍 BoardController.remove()... GET");

		if( this.boardService.remove(bno) ) {
			rttr.addFlashAttribute("result", "REMOVESUCCESS");
			rttr.addFlashAttribute("bno", bno); // 삭제된 글 번호
		}

		return "redirect:/board/list";
	}

}






