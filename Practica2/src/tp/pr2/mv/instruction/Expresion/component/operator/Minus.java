package tp.pr2.mv.instruction.Expresion.component.operator;

import java.util.ArrayList;

import tp.pr2.mv.instruction.Instruction;
import tp.pr2.mv.instruction.InstructionParser;
import tp.pr2.mv.instruction.Expresion.Result;

public class Minus extends Operator {

	public Minus(){
		this.prior = 0;
	}
	
	@Override
	public void solve() {
		ArrayList<Instruction> list = new ArrayList<>();
		list.addAll(this.opIzq.getResult());
		list.addAll(this.opDer.getResult());
		list.add (InstructionParser.parseInstruction("SUB"));
		
		this.result = new Result(list);
	}

	@Override
	public Operator parse(char s) {
		Operator out = null;
		
		if(s == '-')
			out = new Minus();
		
		return out;
	}

	@Override
	public String toString() {
		return "-";
	}

}
