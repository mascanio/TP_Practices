package tp.pr3.mv.commandInterpreter;

import java.io.IOException;

import tp.pr3.mv.CpuIsHaltedException;
import tp.pr3.mv.CpuPCOverflowException;
import tp.pr3.mv.instruction.InstructionExecutionException;

/**
 * Comando para ejecutar la siguiente instrucción
 * 
 * @author Miguel Ascanio Góemez
 * @author Guillermo Romero Alonso
 */
public class Step extends CommandInterpreter {

	@Override
	/**
	 * Ejecuta el comando step de la CPU, ejecutando la instrucción a la que apunte PC
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

		cpu.step();

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
