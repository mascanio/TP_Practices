package tp.pr2.mv.commandInterpreter;

public class Steps extends Step {

	private int n;

	public Steps(int n) {
		super();
		this.n = n;
	}

	public Steps() {
	}

	/**
	 * Ejecuta las n-siguientes instrucciones
	 */
	public boolean executeCommand() {
		int i = 0;

		while (i < this.n && super.executeCommand())
			i++;

		return i == this.n; // Si se llega a recorrer todas las instrucciones
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Steps out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("STEPS"))
			out = new Steps(Integer.parseInt(s[1]));
		// else return null

		return out;
	}
}