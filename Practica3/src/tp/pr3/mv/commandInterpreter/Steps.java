package tp.pr3.mv.commandInterpreter;

import java.io.IOException;

import tp.pr3.mv.CpuIsHaltedException;
import tp.pr3.mv.CpuPCOverflowException;
import tp.pr3.mv.instruction.InstructionExecutionException;

/**
 * Comando para ejecutar las n-siguientes instrucciones
 * 
 * @author Miguel Ascanio Góemez
 * @author Guillermo Romero Alonso
 */
public class Steps extends Step {

	private int n;

	public Steps(int n) {
		super();
		this.n = n;
	}

	public Steps() {
	}

	/**
	 * Ejecuta el comando step las n-siguientes instrucciones, o hasta que una
	 * de estas falla, el contador de programa se sále del limite o se ejecuta
	 * halt
	 * 
	 * @throws CommandInterpreterException
	 *             si el comando falla
	 * @throws InstructionExecutionException
	 *             si la instrucción falla
	 * @throws CpuPCOverflowException
	 *             si el ocntador de programa se sale del limite
	 * @throws CpuIsHaltedException
	 *             si se ha ejecutado halt
	 * @throws IOException
	 *             si ocurre algún problema en la entrada/salida
	 */
	public void executeCommand() throws InstructionExecutionException,
			CpuPCOverflowException, CpuIsHaltedException, IOException {
		int i = 0;

		while (i < this.n) {
			super.executeCommand();
			i++;
		}
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Steps out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("STEPS"))
			out = new Steps(Integer.parseInt(s[1]));
		// else return null

		return out;
	}
}