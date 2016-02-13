package tp.pr3.mv.launcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tp.pr3.mv.Expresion.Expresion;
import tp.pr3.mv.commandExecuter.BatchCommandExecuter;
import tp.pr3.mv.commandExecuter.CommandExecuter;
import tp.pr3.mv.commandExecuter.InteractiveCommandExecuter;
import tp.pr3.mv.input.*;
import tp.pr3.mv.output.*;
import tp.pr3.mv.programMV.*;

/**
 * Clase utilizada para configurar la ejecucion de la mv
 * 
 * Contiene diferentes m�todos para la configuraci�n de los elementos de la MV
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 * 
 */
public class Launcher {

	public static final String newLine = System.getProperty("line.separator");

	private boolean asmFileSelected, iniFileSelected, outFileSelected,
			batchMode, onlyHelp;
	private String asmFile, iniFile, outFile;
	private String[] args;

	/**
	 * Constructor
	 * 
	 * @param s
	 *            argumentos de ejecuci�n de la aplicaci�n
	 */
	public Launcher(String[] s) {

		this.asmFileSelected = false;
		this.iniFileSelected = false;
		this.outFileSelected = false;
		this.batchMode = true;
		this.onlyHelp = true;

		this.args = s;
	}

	/**
	 * M�todo que configura this.launcher seg�n los argumentos this.args
	 * 
	 * @throws LaunchException
	 *             si alguno de los argumentos no es v�lido
	 */
	public void processArgs() throws LaunchException {

		try {
			int i = 0;
			while (i < args.length) {
				if (args[i].equalsIgnoreCase("-a")
						|| args[i].equalsIgnoreCase("--asm")) {
					// ASM file arg
					i++;
					asmFile = args[i];
					asmFileSelected = true;
					onlyHelp = false;
				} else if (args[i].equalsIgnoreCase("-h")
						|| args[i].equalsIgnoreCase("--help")) {
					// help arg
					System.out
							.println("usage: tp.pr3.mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]"
									+ newLine
									+ newLine
									+ "-a, --asm <asmfile> \t Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch."
									+ newLine
									+ "-h, --help \t\t Muestra esta ayuda."
									+ newLine
									+ "-i, --in <infile> \t Entrada del programa de la maquina-p."
									+ newLine
									+ "-m, --mode <mode> \t Modo de funcionamiento (batch | interactive). Por defecto, batch."
									+ newLine
									+ "-o, --out <outfile> \t Fichero donde se guarrda la salida del programa de la maquina-p."
									+ newLine
									+ "-e, -- expresion \t Para ejecuatr el evaluador de expresiones");
				} else if (args[i].equalsIgnoreCase("-i")
						|| args[i].equalsIgnoreCase("--in")) {
					// INI file arg
					i++;
					iniFile = args[i];
					iniFileSelected = true;
					onlyHelp = false;
				} else if (args[i].equalsIgnoreCase("-m")
						|| args[i].equalsIgnoreCase("--mode")) {
					// Mode arg
					i++;
					if (args[i].equalsIgnoreCase("interactive"))
						batchMode = false;
					else if (!args[i].equalsIgnoreCase("batch"))
						throw new LaunchException(
								"Error: modo seleccionado err�neo");
					onlyHelp = false;

				} else if (args[i].equalsIgnoreCase("-o")
						|| args[i].equalsIgnoreCase("--out")) {
					// OUT file arg
					i++;
					outFile = args[i];
					outFileSelected = true;
					onlyHelp = false;
				} else if (args[i].equalsIgnoreCase("-e")
						|| args[i].equalsIgnoreCase("--evaluator")) {
					
					System.out.println("Introduzca Instrucci�n a evaluar");
					
					Scanner sc = new Scanner(System.in);
					String aux = sc.nextLine();
					Expresion ex = new Expresion(aux);
					
					System.out.println(ex.getSetOfInstr());
					
					sc.close();
					
					throw new LaunchException(""); //S�lo evaluar
				} else {
					throw new LaunchException(
							"Error: argumento de ejecuci�n no v�lido.");
				}
				i++;
			}
			if (this.onlyHelp)
				throw new LaunchException("");
		} catch (IndexOutOfBoundsException e) {
			throw new LaunchException("Error: argumento de ejecuci�n no v�lido");
		}
	}

	/**
	 * M�todo que devuelve el objeto de control de stream de entrada configurado
	 * seg�n los argumentos de la aplciaci�n
	 * 
	 * @return objeto de control de stream de entrada configurado
	 * @throws FileNotFoundException
	 *             si fuera entrada pro archivo y este no existiera
	 */
	public InMethod configureIN() throws FileNotFoundException {
		InMethod r = null;

		if (batchMode) {
			if (iniFileSelected) {
				r = new FileImput(this.iniFile);
			} else {
				r = new ConsoleImput();
			}
		} else { // interactive mode
			if (iniFileSelected) {
				r = new FileImput(this.iniFile);
			} else {
				r = new NullImput();
			}
		}
		return r;
	}

	/**
	 * M�todo que devuelve el objeto de control de stream de salida configurado
	 * seg�n los argumentos de la aplciaci�n
	 * 
	 * @return objeto de control de stream de salida configurado
	 * @throws IOException
	 *             si ocurre alg�n error de IO
	 */
	public OutMethod configureOUT() throws IOException {
		OutMethod r = null;

		if (batchMode) {
			if (outFileSelected) {
				r = new FileOutput(this.outFile);
			} else {
				r = new ConsoleOutput();
			}
		} else { // interactive mode
			if (outFileSelected) {
				r = new FileOutput(this.outFile);
			} else {
				r = new NullOutput();
			}
		}
		return r;
	}

	/**
	 * M�todo que devuelve el cargador de programa configurado seg�n los
	 * argumentos de la aplicaci�n
	 * 
	 * @return el cargador de programa configurado
	 * @throws LaunchException
	 *             si se intenta generar un cargador ilegal (ejecuci�n batch sin
	 *             asm file)
	 */
	public ProgramLoader configureProgramLoader() throws LaunchException {
		ProgramLoader r = null;

		if (batchMode) {
			if (!asmFileSelected) {
				throw new LaunchException(
						"Error: no se ha especificado archivo ASM para modo batch");
			} else {
				r = new FileProgramLoader(asmFile);
			}
		} else { // Interactive mode
			if (!asmFileSelected)
				r = new ConsoleProgramLoader();
			else
				// Interactive with asm file
				r = new FileProgramLoader(asmFile);
		}

		return r;
	}

	/**
	 * M�todo que configura el ejecutor de comandos seg�n lso par�metros de la
	 * aplicaci�n
	 * 
	 * @return el ejecutor configurado
	 */
	public CommandExecuter configureCommandExecuter() {
		CommandExecuter r = null;

		if (batchMode)
			r = new BatchCommandExecuter();
		else
			r = new InteractiveCommandExecuter();

		return r;

	}

}
