package tp.pr4.mv.input;

public class NullInput implements InMethod {

	public NullInput() {
	}

	@Override
	public int readChar() {
		return -1;
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
		return "NULL input";
	}

}
