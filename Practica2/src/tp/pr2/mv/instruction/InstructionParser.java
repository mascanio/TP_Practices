package tp.pr2.mv.instruction;

import tp.pr2.mv.instruction.Arithmetics.*;
import tp.pr2.mv.instruction.BooleanCond.*;
import tp.pr2.mv.instruction.Expresion.Expresion;
import tp.pr2.mv.instruction.Jumps.*;
import tp.pr2.mv.instruction.NumericCond.*;
import tp.pr2.mv.instruction.RestSeq.*;

public class InstructionParser {
	
	private static Instruction instructionSet[] = {
		new Expresion(),
		new Add(), new Sub(), new Mul(), new Div(), 
		new And(), new Or(), new Not(),
		new BF(), new BT(), new Jump(),
		new RBF(), new RBT(), new RJump(),
		new Equals(), new GreaterThan(), new LessOrEqual(), new LessThan(),
		new Dup(), new Flip(), new Halt(), new Load(), new Neg(), new Out(), new Pop(), new Push(), new Store(),
		
	};
	
	/**
	 * 
	 * @param instruction
	 * @return objeto instanciado de la clase referente a instruccion
	 */
	public static Instruction parseInstruction (String instruction){
		
		Instruction out = null;
		
		//Divido la string a parsear
		String[] split;	
		split = instruction.split(" "); //split[0] contiene el nombre de la instrucción, split[1] contiene el parámetro (si procede)
		
		int i = 0;
		boolean stop = false;
		
		try
		{
			while (i < InstructionParser.instructionSet.length && !stop) {
				out = InstructionParser.instructionSet[i].parse(split);
				if (out != null)
					stop = true;
				else
					i++;
			}
		} catch (NumberFormatException e){
			return null;
		} catch (StringIndexOutOfBoundsException e2) {
			return null;
		}
		
		return out;		
	}
}
