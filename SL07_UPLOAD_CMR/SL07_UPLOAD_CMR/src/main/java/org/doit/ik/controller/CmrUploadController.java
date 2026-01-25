package org.doit.ik.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.doit.ik.domain.Message;
import org.doit.ik.domain.MultiMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cmr/*")
public class CmrUploadController {
	
	// /cmr/multiupload 요청 -> /cmr/multiupload.jsp 포워딩
	@GetMapping("/multiupload")
	public void multiupload() {
		
	}
	
	@PostMapping("/multiupload")
	public void multiupload(MultiMessage multiMessage) {
	
		// 1.
		String output = multiMessage.getOutput();
		log.info("🍀🐹🍀🐹 output: " + output);
		
		// 2. 
		List<CommonsMultipartFile> attachList = multiMessage.getAttach();
		for (CommonsMultipartFile attach : attachList) {
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

	// /cmr/upload 요청 -> /cmr/upload.jsp 포워딩
	@GetMapping("/upload")
	public void upload() {
		
	}
	
	@PostMapping("/upload")
	public void upload(Message message) {
		
		// 1.
		String output = message.getOutput();
		log.info("🍀🐹🍀🐹 output: " + output);
		
		// 2.
		MultipartFile attach = message.getAttach();
		if (!attach.isEmpty()) { // 첨부파일이 존재할 경우에 if문
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
