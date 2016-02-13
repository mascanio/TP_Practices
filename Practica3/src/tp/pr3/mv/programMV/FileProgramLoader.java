package tp.pr3.mv.programMV;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.InstructionParseException;
import tp.pr3.mv.instruction.InstructionParser;

/**
 * Cargador de programa desde archivo
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public class FileProgramLoader implements ProgramLoader {

	static private final Character CommentCharacter = ';';

	String asmFile;

	public FileProgramLoader(String file) {
		this.asmFile = file;
	}

	@Override
	public ProgramMV getProgram() throws FileNotFoundException,
			InstructionParseException {
		ProgramMV program = new ProgramMV();

		Scanner fileSC = null;
		try {
			fileSC = new Scanner(new FileReader(asmFile));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Error, no el archivo asm \""
					+ asmFile + "\" no se encuentra.");
		}
		try {
			int i = 0;
			String line = "";
			Instruction parseredInstruction = null;

			while (fileSC.hasNext() && !line.equalsIgnoreCase("END")) {
				// ignorar intros
				do {
					line = fileSC.nextLine();
				} while (line.isEmpty());
				// Ignorar espacios
				while (line.charAt(0) == ' ')
					line = line.replaceFirst(" ", "");
				// Ignora CommentCharacter
				if (line.contains(CommentCharacter.toString()))
					line = line.substring(0, line.indexOf(CommentCharacter));

				if (!line.isEmpty() && !line.equalsIgnoreCase("END")) {
					parseredInstruction = InstructionParser
							.parseInstruction(line);
					if (parseredInstruction == null) {
						// TODO
						throw new InstructionParseException(
								"Error: instrucción no válida: \"" + line
										+ "\" en línea " + i);
					}
					program.push(parseredInstruction);
				}
				i++;
			}
		} finally {
			fileSC.close();
		}

		return program;
	}
}
