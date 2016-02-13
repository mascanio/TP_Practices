package tp.pr4.mv.Expresion;

import java.util.ArrayList;

import tp.pr4.mv.Expresion.component.Operand;
import tp.pr4.mv.instruction.Instruction;

/**
 * Clase usada para el evaluador de expresiones arim�ticas
 * 
 * @author Miguel Ascanio G�mez
 * 
 */
public class Expresion {

	private ArrayList<Instruction> setOfInstr;

	public Expresion(String s) {

		Operand op = new Operand(s);
		op.solve();

		this.setOfInstr = op.getResult().getResult();
	}

	public ArrayList<Instruction> getSetOfInstr() {
		return setOfInstr;
	}

}
