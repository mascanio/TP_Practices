package tp.pr4.mv.commandExecuter;

import tp.pr4.mv.CPU;
/**
 * Interfaz que la implementarar las clases de los distintos modos de ejecución
 * de comandos
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public interface CommandExecuter {

	@SuppressWarnings("serial")
	public class CommandExecuterException extends Throwable {

		public CommandExecuterException() {
		}

		public CommandExecuterException(String message) {
			super(message);
		}

	}
	
	void run(CPU cpu) throws CommandExecuterException;
	void endOfMain(CPU cpu);
}
