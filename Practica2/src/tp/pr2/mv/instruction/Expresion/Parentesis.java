package tp.pr2.mv.instruction.Expresion;

public class Parentesis {

	/**
	 * 
	 * @param s
	 * @return devuelve la string s eliminando los par�ntesis innecesarios
	 *         iniciales y finales, ejemplo: ((8+(2+3))) -> 8+(2+3)
	 */
	public static String ignoreParentesis(String s) {
		while (buscaParentesisFinal(s, 0) == s.length() - 1
				&& buscaParentesisInicial(s, s.length() - 1) == 0)
			s = s.substring(1, s.length() - 1);

		return s;
	}

	/**
	 * 
	 * @param s
	 *            : string en la que buscar el par�ntesis
	 * @param ini
	 *            : posici�n en la que se encuentra el par�ntesis inical
	 * @return la posici�n del par�ntesis que cierra el inicial, -1 si ini no es
	 *         un par�ntesis. Comportamiento no definido si el par�ntesis no se
	 *         cierra
	 */
	public static int buscaParentesisFinal(String s, int ini) {

		int cont = 0, out = ini;

		if (s.charAt(out) == '(') {
			cont++;
			out++;
			while (cont > 0) {
				if (s.charAt(out) == '(')
					cont++;
				else if (s.charAt(out) == ')')
					cont--;

				out++;
			}
		}

		else
			out = 0;

		return out - 1;
	}

	/**
	 * 
	 * @param s
	 *            : string en la que buscar el par�ntesis
	 * @param fin
	 *            : posici�n en la que se encuentra el par�ntesis final
	 * @return la posici�n del par�ntesis que abre el final, -1 si fin no es un
	 *         par�ntesis. Comportamiento no definido si el par�ntesis no se
	 *         abre
	 */
	public static int buscaParentesisInicial(String s, int fin) {

		int cont = 0, out = fin;

		if (s.charAt(out) == ')') {
			cont--;
			out--;
			while (cont < 0) {
				if (s.charAt(out) == '(')
					cont++;
				else if (s.charAt(out) == ')')
					cont--;

				out--;
			}
		}

		else
			out = -2;

		return out + 1;
	}
}
