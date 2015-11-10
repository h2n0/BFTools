package uk.fls.h2n0.main;

public class BFTester {

	
	public static void main(String[] args){
		BFTranslator bf = BFTranslator.instance;
		BFCreater bc = BFCreater.instance;
		
		bc.printString("Hello World!");
		bc.add(10);
		bc.printCell();
		bf.parseProgramSnippet(bc.printProgram());
	}
}
