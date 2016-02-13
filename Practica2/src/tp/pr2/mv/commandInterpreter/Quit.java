package tp.pr2.mv.commandInterpreter;

public class Quit extends CommandInterpreter {

	@Override
	/**
	 * Finaliza la ejecución de comandos
	 */
	public boolean executeCommand() {

		this.isFinished = true;

		return true;
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Quit out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("QUIT"))
			out = new Quit();
		// else return null

		return out;
	}

}
