package tp.pr3.mv.commandInterpreter;

import tp.pr3.mv.MemoryException;

/**
 * Comando para escribir en memoria
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 */
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
	 * @throws CommandInterpreterRuntimeException
	 *             si ocurre error al escribir de la pila
	 */
	public void executeCommand() throws CommandInterpreterException {
		try {
			cpu.memWrite(pos, value);
		} catch (MemoryException e) {
			throw new CommandInterpreterException(e.getMessage());
		}
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
