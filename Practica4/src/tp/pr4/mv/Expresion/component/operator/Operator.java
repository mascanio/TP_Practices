package tp.pr4.mv.Expresion.component.operator;

import tp.pr4.mv.Expresion.Result;
import tp.pr4.mv.Expresion.component.Component;

public abstract class Operator extends Component {

	public static final Operator[] setOfOperators = { 
		new Div(), new Prod(),
			new Minus(), new Plus() };

	// Prioridad el operador, se establece en su instanciación
	protected int prior;

	protected Result opIzq, opDer;

	abstract public void solve();

	abstract public Operator parse(char s);

	abstract public String toString();

	public void setOperands(Result izq, Result der) {
		this.opIzq = izq;
		this.opDer = der;
	}

	/**
	 * 
	 * @param c
	 * @return Devuelve un objeto instanciado operador referente al carácter c
	 * 
	 */
	public static Operator getOperatorFromChar(char c) {
		Operator out = null;

		boolean stop = false;
		int i = 0;
		while (i < setOfOperators.length && !stop) {
			out = Operator.setOfOperators[i].parse(c);
			if (out != null)
				stop = true;
			else
				i++;
		}
		return out;
	}

	/**
	 * 
	 * @param c
	 * @return true si c es un operador válido
	 */
	public static boolean isOperator(char c) {

		int i = 0;
		while (i < setOfOperators.length && setOfOperators[i].parse(c) == null)
			i++;

		return i < setOfOperators.length;
	}

	public int getPriorityOfOperator() {
		return this.prior;
	}
}
