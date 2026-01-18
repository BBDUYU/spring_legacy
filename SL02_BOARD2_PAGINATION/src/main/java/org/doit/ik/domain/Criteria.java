package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum;  // 현재 페이지 번호                 1
    private int amount;   // 한 페이지에 출력할 게시글 수     10
    
	public Criteria() {
		this(1, 3);
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
    			.queryParam("amount", this.amount);
    	return builder.toUriString();
    }

}






