package tp.pr5.mv.model.instruction.NumericCond;

import tp.pr5.mv.model.instruction.Instruction;

public class GreaterThan extends NumericCond {


	@Override
	protected boolean compare(int cima, int subcima) {
		return subcima > cima;
	}

	@Override
	public Instruction parse(String[] s) {
		GreaterThan out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("GT")) //$NON-NLS-1$
			out = new GreaterThan();
		//else return null
		
		return out;
	}
	public String toString(){
		return "GT"; //$NON-NLS-1$
	}

}
