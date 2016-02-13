package tp.pr3.mv.programMV;

import java.io.FileNotFoundException;

import tp.pr3.mv.instruction.InstructionParseException;

/**
 * Interfaz que implementara los distintos tipos de cargador de programa
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 */
public interface ProgramLoader {
	/**
	 * M�todo que lee el programa desde la entrada correspondiente y lo devuelve
	 * 
	 * @return el programa
	 * @throws FileNotFoundException
	 *             si el archivo de programa no se encuenrta (en caso de que sea
	 *             programa por asm file)
	 * @throws InstructionParseException
	 *             si alguna instrucci�n no es correcta
	 */
	ProgramMV getProgram() throws FileNotFoundException,
			InstructionParseException;
}
