package tp.pr3.mv.commandInterpreter;

import tp.pr3.mv.OperandStackException;

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
			cpu.pop();
		} catch (OperandStackException e) {
			throw new CommandInterpreterRuntimeException(e.getMessage());
		}

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
