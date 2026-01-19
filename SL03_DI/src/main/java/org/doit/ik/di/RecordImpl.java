package org.doit.ik.di;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecordImpl implements Record{
	private int kor;
	private int mat;
	private int eng;
	
	@Override
	public int total() {
		return this.kor+eng+mat;
	}
	@Override
	public double avg() {
		return (double)this.total()/3;
	}
	
}
