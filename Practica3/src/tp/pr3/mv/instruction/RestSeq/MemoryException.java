package tp.pr3.mv.instruction.RestSeq;

import tp.pr3.mv.instruction.InstructionExecutionException;

@SuppressWarnings("serial")
public class MemoryException extends InstructionExecutionException {

	public MemoryException(String message) {
		super(message);
	}

}
