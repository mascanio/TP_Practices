package tp.pr4.mv.output;

public class NullOutput implements OutMethod {

	public NullOutput() {
	}

	@Override
	public void writeChar(int c) {
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
		return "NULL output";
	}

}
