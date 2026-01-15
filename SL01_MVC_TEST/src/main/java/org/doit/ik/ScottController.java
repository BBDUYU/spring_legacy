package org.doit.ik;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;

@Controller
public class ScottController {

	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private EmpMapper empMapper;

	private static final Logger logger = LoggerFactory.getLogger(ScottController.class);

	@GetMapping(value = "/scott/dept")
	public String dept(Locale locale, Model model) {
		logger.info("> ScottContoller.dept()");
		ArrayList<DeptDTO> list = this.deptMapper.selectDept();

		model.addAttribute("list", list);

		return "/scott/dept";
	}

	// 모든 사원정보 처리 컨트롤러 메서드
	
	 @PostMapping("/scott/emp") public String emp(Locale locale, Model model,HttpServletRequest request,@RequestParam("deptno")int deptnoArr[]) {
	 logger.info("> ScottContoller.emp()"); 
	
	 ArrayList<EmpDTO> list =this.empMapper.selectEmp(deptnoArr);
	 
	 model.addAttribute("list", list );
	 
	 return "/scott/emp"; 
	 }
	

	 @RequestMapping(value = "/scott/empdept", method = RequestMethod.GET)
		public String empdept(Locale locale, Model model,HttpServletRequest request,@RequestParam(value="deptno", defaultValue="10")int deptno) {
			logger.info("ScottController.empdept", locale);
			/* 필요없는 코딩
			String pDeptno = request.getParameter("deptno");
			int deptno=0;
			if (pDeptno==null) {
				deptno=10;
			}else {
				deptno=Integer.parseInt(pDeptno);				
			}
			*/
			ArrayList<DeptDTO> dlist = this.deptMapper.selectDept();
			model.addAttribute("dlist", dlist);
			
			ArrayList<EmpDTO> elist = this.empMapper.selectEmpDept(deptno);
			model.addAttribute("elist", elist);
			return "/scott/empdept";
	}
	


}
