package tp.pr2.mv.instruction.Expresion.component.operator;

import java.util.ArrayList;

import tp.pr2.mv.instruction.Instruction;
import tp.pr2.mv.instruction.InstructionParser;
import tp.pr2.mv.instruction.Expresion.Result;

public class Plus extends Operator {

	public Plus(){
		this.prior = 0;
	}
	
	@Override
	public void solve() {
		ArrayList<Instruction> list = new ArrayList<>();
		list.addAll(this.opIzq.getResult());
		list.addAll(this.opDer.getResult());
		list.add (InstructionParser.parseInstruction("ADD"));
		
		this.result = new Result(list);
	}


	@Override
	public Operator parse(char s) {
		Operator out = null;
		
		if(s == '+')
			out = new Plus();
		
		return out;
	}

	@Override
	public String toString() {
		return "+";
	}

}
