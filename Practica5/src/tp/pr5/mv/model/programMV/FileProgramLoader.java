package tp.pr5.mv.model.programMV;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionParseException;
import tp.pr5.mv.model.instruction.InstructionParser;
import tp.pr5.mv.view.msg.Messages;

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
			throw new FileNotFoundException(Messages.getString("FileProgramLoader.0") //$NON-NLS-1$
					+ file.getName() + Messages.getString("FileProgramLoader.1")); //$NON-NLS-1$
		}
		try {
			int i = 0;
			String line = ""; //$NON-NLS-1$
			Instruction parseredInstruction = null;

			while (fileSC.hasNext() && !line.equalsIgnoreCase("END")) { //$NON-NLS-1$
				// ignorar intros
				do {
					line = fileSC.nextLine();
				} while (line.isEmpty());
				// Ignorar espacios
				while (line.charAt(0) == ' ')
					line = line.replaceFirst(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
				// Ignora CommentCharacter
				if (line.contains(CommentCharacter.toString()))
					line = line.substring(0, line.indexOf(CommentCharacter));

				if (!line.isEmpty() && !line.equalsIgnoreCase("END")) { //$NON-NLS-1$
					parseredInstruction = InstructionParser
							.parseInstruction(line);
					if (parseredInstruction == null) {
						throw new InstructionParseException(
								Messages.getString("FileProgramLoader.2") + line //$NON-NLS-1$
										+ Messages.getString("FileProgramLoader.3") + i); //$NON-NLS-1$
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
