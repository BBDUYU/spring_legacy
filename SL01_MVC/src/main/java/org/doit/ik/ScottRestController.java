package org.doit.ik;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.TimeMapper;
import org.doit.ik.mapper.scott.DeptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;

@RestController
public class ScottRestController {
	
	@Autowired
	//@Setter(onMethod=@__({@Autowired}))
	private DeptMapper deptMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ScottRestController.class);
	
	// /scott/dept/new 요청 + POST방식 요청
	@PostMapping("/scott/dept/new")
	public ResponseEntity<String> insertDept(@RequestBody DeptDTO dto){
		logger.info("> ScottRestController.insertDept() 컨트롤러 메서드 호출됨...");      
	      int rowCount = this.deptMapper.insertDept(dto);      
	      //return rowCount;
	      return rowCount==1
	            ? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
	            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// http://localhost/scott/dept/50   +  DELETE 방식 요청 
	@DeleteMapping("/scott/dept/{deptno}")
	public ResponseEntity<String> deleteDept(@PathVariable("deptno")int deptno){
		logger.info("> ScottRestController.deleteDept() 컨트롤러 메서드 호출됨...");      
	      int rowCount = this.deptMapper.deleteDept(deptno);      
	      //return rowCount;
	      return rowCount==1
	            ? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
	            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
