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
	 * Método que pregunta al usuario si desea detener la ejecución, y en caso
	 * afirmativo detiene la máquina y el programa
	 */
	void quit() {
		if (0 == JOptionPane.showOptionDialog(new JFrame(),
				"¿Desea salir de la aplicación?", "Salir",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null)) {
			cpu.exit();
			System.exit(0);
		}
	}

	/**
	 * Método que detiene la ejecución y evita que se puedan pulsar los botones
	 * run y step.
	 * 
	 * @param error
	 *            indica si se detiene a causa de un error. Si false, se muestra
	 *            un mensaje de ejecución satisfactoria
	 */
	void stop(boolean error) {
		//view.disableButtons();
		stopped = true;
		if (!error) {
			view.disableButtons();
			JOptionPane.showMessageDialog(new JFrame(),
					"*** Ejecución satisfactoria ***", "",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Método que muestra por pantalla (como error) el error con título y
	 * mensaje seleccionados
	 * 
	 * @param msg
	 *            mensaje del error
	 * @param title
	 *            título del error
	 */
	void reportError(String msg, String title) {
		JOptionPane.showMessageDialog(new JFrame(), msg, title,
				JOptionPane.ERROR_MESSAGE);
		view.updateError(title + " // " + msg);
	}

	/**
	 * Pregunta al usuario si desea reiniciar la máquina
	 * 
	 * @return 0 si selecciona reinicio
	 */
	int askForReset() {
		return JOptionPane.showOptionDialog(new JFrame(),
				"Se reiniciará la máquina. ¿Continuar?", "",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);
	}

	/**
	 * Método que ejecuta la siguiente instrucción de la cpu. Se controla
	 * internamente lso errores que puedan ocurrir (error de ejecución de la
	 * instrucción, pc erróneo...)
	 * 
	 * Actualiza la vista
	 */
	void step() {
		stepWithoutUpdate();
		view.updateView();
	}

	/**
	 * Método que ejecuta la siguiente instrucción de la cpu. Se controla
	 * internamente lso errores que puedan ocurrir (error de ejecución de la
	 * instrucción, pc erróneo...)
	 * 
	 * No actualiza la vista
	 */
	private void stepWithoutUpdate() {
		try {
			cpu.step();
		} catch (InstructionExecutionException e) {
			reportError(e.getMessage(), "Error de ejecución");
			stop(true);
		} catch (CpuIsHaltedException e) {
			stop(false);
		} catch (CpuPCOverflowException e) {
			reportError(e.getMessage(), "Error en el contador de programa");
			stop(true);
		}
	}

	/**
	 * Método que ejecuta step() hasta que encuentre un error, o finalice la
	 * ejecución de todas las instrucciones (halt).
	 * 
	 * Se preguntará al usuario si desea continuar desde la isntrucción actual o
	 * si se debe reiniciar, y se actuará en consecuencia
	 */
	void run() {
		try {
			Object[] Options = { "Reiniciar", "Continuar" };
			if (0 == JOptionPane
					.showOptionDialog(
							new JFrame(),
							"¿Desea resetear la CPU para reiniciar la ejecución, o continuar la actual hasta el final?",
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
	 * Método que resetea la mv
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
	 * Método que ejecuta pop en la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecución
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
	 * Método que ejecuta push en la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecución
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
	 * Método que escribe en la memoria de la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecución
	 * 
	 * @param pos
	 *            posición en la que escribir
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
