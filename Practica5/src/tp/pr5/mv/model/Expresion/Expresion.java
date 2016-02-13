package tp.pr5.mv.model.Expresion;

import java.util.ArrayList;

import tp.pr5.mv.model.Expresion.component.Operand;
import tp.pr5.mv.model.instruction.Instruction;

/**
 * Clase usada para el evaluador de expresiones ariméticas
 * 
 * @author Miguel Ascanio Gómez
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
