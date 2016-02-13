package tp.pr3.mv.commandInterpreter;

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
		System.out.println(cpu);
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Push out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("PUSH"))
			out = new Push(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

}
