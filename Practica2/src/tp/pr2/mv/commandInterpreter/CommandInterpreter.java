package tp.pr2.mv.commandInterpreter;

import tp.pr2.mv.CPU;

abstract public class CommandInterpreter {

	// Comprueba la ejecuci�n de Quit o el final de la ejecucion del comando
	protected boolean isFinished;
	protected static CPU cpu;

	public CommandInterpreter() {
		this.isFinished = false;
	}

	/**
	 * @param c
	 *            : la que se va a trabajar
	 */
	public static void configureCommandInterpreter(CPU c) {
		cpu = c;
	}

	/**
	 * Se implementar� la ejecuci�n del comando instanciado como proceda
	 * 
	 * @return true si el comando ejecut� correctamente
	 */
	public abstract boolean executeCommand();

	/**
	 * 
	 * @return true si se ha finalizado la ejecuci�n del comando
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * Muestra por pantalla el estado de la m�quina
	 */
	public static void printStateMachine() {
		System.out.println(cpu);
	}

	/**
	 * Se implementar� el parseo del comando instanciado como proceda
	 * 
	 * @return puntero al nuevo objeto instanciado referente a s; null si no
	 *         existe
	 */
	abstract public CommandInterpreter parse(String[] s);
}
