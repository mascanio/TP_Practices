package tp.pr4.mv;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import tp.pr4.mv.Launcher.LaunchException;
import tp.pr4.mv.commandExecuter.CommandExecuter;
import tp.pr4.mv.gui.swing.GUIControler;
import tp.pr4.mv.gui.swing.MainWindow;
import tp.pr4.mv.instruction.InstructionParseException;
import tp.pr4.mv.programMV.ProgramLoader;
import tp.pr4.mv.programMV.ProgramMV;

/**
 * Clase principal
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public class Main {

	private static Launcher launcher;

	private static void windowMode() {
		ProgramLoader programloader = null;
		try {
			programloader = launcher.configureProgramLoader();
		} catch (LaunchException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(),
					"Error al cargar programa", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		ProgramMV program = null;
		try {
			program = programloader.getProgram();
		} catch (FileNotFoundException | InstructionParseException e1) {
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(),
					"Error al cargar programa", JOptionPane.ERROR_MESSAGE);
			System.exit(2);
		}

		CPU cpu = null;
		try {
			cpu = new CPU(launcher);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(),
					"Error de I/O", JOptionPane.ERROR_MESSAGE);
			System.exit(2);
		}
		cpu.loadProgram(program);

		GUIControler controler = new GUIControler(cpu);		
		MainWindow view = launcher.configureView(controler);
		controler.linkView(view);
		
		SwingUtilities.invokeLater(view);
	}

	private static void consoleMode() {
		ProgramLoader programloader = null;
		try {
			programloader = launcher.configureProgramLoader();
		} catch (LaunchException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

		ProgramMV program = null;
		try {
			program = programloader.getProgram();
		} catch (FileNotFoundException | InstructionParseException e1) {
			System.err.println(e1.getMessage());
			System.exit(2);
		}
		// System.out.println(program.toString());

		CPU cpu = null;
		try {
			cpu = new CPU(launcher);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

		cpu.loadProgram(program);

		CommandExecuter executer = launcher.configureCommandExecuter(cpu);

		try {
			executer.run(cpu);
		} catch (CommandExecuter.CommandExecuterException e) {
			System.err.println(e.getMessage());
			cpu.exit();
			System.exit(2);
		}
		executer.endOfMain(cpu);
	}

	public static void main(String[] args) {

		launcher = new Launcher(args);
		try {
			launcher.processArgs();
		} catch (LaunchException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

		if (launcher.isVisualMode()) {
			windowMode();
		} else {
			consoleMode();
		}
	}
}