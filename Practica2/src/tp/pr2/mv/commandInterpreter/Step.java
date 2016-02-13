package tp.pr2.mv.commandInterpreter;

public class Step extends CommandInterpreter {

	@Override
	/**
	 * ejecuta la instrucción a la que apunte el PC
	 * si falla no acutaliza el PC
	 */
	public boolean executeCommand() {
		boolean out = cpu.step();

		this.isFinished = cpu.abortComputation();

		return out;
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Step out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("STEP"))
			out = new Step();
		// else return null

		return out;
	}
}
