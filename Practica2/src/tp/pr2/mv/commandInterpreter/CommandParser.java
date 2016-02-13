package tp.pr2.mv.commandInterpreter;

public class CommandParser {

	private static CommandInterpreter instructionSet[] = { new Step(),
			new Run(), new Steps(), new Pop(), new Push(), new Write(),
			new Quit() };

	/**
	 * Parseador de comandos
	 * 
	 * @param s
	 *            : comadno a parsear
	 * @return comando parseado
	 */
	public static CommandInterpreter readCommandInstruction(String s) {

		CommandInterpreter out = null;

		// Divido la string a parsear
		String[] split;
		split = s.split(" "); // split[0] contiene el nombre de la instrucción,
								// split[1] contiene el parámetro (si procede)

		int i = 0;
		boolean stop = false;
		while (i < CommandParser.instructionSet.length && !stop) {
			out = CommandParser.instructionSet[i].parse(split);
			if (out != null)
				stop = true;
			else
				i++;
		}

		return out;
	}

}
