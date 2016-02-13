package tp.pr2.mv.instruction.Arithmetics;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public abstract class Arithmetics extends Instruction {

	protected int result;

	abstract protected boolean execute(int cima, int subcima);

	/**
	 * realiza la operación aritmética referente al objeto intanciado.
	 * @return true si se puede ejecutar
	 */
	public boolean execute(CPU cpu) {
		boolean out = false;

		if (cpu.getStackSize() >= 2) {
			int cima = cpu.pop();
			int subcima = cpu.pop();

			if (this.execute(cima, subcima)) {
				cpu.push(this.result);
				out = true;
			} else {
				cpu.push(subcima);
				cpu.push(cima);
			}
		}
		return out;
	}
}
