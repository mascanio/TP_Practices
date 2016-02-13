package tp.pr5.mv.model.instruction.Arithmetics;

import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.view.msg.Messages;

public class Div extends Arithmetics {

	@Override
	protected void execute(int cima, int subcima) {
		if (cima == 0)
			throw new IllegalArgumentException(Messages.getString("Div.0")); //$NON-NLS-1$
		this.result = subcima / cima;
		
	}

	@Override
	public Instruction parse(String[] s) {
		Div out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("DIV")) //$NON-NLS-1$
			out = new Div();
		// else return null

		return out;
	}

	public String toString() {
		return "DIV"; //$NON-NLS-1$
	}

}
