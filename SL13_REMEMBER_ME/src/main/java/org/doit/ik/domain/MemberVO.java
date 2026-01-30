package org.doit.ik.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	
	private String id;       // uid -> id
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String is_lunar; // 
	private String cphone;   // 
	private String email;
	private String habit;
	private Date   regdate;   //  
	private int point;
	
	//필드 추가
	private boolean ennabled;
	//회원 1:권한 N
	private List<AuthVO> authList;
	
	
	
}
