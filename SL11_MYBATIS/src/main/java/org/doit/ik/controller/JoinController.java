package org.doit.ik.controller;

import java.beans.PropertyEditorSupport;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/joinus/*")
public class JoinController {

	@Autowired
	private MemberMapper memberDao;

	// 회원가입
	@GetMapping("/join.htm")
	public String join() throws Exception{
		return "joinus.join";
	}

	// 가입
	//[3] 336p
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, "habit",new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text);

			}
			@Override
			public void setValue(Object value) {
				if(value instanceof String[]) {
					String combinedHabit = String.join("/", (String[])value);
					super.setValue(combinedHabit);
				}else {
					super.setValue(value);
				}

			}
		});
	}

	@PostMapping("/join.htm") 
	public String join(MemberVO memberVO
			,@RequestParam("year") String year
			,@RequestParam("month") String month
			,@RequestParam("day") String day
			,@RequestParam(value="habit") String []habitArr
			) throws Exception{
		String birth = String.format("%s-%s-%s",year,month,day);
		memberVO.setBirth(birth);

		String combineHabit = String.join("/",habitArr);
		memberVO.setHabit(combineHabit);

		this.memberDao.insert(memberVO);
		return "redirect:../index.htm";
	}
	
	// 로그인
	@GetMapping("/login.htm")
	public String login() throws Exception{
		return "joinus.login";
	} 
}
