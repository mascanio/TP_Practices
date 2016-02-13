package tp.pr5.mv;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr5.mv.Launcher.LaunchException;
import tp.pr5.mv.controler.Controler;
import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.instruction.InstructionParseException;
import tp.pr5.mv.model.programMV.ProgramLoader;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.View;

/**
 * Clase principal
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public class Main {

	private static Launcher launcher;

	public static void main(String[] args) {
		
		launcher = new Launcher(args);
		try {
			launcher.processArgs();
		} catch (LaunchException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
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

		CPU cpu = null;
		try {
			cpu = new CPU(launcher);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

		cpu.loadProgram(program);
		
		Controler ctrl = launcher.configureControler(cpu);
		View view = launcher.configureView(ctrl, cpu, cpu.getStackObservable(), cpu.getMemoryObserver());
		
		view.start();
		ctrl.start();
	}
}