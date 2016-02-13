package tp.pr4.mv.programMV;

import java.io.FileNotFoundException;

import tp.pr4.mv.instruction.InstructionParseException;

public class NullProgramLoader implements ProgramLoader {

	@Override
	public ProgramMV getProgram() throws FileNotFoundException,
			InstructionParseException {
		return new ProgramMV();
	}

}
