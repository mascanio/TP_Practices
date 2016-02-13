package tp.pr3.mv.commandExecuter;

import java.io.IOException;
import java.util.Scanner;

import tp.pr3.mv.CPU;
import tp.pr3.mv.CpuIsHaltedException;
import tp.pr3.mv.CpuPCOverflowException;
import tp.pr3.mv.commandInterpreter.CommandInterpreter;
import tp.pr3.mv.commandInterpreter.CommandInterpreterException;
import tp.pr3.mv.commandInterpreter.CommandInterpreterRuntimeException;
import tp.pr3.mv.commandInterpreter.CommandParser;
import tp.pr3.mv.instruction.InstructionExecutionException;

/**
 * Clase para ejecutar comandos en modo interactive
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 */
public class InteractiveCommandExecuter implements CommandExecuter {

	public InteractiveCommandExecuter() {
	}

	/**
	 * Ejecuta el programa de la CPU en modo interactivo. Para ello, solicita
	 * comandos al usuario haciendo uso del interprete de comandos, comandos los
	 * ccuales ejecuta y reacciona frente a posibles fallos.
	 */
	@Override
	public void run(CPU cpu) throws CommandExecuterException {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in); //Se cierra en main

		CommandInterpreter.configureCommandInterpreter(cpu);

		String commandInstruction = "";
		CommandInterpreter parseredCommand;

		boolean out = false;

		do { //while (!out); mientras no se tenga un halt
			System.out.print("> ");
			commandInstruction = sc.nextLine();

			parseredCommand = CommandParser
					.readCommandInstruction(commandInstruction);

			while (parseredCommand == null) {

				System.out.println("Error, comando no válido");
				System.out.print("> ");

				commandInstruction = sc.nextLine();
				parseredCommand = CommandParser
						.readCommandInstruction(commandInstruction);
			}
			try {
				parseredCommand.executeCommand();
			} catch (CommandInterpreterRuntimeException e) {
				System.err.println(e.getMessage());
				//se muestra error y se vuelve a pedir comando
			} catch (CommandInterpreterException e) {
				throw new CommandExecuterException(e.getMessage());
			} catch (InstructionExecutionException e) {
				System.err.println(e.getMessage());
			} catch (CpuIsHaltedException e) {
				out = true;
			} catch (CpuPCOverflowException e) {
				throw new CommandExecuterException(e.getMessage());
			} catch (IOException e) {
				throw new CommandExecuterException(
						"Error en la entrada/salida del sistema: "
								+ e.getMessage());
			}

		} while (!out);
	}

}
