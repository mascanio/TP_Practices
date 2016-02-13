package tp.pr4.mv.commandInterpreter;

/**
 * Comando para detener la ejecución de comandos
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public class Quit extends CommandInterpreter {

	@Override
	/**
	 * Finaliza la ejecución de comandos
	 * @trhows CommandInterpreterException para indicar que se ha detenido la ejecución
	 */
	public void executeCommand() throws CommandInterpreterException {
		throw new CommandInterpreterException(
				"Ejecución detenida por el usuario");
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
