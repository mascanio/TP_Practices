package tp.pr5.mv.model.Expresion.component.operator;

import java.util.ArrayList;

import tp.pr5.mv.model.Expresion.Result;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionParser;

public class Prod extends Operator {

	public Prod(){
		this.prior = 1;
	}
	
	@Override
	public void solve() {
		ArrayList<Instruction> list = new ArrayList<>();
		list.addAll(this.opIzq.getResult());
		list.addAll(this.opDer.getResult());
		list.add (InstructionParser.parseInstruction("MULT")); //$NON-NLS-1$
		
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
		return "*"; //$NON-NLS-1$
	}

}
