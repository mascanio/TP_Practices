package tp.pr4.mv.input;

import java.io.IOException;

public class ConsoleImput implements InMethod {

	public ConsoleImput() {
	}

	@Override
	public int readChar() throws IOException {
		int r = System.in.read();
		return r == END_OF_CONSOLE_STREAM ? -1 : r;
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
		return "Console input" ;
	}

}
