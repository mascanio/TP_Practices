package tp.pr3.mv.input;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interfaz para implementar los distintos métodos de entrada al sistema
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public interface InMethod {

	/**
	 * Caracter final de línea
	 */
	public static final int END_OF_CONSOLE_STREAM = 10;

	/**
	 * Abre el stream de lectura
	 * 
	 * @throws FileNotFoundException
	 *             si el archivo no se encuentra (en caso de lectura por
	 *             archivo)
	 */
	void open() throws FileNotFoundException;

	/**
	 * Resetea el stream de lectura
	 * 
	 * @throws FileNotFoundException
	 *             si el archivo no se encuentra (en caso de lectura por
	 *             archivo)
	 * @throws IOException
	 *             si ocurre algún problema al cerrarlo (ya estaba cerrado)
	 */
	void reset() throws FileNotFoundException, IOException;

	/**
	 * Cierra el stream de lectura
	 * 
	 * @throws IOException
	 *             si ocurre algún problema al cerrarlo (ya estaba cerrado)
	 */
	void close() throws IOException;

	/**
	 * Método que lee del stream el siguiente caracter. Duevuelve -1 si se llega
	 * al final de la lectura (fin de archivo, fin de línea, caracter 10)
	 * 
	 * @return El siguiente caracter del stream
	 * @throws IOException
	 *             si ocurre algún problema en la lectura
	 */
	int readChar() throws IOException;
}
