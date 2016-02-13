package tp.pr2.mv.commandInterpreter;

public class Run extends Step {

	/**
	 * Ejecuta todas las instrucciones hasta encontrar un error de ejecución, se
	 * queda a la espera de otro comando
	 */
	public boolean executeCommand() {
		cpu.reset();

		while (super.executeCommand()) {
		} // Se ejecutan todas las instrucciones
			// hasta que una falle o salte halt

		return !cpu.isHalt(); // Si se ha salido del bucle anterior y no se ha
								// ejecutado halt, la instrucción anterior no
								// ejecutó bien
	}

	@Override
	public CommandInterpreter parse(String[] s) {
		Run out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("RUN"))
			out = new Run();
		// else return null

		return out;
	}
}
