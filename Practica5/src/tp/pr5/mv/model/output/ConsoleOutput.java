package tp.pr5.mv.model.output;

public class ConsoleOutput implements OutMethod {

	public ConsoleOutput() {
	}

	@Override
	public void writeChar(int c) {
		System.out.print((char) c);
	}

	@Override
	public void reset() {		
	}

	@Override
	public void close() {
	}

	@Override
	public void open() {
		
	}

	@Override
	public String getName() {
		return "Console output"; //$NON-NLS-1$
	}
}
