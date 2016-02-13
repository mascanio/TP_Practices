package tp.pr5.mv.controler.interactiveCommandInterpreter;

/**
 * Comando para apilar un elemento en la pila
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public class Push extends CommandInterpreter {

	private int n;

	public Push(int n) {
		this.n = n;
	}

	public Push() {
	}

	@Override
	/**
	 * Apila el elemento
	 */
	public void executeCommand() {
		cpu.push(n);
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Push out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("PUSH")) //$NON-NLS-1$
			out = new Push(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

}
