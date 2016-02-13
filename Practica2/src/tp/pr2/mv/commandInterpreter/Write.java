package tp.pr2.mv.commandInterpreter;

public class Write extends CommandInterpreter {

	private int pos, value;

	public Write(int pos, int value) {
		this.pos = pos;
		this.value = value;
	}

	public Write() {
	}

	@Override
	/**
	 * Escribe en la posicion de memoria pos el dato value
	 * estos datos se actualizan en el parseo
	 */
	public boolean executeCommand() {
		cpu.memWrite(pos, value);

		return true;
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Write out = null;

		if (s.length == 3 && s[0].equalsIgnoreCase("write"))
			out = new Write(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
		// else return null

		return out;
	}

}
