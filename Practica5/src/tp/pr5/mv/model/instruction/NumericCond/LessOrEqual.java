package tp.pr5.mv.model.instruction.NumericCond;

import tp.pr5.mv.model.instruction.Instruction;

public class LessOrEqual extends NumericCond {

	@Override
	protected boolean compare(int cima, int subcima) {
		return subcima <= cima;
	}

	@Override
	public Instruction parse(String[] s) {
		LessOrEqual out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("LE")) //$NON-NLS-1$
			out = new LessOrEqual();
		//else return null
		
		return out;
	}
	public String toString(){
		return "LE"; //$NON-NLS-1$
	}

}
