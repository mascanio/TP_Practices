package tp.pr5.mv.model.programMV;

import java.io.FileNotFoundException;

import tp.pr5.mv.model.instruction.InstructionParseException;

public class NullProgramLoader implements ProgramLoader {

	@Override
	public ProgramMV getProgram() throws FileNotFoundException,
			InstructionParseException {
		return new ProgramMV();
	}

}
