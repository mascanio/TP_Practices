package tp.pr4.mv.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOutput implements OutMethod {

	private String fileName;
	private FileWriter fichero;
	private PrintWriter pw;

	public FileOutput() {
	}

	public FileOutput(String file) {
		fileName = file;
	}

	@Override
	public void writeChar(int c) {
		pw.print((char) c);
	}

	@Override
	public void reset() throws IOException {
		this.close();
		this.open();
	}

	@Override
	public void close() throws IOException {
		fichero.close();
		pw.close();
	}

	@Override
	public void open() throws IOException {
		fichero = new FileWriter(fileName);
		pw = new PrintWriter(fichero);
	}

	@Override
	public String getName() {
		return fileName;
	}

}
