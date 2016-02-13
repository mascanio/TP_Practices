package tp.pr2.mv;

import java.util.Scanner;

import tp.pr2.mv.commandInterpreter.CommandInterpreter;
import tp.pr2.mv.commandInterpreter.CommandParser;
import tp.pr2.mv.instruction.Instruction;
import tp.pr2.mv.instruction.InstructionParser;
import tp.pr2.mv.instruction.Expresion.Expresion;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public class Main {

	static private Scanner sc;

	/**
	 * Método estático que lee un programa por consola, pidiento instrucciones al usuario
	 * @return ProgramMV el programa introducido
	 */
	static private ProgramMv readProgram() {
		ProgramMv program = new ProgramMv();

		String instr = "";
		Instruction parseredInstruction = null;

		System.out.println("Introduce el programa fuente");

		do {
			System.out.print("> ");
			instr = sc.nextLine();

			parseredInstruction = InstructionParser.parseInstruction(instr);

			while (!instr.equalsIgnoreCase("END")
					&& parseredInstruction == null) {

				System.out.println("Instrucción incorrecta");
				System.out.print("> ");

				instr = sc.nextLine();
				parseredInstruction = InstructionParser.parseInstruction(instr);
			}

			if (!instr.equalsIgnoreCase("END")) {
				if (!instr.substring(0, 2).equalsIgnoreCase("EX"))
					program.push(parseredInstruction);
				else
					program.pushExp((Expresion) parseredInstruction);
			}
				

		} while (!instr.equalsIgnoreCase("END"));

		return program;
	}

	public static void main(String[] args) {

		sc = new Scanner(System.in);

		ProgramMv program = readProgram();
		System.out.println(program.toString());

		CPU cpu = new CPU();
		cpu.loadProgram(program);
		CommandInterpreter.configureCommandInterpreter(cpu);

		String commandInstruction = "";

		CommandInterpreter parseredCommand;

		boolean out = false;

		do {
			System.out.print("> ");
			commandInstruction = sc.nextLine();

			parseredCommand = CommandParser.readCommandInstruction(commandInstruction);

			while (parseredCommand == null) {

				System.out.println("Error, comando no válido");
				System.out.print("> ");

				commandInstruction = sc.nextLine();
				parseredCommand = CommandParser
						.readCommandInstruction(commandInstruction);
			}

			parseredCommand.executeCommand();
			out = parseredCommand.isFinished();
			
		} while (!out);

		sc.close();

	}
}