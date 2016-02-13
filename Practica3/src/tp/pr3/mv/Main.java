package tp.pr3.mv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tp.pr3.mv.commandExecuter.CommandExecuter;
import tp.pr3.mv.commandExecuter.CommandExecuterException;
import tp.pr3.mv.instruction.InstructionParseException;
import tp.pr3.mv.launcher.LaunchException;
import tp.pr3.mv.launcher.Launcher;
import tp.pr3.mv.programMV.ProgramLoader;
import tp.pr3.mv.programMV.ProgramMV;

/**
 * Clase principal
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		//Launcher launcher = new Launcher(sc.nextLine().split(" "));
		Launcher launcher = new Launcher(args);
		try {
			launcher.processArgs();
		} catch (LaunchException e) {
			System.err.println(e.getMessage());
			sc.close();
			System.exit(1);
		}

		ProgramLoader programloader = null;
		try {
			programloader = launcher.configureProgramLoader();
		} catch (LaunchException e) {
			System.err.println(e.getMessage());
			sc.close();
			System.exit(1);
		}

		ProgramMV program = null;
		try {
			program = programloader.getProgram();
		} catch (FileNotFoundException | InstructionParseException e1) {
			System.err.println(e1.getMessage());
			sc.close();
			System.exit(2);
		}
		System.out.println(program.toString());

		CPU cpu = null;
		try {
			cpu = new CPU(launcher);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			sc.close();
			System.exit(1);
		}

		cpu.loadProgram(program);

		CommandExecuter executer = launcher.configureCommandExecuter();

		try {
			executer.run(cpu);
		} catch (CommandExecuterException e) {
			System.err.println(e.getMessage());
			cpu.exit();
			sc.close();
			System.exit(2);
		}

		System.out.println("*** Ejecución satisfactoria ***");
		
		cpu.exit();
		sc.close();
	}
}