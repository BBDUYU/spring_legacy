package org.doit.ik.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.doit.ik.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/ajax/*")
public class AjaxRestUploadController {
	
	// 1. ajax 요청 테스트용 컨트롤러 메서드
	@GetMapping("/user")
	public User getUser() {
		return new User("John", "Doe", 30);
	}
	
	// RequestParam 애노테이션을 이용한 업로드 파일 접근
	@PostMapping("/uploadAjax")
	public void uploadAjax(
			@RequestParam("attachList") List<MultipartFile> attachList
			) {
		
		for (MultipartFile attach : attachList) {
			if (!attach.isEmpty()) { // 첨부파일이 존재할 경우에 if문
				log.info("-".repeat(30));
				String originalFilename = attach.getOriginalFilename();
				log.info("🐈‍⬛🐱🐈‍⬛🐱 originalFilename: " + originalFilename);
				long fileSize = attach.getSize();
				log.info("🦌🦌🦌🦌 fileSize: " + fileSize);
				
				String parent = "C:\\upload";
				File dest = new File(parent, originalFilename);
				try {
					attach.transferTo(dest);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				log.info("🍀🐹🍀🐹 end: ");
			}
		}
	}
	
}
