package tp.pr5.mv.model.output;

import java.io.IOException;

/**
 * Interfaz para implementar lso diferentes modos de flujo de saldia del sistema
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 */
public interface OutMethod {

	/**
	 * Abre el stream de salida
	 * 
	 * @throws IOException
	 *             si ocurre alg�n error al abrilo
	 */
	void open() throws IOException;

	/**
	 * resetea el stream de salida
	 * 
	 * @throws IOException
	 *             si ocurre alg�n error al resetearlo
	 */
	void reset() throws IOException;

	/**
	 * Cierra el stream de salida
	 * 
	 * @throws IOException
	 *             si ocurre alg�n error al cerrarlo
	 */
	void close() throws IOException;

	/**
	 * Env�a al stream de salida el car�cter pasado
	 * 
	 * @param c
	 *            caracter a enviar
	 */
	void writeChar(int c);
	
	/**
	 * 
	 * @return Nombre del stream que se est� usando, ser� NULL output, console
	 *         output o el nombre del archivo en caso de FileOutput
	 */
	String getName();

}
