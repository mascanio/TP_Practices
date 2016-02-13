package tp.pr4.mv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tp.pr4.mv.Expresion.Expresion;
import tp.pr4.mv.commandExecuter.BatchCommandExecuter;
import tp.pr4.mv.commandExecuter.CommandExecuter;
import tp.pr4.mv.commandExecuter.InteractiveCommandExecuter;
import tp.pr4.mv.commandInterpreter.CommandInterpreter;
import tp.pr4.mv.gui.swing.AdvacedMainWindow;
import tp.pr4.mv.gui.swing.GUIControler;
import tp.pr4.mv.gui.swing.MainWindow;
import tp.pr4.mv.gui.swing.StdMainWindow;
import tp.pr4.mv.input.*;
import tp.pr4.mv.output.*;
import tp.pr4.mv.programMV.*;

/**
 * Clase utilizada para configurar la ejecucion de la mv
 * 
 * Contiene diferentes métodos para la configuración de los elementos de la MV
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public class Launcher {

	public static final String newLine = System.getProperty("line.separator");

	public enum Mode {
		BATCH, INTERACTIVE, WINDOW, ADVANCED_WINDOW
	}

	/**
	 * Clase para las excepciones relacionadas con el Laucher
	 * 
	 * @author Miguel Ascanio Gómez
	 * @author Guillermo Romero Alonso
	 * 
	 */
	@SuppressWarnings("serial")
	public class LaunchException extends Throwable {

		public LaunchException() {
		}

		public LaunchException(String arg0) {
			super(arg0);
		}
	}

	private boolean asmFileSelected, iniFileSelected, outFileSelected,
			onlyHelp;
	private String asmFile, iniFile, outFile;
	private String[] args;

	private Mode mode;

	/**
	 * Constructor
	 * 
	 * @param s
	 *            argumentos de ejecución de la aplicación
	 */
	public Launcher(String[] s) {

		asmFileSelected = false;
		iniFileSelected = false;
		outFileSelected = false;
		onlyHelp = true;

		mode = Mode.BATCH;
		args = s;
	}

	/**
	 * Método que configura this.launcher según los argumentos this.args
	 * 
	 * @throws LaunchException
	 *             si alguno de los argumentos no es válido
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
									+ "-a, --asm <asmfile> \t Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch y window."
									+ newLine
									+ "-h, --help \t\t Muestra esta ayuda."
									+ newLine
									+ "-i, --in <infile> \t Entrada del programa de la maquina-p."
									+ newLine
									+ "-m, --mode <mode> \t Modo de funcionamiento (batch | interactive | window | advanced). Por defecto, batch."
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
						mode = Mode.INTERACTIVE;
					else if (args[i].equalsIgnoreCase("window"))
						mode = Mode.WINDOW;
					else if (args[i].equalsIgnoreCase("advanced"))
						mode = Mode.ADVANCED_WINDOW;
					else if (!args[i].equalsIgnoreCase("batch"))
						throw new LaunchException(
								"Error: modo seleccionado erróneo");
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

					System.out.println("Introduzca Instrucción a evaluar");

					Scanner sc = new Scanner(System.in);
					String aux = sc.nextLine();
					Expresion ex = new Expresion(aux);

					System.out.println(ex.getSetOfInstr());

					sc.close();

					throw new LaunchException(""); // Sólo evaluar
				} else {
					throw new LaunchException(
							"Error: argumento de ejecución no válido.");
				}
				i++;
			}
			if (this.onlyHelp)
				throw new LaunchException("");
		} catch (IndexOutOfBoundsException e) {
			throw new LaunchException("Error: argumento de ejecución no válido");
		}

		CommandInterpreter.configureShowConsoleInfo(mode == Mode.INTERACTIVE);
	}

	/**
	 * Método que devuelve el objeto de control de stream de entrada configurado
	 * según los argumentos de la aplciación
	 * 
	 * @return objeto de control de stream de entrada configurado
	 * @throws FileNotFoundException
	 *             si fuera entrada pro archivo y este no existiera
	 */
	public InMethod configureIN() throws FileNotFoundException {
		InMethod r = null;

		switch (mode) {
		case BATCH:
			if (iniFileSelected) {
				r = new FileInput(this.iniFile);
			} else {
				r = new ConsoleImput();
			}
			break;
		case INTERACTIVE:
			if (iniFileSelected) {
				r = new FileInput(this.iniFile);
			} else {
				r = new NullInput();
			}
			break;
		case WINDOW:
		case ADVANCED_WINDOW:
			if (iniFileSelected) {
				r = new FileInput(this.iniFile);
			} else {
				r = new NullInput();
			}
			break;
		}

		return r;
	}

	/**
	 * Método que devuelve el objeto de control de stream de salida configurado
	 * según los argumentos de la aplciación
	 * 
	 * @return objeto de control de stream de salida configurado
	 * @throws IOException
	 *             si ocurre algún error de IO
	 */
	public OutMethod configureOUT() throws IOException {
		OutMethod r = null;

		switch (mode) {
		case BATCH:
			if (outFileSelected) {
				r = new FileOutput(this.outFile);
			} else {
				r = new ConsoleOutput();
			}
			break;
		case INTERACTIVE:
			if (outFileSelected) {
				r = new FileOutput(this.outFile);
			} else {
				r = new NullOutput();
			}
			break;
		case WINDOW:
		case ADVANCED_WINDOW:
			if (outFileSelected) {
				r = new FileOutput(this.outFile);
			} else {
				r = new NullOutput();
			}
			break;
		}

		return r;
	}

	/**
	 * Método que devuelve el cargador de programa configurado según los
	 * argumentos de la aplicación
	 * 
	 * @return el cargador de programa configurado
	 * @throws LaunchException
	 *             si se intenta generar un cargador ilegal (ejecución batch sin
	 *             asm file)
	 */
	public ProgramLoader configureProgramLoader() throws LaunchException {
		ProgramLoader r = null;

		switch (mode) {
		case BATCH:
			if (!asmFileSelected) {
				throw new LaunchException(
						"Error: no se ha especificado archivo ASM para modo batch");
			} else {
				r = new FileProgramLoader(asmFile);
			}
			break;
		case INTERACTIVE:
			if (!asmFileSelected)
				r = new ConsoleProgramLoader();
			else
				// Interactive with asm file
				r = new FileProgramLoader(asmFile);
			break;
		case WINDOW:
			if (!asmFileSelected) {
				throw new LaunchException(
						"Error: no se ha especificado archivo ASM para modo window");
			} else {
				r = new FileProgramLoader(asmFile);
			}
			break;
		case ADVANCED_WINDOW:
			if (!asmFileSelected)
				r = new NullProgramLoader();
			else
				r = new FileProgramLoader(asmFile);
			break;
		}

		return r;
	}

	/**
	 * Método que configura el ejecutor de comandos según lso parámetros de la
	 * aplicación
	 * 
	 * @return el ejecutor configurado
	 */
	public CommandExecuter configureCommandExecuter(CPU cpu) {
		CommandExecuter r = null;

		if (mode == Mode.BATCH)
			r = new BatchCommandExecuter();
		else if (mode == Mode.INTERACTIVE)
			r = new InteractiveCommandExecuter();

		return r;

	}

	public MainWindow configureView(GUIControler controler) {
		MainWindow r = null;
		
		if(mode == Mode.ADVANCED_WINDOW)
			r = new AdvacedMainWindow(controler);
		else if (mode == Mode.WINDOW)
			r = new StdMainWindow(controler);
		
		return r;
	}
	
	public boolean isVisualMode() {
		return mode == Mode.WINDOW || mode == Mode.ADVANCED_WINDOW;
	}

}
