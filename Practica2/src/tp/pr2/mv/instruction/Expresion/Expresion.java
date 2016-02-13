package tp.pr2.mv.instruction.Expresion;

import java.util.ArrayList;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;
import tp.pr2.mv.instruction.Expresion.component.Operand;

public class Expresion extends Instruction {

	private ArrayList<Instruction> setOfInstr;

	public Expresion() {
	}

	@Override
	public boolean execute(CPU cpu) {
		return false;
	}

	@Override
	/**
	 * Parsea la instrucción.
	 * Se inicializa el operando y se resuelve, guardando el
	 * resultado como un array de instrucciones
	 */
	public Instruction parse(String[] s) {
		Expresion out = null;

		if (s.length > 1 && s[0].equalsIgnoreCase("Ex")) {
			String aux = "";

			for (int i = 1; i < s.length; i++) {
				aux = aux.concat(s[i]);
			}
			Operand op = new Operand(aux);
			op.solve();

			this.setOfInstr = op.getResult().getResult();

			out = this;
		}
		// else return null

		return out;
	}

	public ArrayList<Instruction> getSetOfInstr() {
		return setOfInstr;
	}

}
