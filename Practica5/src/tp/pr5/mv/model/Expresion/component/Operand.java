package tp.pr5.mv.model.Expresion.component;

import java.util.ArrayList;

import tp.pr5.mv.model.Expresion.Parentesis;
import tp.pr5.mv.model.Expresion.Result;
import tp.pr5.mv.model.Expresion.component.operator.Operator;

public class Operand extends Component {

	private String operand;

	/**
	 * Array que contiene la posición en el array components de los operadores
	 * que quedan por resolver, por orden de prioridad
	 */
	private ArrayList<Integer> arrangedOperators;

	/**
	 * Array que contiene loo componentes del operando, sean operadores u
	 * operandos
	 */
	private ArrayList<Component> components;

	public Operand(String s) {
		this.arrangedOperators = new ArrayList<>();
		this.components = new ArrayList<>();
		this.operand = Parentesis.ignoreParentesis(s).replaceAll(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$

		this.result = null;
	}

	public String getOperand() {
		return this.operand;
	}

	/**
	 * Resuelve el operando, guardando el result su valor total
	 */
	public void solve() {

		/*
		 * Se hace uso de una función recursiva
		 * 
		 * Básicamente se van parseando los elementos de la string operando. 
		 * Si se encuentra un paréntesis, se busca su cierre y se trata como un
		 * operando, se extra su resultado y se pone en el array de componentes
		 * Si es un operador, simplemente se añade al array. 
		 * Si es un operando básico (número) se añade
		 * 
		 * Después se resuelven los operadores, quedará un resultado 
		 * que será el resultado de este operando
		 */
		int i = 0;
		try { // Caso base
			this.result = new Result(Integer.parseInt(this.operand));
		} catch (NumberFormatException e) { // Paso recursivo

			while (i < this.operand.length()) {
				if (operand.charAt(i) == '(') {
					int fin = Parentesis.buscaParentesisFinal(this.operand, i);
					this.components.add(new Operand(this.operand.substring(i,
							fin + 1)));
					(this.components.get(this.components.size() - 1)).solve();
					i = fin;
				} else if (Operator.isOperator(operand.charAt(i))) {
					this.components.add(Operator.getOperatorFromChar(operand
							.charAt(i)));
				} else {
					String aux = nextStrInt(this.operand.substring(i,
							this.operand.length()));
					try {
						
						Integer.parseInt(aux);
					} catch (NumberFormatException e1) {
						throw new NumberFormatException();
					}
					this.components.add(new Operand(aux));
					(this.components.get(this.components.size() - 1)).solve();
					i += aux.length() - 1;
				}
				i++;
			}

			solveOperators();

			this.result = this.components.get(0).getResult();
		}

	}

	/**
	 * Resuelve los operadores
	 */
	private void solveOperators() {

		arrangeOperators();
		int numOperators = this.arrangedOperators.size();
		for (int j = 0; j < numOperators; j++) {
			// Resulevo los oreadores según su orden de prioridad en
			// arrangedOperators
			int thisOperator = this.arrangedOperators.get(0);
			// Añado los operandos al operator
			((Operator) this.components.get(thisOperator)).setOperands(
					(this.components.get(thisOperator - 1)).getResult(),
					(this.components.get(thisOperator + 1)).getResult());
			// Borro izquierda y derecha
			this.components.remove(thisOperator - 1);
			this.components.remove(thisOperator);
			this.components.get(thisOperator - 1).solve();
			// Quito el operador de la lista
			this.arrangedOperators.remove(0);
			arrangeOperators();
		}
	}

	/**
	 * Reordena el array de operadores como se define arriba
	 */
	private void arrangeOperators() {
		// Los operators siempre estan en las posiciones impares de component

		int indexOfLastAdded = -1, priorityOfLastAdded;
		Operator lastOperatorAdded;
		this.arrangedOperators.clear();
		int i = 1;
		while (i < this.components.size()) {
			// siguiente operator
			indexOfLastAdded = i;
			lastOperatorAdded = (Operator) this.components.get(i);
			priorityOfLastAdded = lastOperatorAdded.getPriorityOfOperator();

			int j = 0;
			while (j < this.arrangedOperators.size()
					&& priorityOfLastAdded <= ((Operator) this.components
							.get(this.arrangedOperators.get(j)))
							.getPriorityOfOperator()) {
				j++;
			}
			this.arrangedOperators.add(j, indexOfLastAdded);

			i += 2;
		}
	}

	/**
	 * Método auxiliar, devuelve un string que representa el siguiente número
	 * entero (no necesariamente de una cifra)
	 * 
	 * @param s
	 *            : String en la que bsucar
	 * @return String que representa al entero más cercano al inicio
	 */
	public static String nextStrInt(String s) {
		int i = 0;
		while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') { // No
																				// negativos
			i++;
		}

		return s.substring(0, i);
	}
}
