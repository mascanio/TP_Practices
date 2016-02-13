package tp.pr5.mv.model.Expresion;

import java.util.ArrayList;

import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.RestSeq.Push;

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
