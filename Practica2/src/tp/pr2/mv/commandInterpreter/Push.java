package tp.pr2.mv.commandInterpreter;

public class Push extends CommandInterpreter {

	private int n;

	public Push(int n) {
		this.n = n;
	}

	public Push() {
	}

	@Override
	/**
	 * Apila en la cima el elemento con el que se ejecutó el comando
	 */
	public boolean executeCommand() {
		cpu.push(n);

		return true;
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Push out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("PUSH"))
			out = new Push(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

}
