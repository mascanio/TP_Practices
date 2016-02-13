package tp.pr5.mv.controler.interactiveCommandInterpreter;

import java.io.IOException;

import tp.pr5.mv.model.CpuIsHaltedException;
import tp.pr5.mv.model.CpuPCOverflowException;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

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

		if (s.length == 1 && s[0].equalsIgnoreCase("RUN")) //$NON-NLS-1$
			out = new Run();
		// else return null

		return out;
	}
}
