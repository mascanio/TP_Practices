package tp.pr5.mv.model.programMV;

import java.util.Scanner;

import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionParser;
import tp.pr5.mv.view.msg.Messages;

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

		String instr = ""; //$NON-NLS-1$
		Instruction parseredInstruction = null;

		System.out.println(Messages.getString("ConsoleProgramLoader.0")); //$NON-NLS-1$

		do {
			System.out.print("> "); //$NON-NLS-1$
			instr = sc.nextLine();

			parseredInstruction = InstructionParser.parseInstruction(instr);

			while (!instr.equalsIgnoreCase("END") //$NON-NLS-1$
					&& parseredInstruction == null) {

				System.out.println(Messages.getString("ConsoleProgramLoader.1")); //$NON-NLS-1$
				System.out.print("> "); //$NON-NLS-1$

				instr = sc.nextLine();
				parseredInstruction = InstructionParser.parseInstruction(instr);
			}

			if (!instr.equalsIgnoreCase("END")) { //$NON-NLS-1$
				program.push(parseredInstruction);
			}

		} while (!instr.equalsIgnoreCase("END")); //$NON-NLS-1$

		return program;
	}

}
