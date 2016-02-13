package tp.pr4.mv.instruction.NumericCond;

import tp.pr4.mv.instruction.Instruction;

public class LessThan extends NumericCond {

	@Override
	protected boolean compare(int cima, int subcima) {
		return subcima < cima;
	}

	@Override
	public Instruction parse(String[] s) {
		LessThan out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("LT"))
			out = new LessThan();
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "LT";
	}

}
