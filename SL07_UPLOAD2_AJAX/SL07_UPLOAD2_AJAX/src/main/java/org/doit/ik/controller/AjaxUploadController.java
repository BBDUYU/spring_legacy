package org.doit.ik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/ajax/*")
public class AjaxUploadController {
	
	// /ajax/user 요청 -> /ajax/upload.jsp 포워딩
	@GetMapping("/upload")
	public void ajaxUpload() {
	}
	
}
