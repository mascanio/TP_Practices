package tp.pr2.mv.instruction.NumericCond;

import tp.pr2.mv.instruction.Instruction;

public class Equals extends NumericCond {

	@Override
	protected boolean compare(int cima, int subcima) {
		return cima == subcima;
	}

	@Override
	public Instruction parse(String[] s) {
		Equals out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("EQ"))
			out = new Equals();
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "EQ";
	}

}
