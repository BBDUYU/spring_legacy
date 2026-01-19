package org.doit.ik.controller;

import org.doit.ik.service.EmpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@AllArgsConstructor
public class EmpController {
	private EmpService empService;
//	 url:"pageContext.request.contextPath}/emp/empno",type:"GET",
	@GetMapping(value="/emp/empno")
	public String checkEmpno(@RequestParam("empno") int empno) {
		boolean isAvailable = this.empService.isEmpnoAvailable(empno);
		return isAvailable ? "available" : "unavailable";
	}
	@GetMapping(value="/empnoCheck/{empno}")
	public String checkEmpno2(@PathVariable("empno") int empno) {
		boolean isAvailable = this.empService.isEmpnoAvailable(empno);
		return isAvailable ? "available" : "unavailable";
	}
}
