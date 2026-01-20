package org.doit.ik.di4;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Setter
@Component(value="rvi")
public class RecordViewImpl4 implements RecordView4{
	@Autowired
	private RecordImpl4 record = null;
	
	//결합력이 높은 코딩
	//private RecordImpl record = new RecordImpl();
	
	
	
	public RecordViewImpl4(RecordImpl4 record) {
		this.record=record;
	}
	public RecordViewImpl4() {
	      super();
	   }


	@Override
	public void input() {
		try (Scanner scanner = new Scanner(System.in);){
			System.out.println("> kor, eng, mat input ");
			int kor = scanner.nextInt();
			int eng = scanner.nextInt();
			int mat = scanner.nextInt();
			
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void output() {
		System.out.printf("> kor=%d, eng=%d, mat=%d, tot=%d, avg=%.2f\n",
				this.record.getKor()
				,this.record.getEng()
				,this.record.getMat()
				,this.record.total()
				,this.record.avg());
		
	}
}
