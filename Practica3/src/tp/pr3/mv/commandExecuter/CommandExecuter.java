package tp.pr3.mv.commandExecuter;

import tp.pr3.mv.CPU;

/**
 * Interfaz que la implementarar las clases de los distintos modos de ejecución
 * de comandos
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public interface CommandExecuter {

	void run(CPU cpu) throws CommandExecuterException;
}
