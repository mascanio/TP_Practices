package tp.pr3.mv.input;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class FileImput implements InMethod {

	private FileReader file;
	private String fileName;

	public FileImput() {
	}

	public FileImput(String f) {
		fileName = f;
		file = null;
	}

	@Override
	public int readChar() throws IOException, InputMismatchException {
		return file.read();
	}

	@Override
	public void reset() throws FileNotFoundException, IOException {
		this.close();
		this.open();
	}

	@Override
	public void close() throws IOException {
		file.close();
	}

	@Override
	public void open() throws FileNotFoundException {
		this.file = new FileReader(fileName);
	}

}
