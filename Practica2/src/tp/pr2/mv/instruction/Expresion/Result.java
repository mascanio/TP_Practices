package tp.pr2.mv.instruction.Expresion;

import tp.pr2.mv.instruction.Instruction;
import tp.pr2.mv.instruction.RestSeq.Push;

import java.util.ArrayList;

public class Result {

	private ArrayList<Instruction> result;

	public Result(int x) {
		this.result = new ArrayList<>() ;
		this.result.add(new Push(x));
	}

	public Result(ArrayList<Instruction> result) {
		this.result = result;
	}
	
	public ArrayList<Instruction> getResult() {
		return result;
	}
	public String toString() {
		return String.valueOf(this.result);
	}

}
