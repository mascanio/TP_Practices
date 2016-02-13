package tp.pr4.mv.gui.swing;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr4.mv.CPU;
import tp.pr4.mv.CpuIsHaltedException;
import tp.pr4.mv.CpuPCOverflowException;
import tp.pr4.mv.MemoryException;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.input.InMethod;
import tp.pr4.mv.instruction.InstructionExecutionException;
import tp.pr4.mv.output.OutMethod;
import tp.pr4.mv.programMV.ProgramMV;

public class GUIControler {

	private CPU cpu;
	private MainWindow view;
	private boolean stopped;

	public GUIControler(CPU cpu) {
		this.cpu = cpu;
	}

	public void linkView(MainWindow view) {
		this.view = view;
	}

	/**
	 * M�todo que pregunta al usuario si desea detener la ejecuci�n, y en caso
	 * afirmativo detiene la m�quina y el programa
	 */
	void quit() {
		if (0 == JOptionPane.showOptionDialog(new JFrame(),
				"�Desea salir de la aplicaci�n?", "Salir",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null)) {
			cpu.exit();
			System.exit(0);
		}
	}

	/**
	 * M�todo que detiene la ejecuci�n y evita que se puedan pulsar los botones
	 * run y step.
	 * 
	 * @param error
	 *            indica si se detiene a causa de un error. Si false, se muestra
	 *            un mensaje de ejecuci�n satisfactoria
	 */
	void stop(boolean error) {
		//view.disableButtons();
		stopped = true;
		if (!error) {
			view.disableButtons();
			JOptionPane.showMessageDialog(new JFrame(),
					"*** Ejecuci�n satisfactoria ***", "",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * M�todo que muestra por pantalla (como error) el error con t�tulo y
	 * mensaje seleccionados
	 * 
	 * @param msg
	 *            mensaje del error
	 * @param title
	 *            t�tulo del error
	 */
	void reportError(String msg, String title) {
		JOptionPane.showMessageDialog(new JFrame(), msg, title,
				JOptionPane.ERROR_MESSAGE);
		view.updateError(title + " // " + msg);
	}

	/**
	 * Pregunta al usuario si desea reiniciar la m�quina
	 * 
	 * @return 0 si selecciona reinicio
	 */
	int askForReset() {
		return JOptionPane.showOptionDialog(new JFrame(),
				"Se reiniciar� la m�quina. �Continuar?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);
	}

	/**
	 * M�todo que ejecuta la siguiente instrucci�n de la cpu. Se controla
	 * internamente lso errores que puedan ocurrir (error de ejecuci�n de la
	 * instrucci�n, pc err�neo...)
	 * 
	 * Actualiza la vista
	 */
	void step() {
		stepWithoutUpdate();
		view.updateView();
	}

	/**
	 * M�todo que ejecuta la siguiente instrucci�n de la cpu. Se controla
	 * internamente lso errores que puedan ocurrir (error de ejecuci�n de la
	 * instrucci�n, pc err�neo...)
	 * 
	 * No actualiza la vista
	 */
	private void stepWithoutUpdate() {
		try {
			cpu.step();
		} catch (InstructionExecutionException e) {
			reportError(e.getMessage(), "Error de ejecuci�n");
			stop(true);
		} catch (CpuIsHaltedException e) {
			stop(false);
		} catch (CpuPCOverflowException e) {
			reportError(e.getMessage(), "Error en el contador de programa");
			stop(true);
		}
	}

	/**
	 * M�todo que ejecuta step() hasta que encuentre un error, o finalice la
	 * ejecuci�n de todas las instrucciones (halt).
	 * 
	 * Se preguntar� al usuario si desea continuar desde la isntrucci�n actual o
	 * si se debe reiniciar, y se actuar� en consecuencia
	 */
	void run() {
		try {
			Object[] Options = { "Reiniciar", "Continuar" };
			if (0 == JOptionPane
					.showOptionDialog(
							new JFrame(),
							"�Desea resetear la CPU para reiniciar la ejecuci�n, o continuar la actual hasta el final?",
							"", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, Options, null)) {
				cpu.reset();
			}
			stopped = false;
			while (!stopped)
				stepWithoutUpdate();
		} catch (IOException e) {
			reportError(
					"Error en la entrada/salida del sistema: " + e.getMessage(),
					"IO error");
			stop(true);
		}
		view.updateView();
	}

	/**
	 * M�todo que resetea la mv
	 */
	void reset() {
		try {
			view.enableButtons();
			cpu.reset();
		} catch (IOException e) {
			reportError(
					"Error en la entrada/salida del sistema: " + e.getMessage(),
					"IO error");
			stop(true);
		}
		view.updateView();
		view.updateError("");
	}

	/**
	 * M�todo que ejecuta pop en la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecuci�n
	 */
	void pop() {
		try {
			cpu.pop();
		} catch (OperandStackException e) {
			reportError(e.getMessage(), "Error de comando");
		}
		view.updateView();
	}

	/**
	 * M�todo que ejecuta push en la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecuci�n
	 * 
	 * @param s
	 *            elemento a introducir en la pila
	 */
	void push(String s) {
		try {
			cpu.push(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			reportError(
					"Error, variable del comando push no es convertible a int",
					"Error de comando");
		}
		view.updateView();
	}

	/**
	 * M�todo que escribe en la memoria de la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecuci�n
	 * 
	 * @param pos
	 *            posici�n en la que escribir
	 * @param value
	 *            dato a escribir
	 */
	void memorySet(String pos, String value) {
		try {
			cpu.memWrite(Integer.parseInt(pos), Integer.parseInt(value));
		} catch (NumberFormatException e) {
			reportError(
					"Error, variable del comando memorySet no es convertible a int",
					"Error de comando");
		} catch (MemoryException e) {
			reportError(e.getMessage(), "Error de comando");
		}
		view.updateView();
	}

	String getCpuProgram() {
		return cpu.getStringProgram();
	}

	int getProgramCounter() {
		return cpu.getProgramCounter();
	}

	Object[] getStackElements() {
		return cpu.getStackElements();
	}

	Object[] getMemoryCells() {
		return cpu.getMemoryCells();
	}

	InMethod getInput() {
		return cpu.getInput();
	}

	void setInput(InputPanel.InputGUI inputGUI) {
		cpu.setInput(inputGUI);
	}

	OutMethod getOutput() {
		return cpu.getOutput();
	}

	void setOutput(OutputPanel.OutputGUI outputGUI) {
		cpu.setOutput(outputGUI);
	}
	
	boolean isCpuPcCorrect() {
		return cpu.isPcCorrect();
	}

	// ADVANCED

	public void setProgram(ProgramMV program) {
		cpu.loadProgram(program);
		try {
			cpu.reset();
		} catch (IOException e) {
			reportError(
					"Error en la entrada/salida del sistema: " + e.getMessage(),
					"IO error");
			stop(true);
		}
	}
}
