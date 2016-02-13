package tp.pr3.mv.instruction.BooleanCond;

import tp.pr3.mv.instruction.Instruction;

public class And extends BooleanCond {

	@Override
	protected void execute(int n1, int n2) {			
		this.result = (n1 != 0 && n2 != 0);
	}
	
	@Override
	public Instruction parse(String[] s) {
		And out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("AND"))
			out = new And();
		//else return null
		
		return out;
	}
	
	public String toString() {
		return "AND";
	}
}
