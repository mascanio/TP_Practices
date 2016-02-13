package tp.pr3.mv.commandInterpreter;

import java.io.IOException;

import tp.pr3.mv.CPU;
import tp.pr3.mv.CpuIsHaltedException;
import tp.pr3.mv.CpuPCOverflowException;
import tp.pr3.mv.instruction.InstructionExecutionException;

/**
 * Clase para el interprete de comandos, que se encargara de procesar los
 * comandos del usuario en modo interactivo
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
abstract public class CommandInterpreter {

	protected static CPU cpu;

	/**
	 * @param c
	 *            : la cpu en la que se va a trabajar
	 */
	public static void configureCommandInterpreter(CPU c) {
		cpu = c;
	}

	/**
	 * Se implementará la ejecución del comando instanciado como proceda
	 * 
	 * @throws CommandInterpreterException
	 *             si ocurre un error en la ejecucion del comando
	 * @throws InstructionExecutionException
	 *             si ocurre un error en la ejecución de una instrucción
	 * @throws CpuPCOverflowException
	 *             si el contador de programa se sale del final
	 * @throws CpuIsHaltedException
	 *             si la cpu ha ejecutado halt
	 * @throws IOException
	 *             si ocurre algún problema en la entrada/salida
	 */
	public abstract void executeCommand() throws CommandInterpreterException,
			InstructionExecutionException, CpuPCOverflowException,
			CpuIsHaltedException, IOException;

	/**
	 * Parsea el comando
	 * 
	 * @return puntero al nuevo objeto instanciado referente a s; null si no
	 *         existe
	 */
	abstract public CommandInterpreter parse(String[] s);
}
