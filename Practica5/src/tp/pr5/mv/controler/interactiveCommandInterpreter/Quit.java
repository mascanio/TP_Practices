package tp.pr5.mv.controler.interactiveCommandInterpreter;

import tp.pr5.mv.view.msg.Messages;

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
				Messages.getString("Quit.0")); //$NON-NLS-1$
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Quit out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("QUIT")) //$NON-NLS-1$
			out = new Quit();
		// else return null

		return out;
	}

}
