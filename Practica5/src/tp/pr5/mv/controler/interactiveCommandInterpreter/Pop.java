package tp.pr5.mv.controler.interactiveCommandInterpreter;

import tp.pr5.mv.model.StackException;

/**
 * Comando para eliminar la cima de la pila
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public class Pop extends CommandInterpreter {

	/**
	 * Elimina elemento cima de la pila
	 * 
	 * @throws CommandInterpreterRuntimeException
	 *             si ocurre error al eliminar de la pila
	 */
	@Override
	public void executeCommand() throws CommandInterpreterRuntimeException {
		try {
			cpu.externalPop();
		} catch (StackException e) {
			throw new CommandInterpreterRuntimeException(e.getMessage());
		}

	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Pop out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("POP")) //$NON-NLS-1$
			out = new Pop();
		// else return null

		return out;
	}

}
