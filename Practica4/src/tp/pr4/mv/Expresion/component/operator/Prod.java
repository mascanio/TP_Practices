package tp.pr4.mv.Expresion.component.operator;

import java.util.ArrayList;

import tp.pr4.mv.Expresion.Result;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionParser;

public class Prod extends Operator {

	public Prod(){
		this.prior = 1;
	}
	
	@Override
	public void solve() {
		ArrayList<Instruction> list = new ArrayList<>();
		list.addAll(this.opIzq.getResult());
		list.addAll(this.opDer.getResult());
		list.add (InstructionParser.parseInstruction("MULT"));
		
		this.result = new Result(list);
	}

	@Override
	public Operator parse(char s) {
		Operator out = null;
		
		if(s == '*')
			out = new Prod();
		
		return out;
	}

	@Override
	public String toString() {
		return "*";
	}

}
