package tp.pr3.mv.commandInterpreter;

import java.io.IOException;

import tp.pr3.mv.CpuIsHaltedException;
import tp.pr3.mv.CpuPCOverflowException;
import tp.pr3.mv.instruction.InstructionExecutionException;

/**
 * Comando para ejecutar todas las instrucciones
 * 
 * @author Miguel Ascanio G�emez
 * @author Guillermo Romero Alonso
 */
public class Run extends Step {

	/**
	 * Ejecuta el comando step hasta que una de estas falla, el contador de
	 * programa se s�le del limite o se ejecuta halt
	 * 
	 * @throws CommandInterpreterException
	 *             si el comando falla
	 * @throws InstructionExecutionException
	 *             si la instrucci�n falla
	 * @throws CpuPCOverflowException
	 *             si el ocntador de programa se sale del limite
	 * @throws CpuIsHaltedException
	 *             si se ha ejecutado halt
	 * @throws IOException
	 *             si ocurre alg�n problema en la entrada/salida
	 */
	public void executeCommand() throws InstructionExecutionException,
			CpuPCOverflowException, CpuIsHaltedException, IOException {

		cpu.reset();

		while (!cpu.isHalt()) {
			super.executeCommand();
		}
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Run out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("RUN"))
			out = new Run();
		// else return null

		return out;
	}
}
