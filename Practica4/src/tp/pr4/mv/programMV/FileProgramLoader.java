package tp.pr4.mv.programMV;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionParseException;
import tp.pr4.mv.instruction.InstructionParser;

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

	public static ProgramMV getProgram(File file) throws FileNotFoundException, InstructionParseException {

		ProgramMV program = new ProgramMV();

		Scanner fileSC = null;
		try {
			fileSC = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Error, el archivo asm \""
					+ file.getName() + "\" no se encuentra.");
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
	
	@Override
	public ProgramMV getProgram() throws FileNotFoundException,
			InstructionParseException {

		return getProgram(new File(asmFile));
	}
}
