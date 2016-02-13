package tp.pr5.mv.model.input;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class FileInput implements InMethod {

	private FileReader file;
	private String fileName;

	public FileInput() {
	}

	public FileInput(String f) {
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

	@Override
	public String getName() {
		return fileName;
	}

}
