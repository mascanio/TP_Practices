package tp.pr4.mv.commandInterpreter;

import java.io.IOException;

import tp.pr4.mv.CpuIsHaltedException;
import tp.pr4.mv.CpuPCOverflowException;
import tp.pr4.mv.instruction.InstructionExecutionException;

/**
 * Comando para ejecutar la siguiente instrucci�n
 * 
 * @author Miguel Ascanio G�emez
 * @author Guillermo Romero Alonso
 */
public class Step extends CommandInterpreter {

	@Override
	/**
	 * Ejecuta el comando step de la CPU, ejecutando la instrucci�n a la que apunte PC
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

		if(showConsoleInfo && !cpu.isHalt())
			System.out.println("Comienza la ejecuci�n de "
					+ cpu.getCurrentInsrtuction());
		cpu.step();
		if(showConsoleInfo)
			System.out.println(cpu);			
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Step out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("STEP"))
			out = new Step();
		// else return null

		return out;
	}
}
