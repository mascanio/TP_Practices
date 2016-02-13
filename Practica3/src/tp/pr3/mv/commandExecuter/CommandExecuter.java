package tp.pr3.mv.commandExecuter;

import tp.pr3.mv.CPU;

/**
 * Interfaz que la implementarar las clases de los distintos modos de ejecuci�n
 * de comandos
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 */
public interface CommandExecuter {

	void run(CPU cpu) throws CommandExecuterException;
}
