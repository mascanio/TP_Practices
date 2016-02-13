package tp.pr5.mv.controler.interactiveCommandInterpreter;

import java.io.IOException;

import tp.pr5.mv.model.CpuIsHaltedException;
import tp.pr5.mv.model.CpuPCOverflowException;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

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

		//if(!cpu.isHalt())			
			cpu.step();			
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Step out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("STEP")) //$NON-NLS-1$
			out = new Step();
		// else return null

		return out;
	}
}
