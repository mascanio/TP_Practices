package tp.pr2.mv.instruction.NumericCond;

import tp.pr2.mv.instruction.Instruction;

public class GreaterThan extends NumericCond {


	@Override
	protected boolean compare(int cima, int subcima) {
		return subcima > cima;
	}

	@Override
	public Instruction parse(String[] s) {
		GreaterThan out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("GT"))
			out = new GreaterThan();
		//else return null
		
		return out;
	}
	public String toString(){
		return "GT";
	}

}
