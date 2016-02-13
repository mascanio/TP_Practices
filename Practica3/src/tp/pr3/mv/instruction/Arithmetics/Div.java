package tp.pr3.mv.instruction.Arithmetics;

import tp.pr3.mv.instruction.Instruction;

public class Div extends Arithmetics {

	@Override
	protected void execute(int cima, int subcima) {
		if (cima == 0)
			throw new IllegalArgumentException("Error: división por 0");
		this.result = subcima / cima;
		
	}

	@Override
	public Instruction parse(String[] s) {
		Div out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("DIV"))
			out = new Div();
		// else return null

		return out;
	}

	public String toString() {
		return "DIV";
	}

}
