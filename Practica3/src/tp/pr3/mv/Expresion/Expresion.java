package tp.pr3.mv.Expresion;

import java.util.ArrayList;

import tp.pr3.mv.Expresion.component.Operand;
import tp.pr3.mv.instruction.Instruction;

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
