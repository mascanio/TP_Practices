package tp.pr4.mv;

import tp.pr4.mv.instruction.InstructionExecutionException;

@SuppressWarnings("serial")
public class MemoryException extends InstructionExecutionException {

	public MemoryException(String message) {
		super(message);
	}

}
