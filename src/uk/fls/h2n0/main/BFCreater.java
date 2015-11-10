package uk.fls.h2n0.main;

public class BFCreater {

	
	public static BFCreater instance = new BFCreater();
	public String program = "";
	
	public void printValue(String a){
		int c = Character.codePointAt(a, 0) - 10;
		add(c);
		printCell();
		zeroCell();
	}
	
	public void printString(String s){
		for(int i = 0; i < s.length(); i++){
			printValue(s.substring(i,i+1));
		}
	}
	
	public void left(){
		addToProgram("<");
	}
	
	public void right(){
		addToProgram(">");
	}
	
	public void zeroCell(){
		String s = "[-]";
		addToProgram(s);
	}
	
	public void add(int i){
		String a = "";
		for(int j = i;j > 0; j --){
			a += "+";
		}
		addToProgram(a);
	}
	
	public void startloop(){
		addToProgram("[");
	}
	
	public void closeLoop(){
		addToProgram("]");
	}
	
	public void debug(){
		addToProgram("?");
	}
	
	public String printProgram(){
		System.out.println(this.program);
		return this.program;
	}
	
	public void printCell(){
		addToProgram(".");
	}
	
	private void addToProgram(String a){
		this.program += a + "\n";
	}
}
