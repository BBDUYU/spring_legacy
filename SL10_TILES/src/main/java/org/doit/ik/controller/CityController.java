package org.doit.ik.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/city/*")
public class CityController {
	@GetMapping("/london")
	public String london() {
		log.info("> CityController.java");
		return "city/london.tiles"; //타일즈 뷰 이름
	}
	@GetMapping("/paris")
	   public String paris() {
	      log.info("> CityController.paris()...");
	      return "city/paris.tiles";
	   }
	   
	   @GetMapping("/seoul")
	   public String seoul() {
	      log.info("> CityController.seoul()...");
	      return "city/seoul.tiles";
	   }
	   
}
