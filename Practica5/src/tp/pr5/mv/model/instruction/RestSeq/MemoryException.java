package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.instruction.InstructionExecutionException;

@SuppressWarnings("serial")
public class MemoryException extends InstructionExecutionException {

	public MemoryException(String message) {
		super(message);
	}

}
