package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.DeptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ScottController {
	
	@Autowired
	private DeptMapper deptMapper;

//	@RequestMapping(value = "/scott/dept", method = RequestMethod.GET)
	@GetMapping(value = "/scott/dept")
	public void dept(Locale locale, Model model) {
		log.info("ScottController.dept() 컨트롤러 메서드 호출됨...");
		List<DeptDTO> list = this.deptMapper.selectDept();
		model.addAttribute("list",list);
	}
	
}
