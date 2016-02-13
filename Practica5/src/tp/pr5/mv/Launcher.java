package tp.pr5.mv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tp.pr5.mv.controler.BatchControler;
import tp.pr5.mv.controler.Controler;
import tp.pr5.mv.controler.GUIControler;
import tp.pr5.mv.controler.InteractiveControler;
import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.MemoryObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.StackObserver;
import tp.pr5.mv.model.Expresion.Expresion;
import tp.pr5.mv.model.input.ConsoleImput;
import tp.pr5.mv.model.input.FileInput;
import tp.pr5.mv.model.input.InMethod;
import tp.pr5.mv.model.input.NullInput;
import tp.pr5.mv.model.output.ConsoleOutput;
import tp.pr5.mv.model.output.FileOutput;
import tp.pr5.mv.model.output.NullOutput;
import tp.pr5.mv.model.output.OutMethod;
import tp.pr5.mv.model.programMV.ConsoleProgramLoader;
import tp.pr5.mv.model.programMV.FileProgramLoader;
import tp.pr5.mv.model.programMV.ProgramLoader;
import tp.pr5.mv.view.BatchView;
import tp.pr5.mv.view.InteractiveView;
import tp.pr5.mv.view.SwingView;
import tp.pr5.mv.view.View;
import tp.pr5.mv.view.msg.Messages;

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

	public static final String newLine = System.getProperty("line.separator"); //$NON-NLS-1$

	public enum Mode {
		BATCH, INTERACTIVE, WINDOW
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
				if (args[i].equalsIgnoreCase("-a") //$NON-NLS-1$
						|| args[i].equalsIgnoreCase("--asm")) { //$NON-NLS-1$
					// ASM file arg
					i++;
					asmFile = args[i];
					asmFileSelected = true;
					onlyHelp = false;
				} else if (args[i].equalsIgnoreCase("-h") //$NON-NLS-1$
						|| args[i].equalsIgnoreCase("--help")) { //$NON-NLS-1$
					// help arg
					System.out
							.println("usage: tp.pr3.mv.Main [-a <asmfile>] [-h] [-l <language>] [-i <infile>] [-m <mode>] [-o <outfile>]" //$NON-NLS-1$
									+ newLine
									+ newLine
									+ Messages.getString("Launcher.0") //$NON-NLS-1$
									+ newLine
									+ Messages.getString("Launcher.1") //$NON-NLS-1$
									+ newLine
									+ Messages.getString("Launcher.2") //$NON-NLS-1$
									+ newLine
									+ Messages.getString("Launcher.11") //$NON-NLS-1$
									+ newLine
									+ Messages.getString("Launcher.3") //$NON-NLS-1$
									+ newLine
									+ Messages.getString("Launcher.4") //$NON-NLS-1$
									+ newLine
									+ Messages.getString("Launcher.5")); //$NON-NLS-1$
				} else if (args[i].equalsIgnoreCase("-i") //$NON-NLS-1$
						|| args[i].equalsIgnoreCase("--in")) { //$NON-NLS-1$
					// INI file arg
					i++;
					iniFile = args[i];
					iniFileSelected = true;
					onlyHelp = false;
				} else if (args[i].equalsIgnoreCase("-m") //$NON-NLS-1$
						|| args[i].equalsIgnoreCase("--mode")) { //$NON-NLS-1$
					// Mode arg
					i++;
					if (args[i].equalsIgnoreCase("interactive")) //$NON-NLS-1$
						mode = Mode.INTERACTIVE;
					else if (args[i].equalsIgnoreCase("window")) //$NON-NLS-1$
						mode = Mode.WINDOW;
					else if (!args[i].equalsIgnoreCase("batch")) //$NON-NLS-1$
						throw new LaunchException(
								Messages.getString("Launcher.6")); //$NON-NLS-1$
					onlyHelp = false;

				} else if (args[i].equalsIgnoreCase("-o") //$NON-NLS-1$
						|| args[i].equalsIgnoreCase("--out")) { //$NON-NLS-1$
					// OUT file arg
					i++;
					outFile = args[i];
					outFileSelected = true;
					onlyHelp = false;
				} else if (args[i].equalsIgnoreCase("-e") //$NON-NLS-1$
						|| args[i].equalsIgnoreCase("--evaluator")) { //$NON-NLS-1$

					System.out.println(Messages.getString("Launcher.7")); //$NON-NLS-1$

					Scanner sc = new Scanner(System.in);
					String aux = sc.nextLine();
					Expresion ex = new Expresion(aux);

					System.out.println(ex.getSetOfInstr());

					sc.close();

					throw new LaunchException(""); // Sólo evaluar //$NON-NLS-1$
				} else if (args[i].equalsIgnoreCase("-l") //$NON-NLS-1$
						|| args[i].equalsIgnoreCase("--lang")) { //$NON-NLS-1$
					try {
					tp.pr5.mv.view.msg.Messages.setLanguae(args[++i]);	
					} catch (Error e) {
						throw new LaunchException(
								Messages.getString("Launcher.8")); //$NON-NLS-1$
					}
				} else {
					throw new LaunchException(
							Messages.getString("Launcher.8")); //$NON-NLS-1$
				}
				i++;
			}
			if (this.onlyHelp)
				throw new LaunchException(""); //$NON-NLS-1$
		} catch (IndexOutOfBoundsException e) {
			throw new LaunchException(Messages.getString("Launcher.8")); //$NON-NLS-1$
		}

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
						Messages.getString("Launcher.9")); //$NON-NLS-1$
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
						Messages.getString("Launcher.10")); //$NON-NLS-1$
			} else {
				r = new FileProgramLoader(asmFile);
			}
			break;
		}

		return r;
	}

	public Controler configureControler(CPU cpu) {
		Controler r = null;
		
		switch (mode) {
		case BATCH:
			r = new BatchControler(cpu);
			break;
		case INTERACTIVE:
			r = new InteractiveControler(cpu);
			break;
		case WINDOW:
			r = new GUIControler(cpu);
			break;
		default:
			break;
		
		}
		
		return r;
	}

	public View configureView(Controler ctrl, Observable<CPUObserver> cpu,
			Observable<StackObserver<Integer>> stack,
			Observable<MemoryObserver<Integer>> memory) {
		View r = null ;
		
		switch (mode) {
		case BATCH:
			r = new BatchView(cpu);
			break;
		case INTERACTIVE:
			r = new InteractiveView(cpu);
			break;
		case WINDOW:
			r = new SwingView((GUIControler) ctrl, cpu, stack, memory);
			break;
		default:
			break;		
		}
		
		return r;
	}

}
