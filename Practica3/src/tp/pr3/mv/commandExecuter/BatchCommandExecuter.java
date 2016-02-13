package tp.pr3.mv.commandExecuter;

import tp.pr3.mv.CPU;
import tp.pr3.mv.CpuIsHaltedException;
import tp.pr3.mv.CpuPCOverflowException;
import tp.pr3.mv.instruction.InstructionExecutionException;

/**
 * Clase para ejecutar comandos en modo batch
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public class BatchCommandExecuter implements CommandExecuter {

	public BatchCommandExecuter() {

	}

	/**
	 * Ejecuta la cpu en modo batch, ejecutando el programa hasta la instruccion
	 * Halt o hasta que ocurra un error
	 */
	@Override
	public void run(CPU cpu) throws CommandExecuterException {
		while (!cpu.isHalt())
			try {
				cpu.step();
			} catch (InstructionExecutionException | CpuPCOverflowException e) {
				throw new CommandExecuterException(e.getMessage());
			} catch (CpuIsHaltedException e) {
				//
			}
	}
}
