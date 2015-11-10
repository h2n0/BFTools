package uk.fls.h2n0.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class BFTranslator {

	private Cell[] cells;
	private int pos;
	private final int cellNum = 30000;
	private Stack<Integer> loops = new Stack<Integer>();
	private int action = 0;
	private final String tokens = "<>,.+-[]?";
	private String program = "";
	
	public static BFTranslator instance = new BFTranslator();
	
	public BFTranslator(){
		cleanUp();
	}
	
	@SuppressWarnings("static-access")
	public void reset(){
		this.instance = new BFTranslator();
	}
	
	public void cleanUp(int num){
		this.pos = 0;
		this.cells = new Cell[num];
		for(int i = 0; i < cells.length; i++){
			cells[i] = new Cell();
		}
	}
	
	public void cleanUp(){
		action = 0;
		cleanUp(cellNum);
	}
	
	private void moveCell(int dir){
		int finalPos = this.pos + dir;
		if(finalPos < 0 || finalPos > cells.length)return;
		this.pos = finalPos;
	}
	
	private void incrementCell(int i){
		this.cells[this.pos].inc(i);
	}
	
	private void decreaseCell(int i){
		this.cells[this.pos].dec(i);
	}
	
	private void getInput(){
		byte res = 0;
		try {
			res = (byte) System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.cells[this.pos].set(res);
	}
	
	private void debugPrintCells(int num){
		String res = "";
		for(int i = 0; i < num; i++){
			res += this.cells[i].value + " ";
		}
		System.out.println(res);
	}
	
	/**
	 * Converts the .bfp file to a parse-able string
	 * @param path
	 */
	public void parseProgram(String path){
		BufferedReader reader = null;
		try {
			 reader = new BufferedReader(new FileReader(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String res = "";
		String line;
		try {
			while((line = reader.readLine()) != null){
				for(int i = 0; i < line.length(); i++){
					char c = line.charAt(i);
					if(tokens.indexOf(String.valueOf(c)) == -1)continue;
					if(c == "#".toCharArray()[0])break;
					res += c;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(res);
		parseProgramSnippet(res);
	}
	
	/**
	 * Runs a bfp string
	 * @param prog
	 */
	public void parseProgramSnippet(String prog){
		cleanUp();
		this.program = prog;
		while(next() != -1);
	}
	
	public String programAt(int pos){
		return String.valueOf(program.charAt(pos));
	}
	
	public int next(){
		if(action >= program.length())return -1;
		if(tokens.indexOf(programAt(action)) == -1){
			action++;
			return next();
		}
		Cell c = cells[this.pos];
		String pos = programAt(action);
		switch(pos){
		case "<":
			this.moveCell(-1);
			break;
		case ">":
			this.moveCell(1);
			break;
		case "-":
			this.decreaseCell(1);
			break;
		case "+":
			this.incrementCell(1);
			break;
		case ",":
			getInput();
			break;
		case ".":
			System.out.println(c.getChar());
			break;
		case "[":
			if(c.value != 0){
				this.loops.push(action);
			}else{
				int loop = 1;
				while(loop > 0){
					action++;
					if(action >= program.length())return -1;
					if(programAt(action) == "]")loop--;
					else if(programAt(action) == "[")loop++;
				}
			}
			break;
		case "]":
			if(c.value != 0){
				action = loops.get(loops.size()-1);
			}else{
				loops.pop();
			}
			break;
		case "?":
			this.debugPrintCells(5);
			break;
		}
		return action++;
	}
}
