package tp.pr5.mv.model.instruction.BooleanCond;

import tp.pr5.mv.model.instruction.Instruction;

public class Or extends BooleanCond {

	@Override
	protected void execute(int n1, int n2) {			
		this.result = (n1 != 0 || n2 != 0);
	}

	public Instruction parse(String[] s) {
		Or out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("OR")) //$NON-NLS-1$
			out = new Or();
		//else return null
		
		return out;
	}
	
	public String toString() {
		return "OR"; //$NON-NLS-1$
	}
}
