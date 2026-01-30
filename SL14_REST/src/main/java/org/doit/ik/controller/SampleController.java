package org.doit.ik.controller;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.doit.ik.domain.SampleVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.log4j.Log4j;

@RestController	//	@Controller + @ResponseBody
@RequestMapping("/sample")
@Log4j
public class SampleController {
	// [1] 단순 문자열 반환
	// http://localhost/sample/getText  + GET
	/*
	 * @GetMapping("getText") public String getText() {
	 * log.info("@ @ @ @ Mime Type : "+ MediaType.TEXT_PLAIN_VALUE); return "Hello";
	 * }
	 */
	// [2]
	// produces 속성 : 생산하는 MIME 타입 지정하는 속성
	/*
	 * @GetMapping(value="getText",produces = "text/plain; charset=UTF-8") public
	 * String getText() { log.info("! ! ! !  Mime Type : "+
	 * MediaType.TEXT_PLAIN_VALUE); return "Hello"; }
	 */
	
	// [3] 자바객체 -> JSON, XML 응답
	@GetMapping(value="getSample", produces = {
	          MediaType.APPLICATION_JSON_UTF8_VALUE ,
	          MediaType.APPLICATION_XML_VALUE
	           })
	public SampleVO getSample() {
		return new SampleVO(101,"길동","홍");
	}
	
	// [4] 자바객체 -> JSON, XML 응답
		@GetMapping(value="getSampleList")
		public List<SampleVO> getSampleList() {
			return IntStream.rangeClosed(1, 11)
					.mapToObj(i->new SampleVO(i,"길동"+i,"홍"))
					.collect(Collectors.toList());
		}
}	
