package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	// 필드
	private int pageNum;  // 현재 페이지 번호                 1
    private int amount;   // 한 페이지에 출력할 게시글 수     10
    
    // 검색조건 필드 추가
    private String type;
    private String keyword;
    
	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
    
	// ?
	public String getListLink() {
    	UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
    			.queryParam("pageNum", this.pageNum)
    			.queryParam("amount", this.amount) 
		    	.queryParam("type", this.type)
		    	.queryParam("keyword", this.keyword);
    				
    	return builder.toUriString();
    }
	// 검색조건 (다중 항목 검색) 제목 + 내용 + 작성자
	//  -> 문자배열로 반환메서드를 만들어서 BoardMapper.xml
	//  where 조건을 동적쿼리를 생성할 때
	//  foreach 반복문을 사용 OR 조건 반복해서 동적쿼리를 작성할때
	//  사용하는 메서드를 선언
	public String []getTypeArr(){
		return this.type==null ? new String[] {}:this.type.split("");
	}
}






