package tp.pr4.mv.Expresion.component.operator;

import java.util.ArrayList;

import tp.pr4.mv.Expresion.Result;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionParser;

public class Div extends Operator {

	public Div(){
		this.prior = 1;
	}
	
	@Override
	public void solve() {
		ArrayList<Instruction> list = new ArrayList<>();
		list.addAll(this.opIzq.getResult());
		list.addAll(this.opDer.getResult());
		list.add (InstructionParser.parseInstruction("DIV"));
		
		this.result = new Result(list);
	}


	@Override
	public Operator parse(char s) {
		Operator out = null;
		
		if(s == '/')
			out = new Div();
		
		return out;
	}

	@Override
	public String toString() {
		return "/";
	}
}
