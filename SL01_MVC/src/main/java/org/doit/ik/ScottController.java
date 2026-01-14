package org.doit.ik;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.TimeMapper;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Setter;

@Controller
public class ScottController {
	
	@Autowired
	//@Setter(onMethod=@__({@Autowired}))
	private DeptMapper deptMapper;
	@Autowired
	private EmpMapper empMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ScottController.class);

//	@RequestMapping(value = "/scott/dept", method = RequestMethod.GET)
	@GetMapping(value="/scott/dept")
	public String dept(Locale locale, Model model) {
		logger.info("> ScottContoller.dept()");
		ArrayList<DeptDTO> list = this.deptMapper.selectDept();
		
		model.addAttribute("list", list );
		
		return "/scott/dept";
	}
	
	//모든 사원정보 처리 컨트롤러 메서드
	/*
	 * @PostMapping("/scott/emp") public String emp(Locale locale, Model model) {
	 * logger.info("> ScottContoller.emp()"); ArrayList<EmpDTO> list =
	 * this.empMapper.selectEmp();
	 * 
	 * model.addAttribute("list", list );
	 * 
	 * return "/scott/emp"; }
	 */
	
	@PostMapping("/scott/emp/{deptno}")
	public ResponseEntity<List<EmpDTO>> selectEmp(@PathVariable("deptno")int deptno) {
		logger.info("> ScottContoller.selectEmp()");
		ArrayList<EmpDTO> list = this.empMapper.selectEmpByDeptno(deptno);
				
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
}
