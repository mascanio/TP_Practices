package tp.pr4.mv.programMV;

import java.util.Scanner;

import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionParser;

/**
 * Clase para cargar el programa desde consola. Se pinden instrucciones hasta
 * llegar a un end
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public class ConsoleProgramLoader implements ProgramLoader {

	public ConsoleProgramLoader() {
	}

	@Override
	public ProgramMV getProgram() {
		ProgramMV program = new ProgramMV();

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

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
				program.push(parseredInstruction);
			}

		} while (!instr.equalsIgnoreCase("END"));

		return program;
	}

}
