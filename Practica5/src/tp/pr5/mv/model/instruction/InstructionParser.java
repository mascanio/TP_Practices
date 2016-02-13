package tp.pr5.mv.model.instruction;

import tp.pr5.mv.model.instruction.Arithmetics.Add;
import tp.pr5.mv.model.instruction.Arithmetics.Div;
import tp.pr5.mv.model.instruction.Arithmetics.Mul;
import tp.pr5.mv.model.instruction.Arithmetics.Sub;
import tp.pr5.mv.model.instruction.BooleanCond.And;
import tp.pr5.mv.model.instruction.BooleanCond.Not;
import tp.pr5.mv.model.instruction.BooleanCond.Or;
import tp.pr5.mv.model.instruction.Jumps.BF;
import tp.pr5.mv.model.instruction.Jumps.BT;
import tp.pr5.mv.model.instruction.Jumps.Jump;
import tp.pr5.mv.model.instruction.Jumps.RBF;
import tp.pr5.mv.model.instruction.Jumps.RBT;
import tp.pr5.mv.model.instruction.Jumps.RJump;
import tp.pr5.mv.model.instruction.NumericCond.Equals;
import tp.pr5.mv.model.instruction.NumericCond.GreaterThan;
import tp.pr5.mv.model.instruction.NumericCond.LessOrEqual;
import tp.pr5.mv.model.instruction.NumericCond.LessThan;
import tp.pr5.mv.model.instruction.RestSeq.Dup;
import tp.pr5.mv.model.instruction.RestSeq.Flip;
import tp.pr5.mv.model.instruction.RestSeq.Halt;
import tp.pr5.mv.model.instruction.RestSeq.In;
import tp.pr5.mv.model.instruction.RestSeq.Load;
import tp.pr5.mv.model.instruction.RestSeq.LoadIND;
import tp.pr5.mv.model.instruction.RestSeq.Neg;
import tp.pr5.mv.model.instruction.RestSeq.Out;
import tp.pr5.mv.model.instruction.RestSeq.Pop;
import tp.pr5.mv.model.instruction.RestSeq.Push;
import tp.pr5.mv.model.instruction.RestSeq.Store;
import tp.pr5.mv.model.instruction.RestSeq.StoreIND;

/**
 * Clase del parseador de instrucciones
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public class InstructionParser {
	
	private static Instruction instructionSet[] = {
		new Add(), new Sub(), new Mul(), new Div(), 
		new And(), new Or(), new Not(),
		new BF(), new BT(), new Jump(),
		new RBF(), new RBT(), new RJump(),
		new Equals(), new GreaterThan(), new LessOrEqual(), new LessThan(),
		new Dup(), new Flip(), new Halt(), new Load(), new Neg(), 
		new Out(), new In(), new Pop(), new Push(), new Store(),
		new StoreIND(), new LoadIND()
	};

	/**
	 * 
	 * @param instruction String que será parseaado a Instruction
	 * @return objeto instanciado de la clase referente a instruccion
	 */
	public static Instruction parseInstruction(String instruction) {

		Instruction out = null;

		// Divido la string a parsear
		String[] split;
		split = instruction.split(" "); // split[0] contiene el nombre de la //$NON-NLS-1$
										// instrucción, split[1] contiene el
										// parámetro (si procede)

		int i = 0;
		boolean stop = false;

		try {
			while (i < InstructionParser.instructionSet.length && !stop) {
				out = InstructionParser.instructionSet[i].parse(split);
				if (out != null)
					stop = true;
				else
					i++;
			}
		} catch (NumberFormatException e) {
			return null;
		} catch (StringIndexOutOfBoundsException e2) {
			return null;
		}

		return out;
	}
}
