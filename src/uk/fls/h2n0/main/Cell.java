package uk.fls.h2n0.main;

public class Cell {

	public int value;
	
	public Cell(){
		this.value = 0;
	}
	
	public void inc(int c){
		set((this.value + c)%256);
	}
	
	public void inc(){
		this.inc(1);
	}
	
	public void dec(int c){
		int nval = this.value - c;
		if(nval < 0){
			nval += 256;
		}
		set(nval);
	}
	
	public void dec(){
		this.dec(1);
	}
	
	public void set(int nval){
		this.value = nval;
	}
	
	public char getChar(){
		return (char)this.value;
	}
}
