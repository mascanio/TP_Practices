package tp.pr2.mv.commandInterpreter;

public class Pop extends CommandInterpreter {

	@Override
	/**
	 * Elimina la cima de la pila
	 */
	public boolean executeCommand() {
		boolean out = false;

		if (cpu.getStackSize() >= 1) {
			cpu.pop();
			out = true;
		}

		return out;
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Pop out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("POP"))
			out = new Pop();
		// else return null

		return out;
	}

}
