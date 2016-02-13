package tp.pr5.mv.controler;

import java.io.IOException;
import java.util.Scanner;

import tp.pr5.mv.controler.interactiveCommandInterpreter.CommandInterpreter;
import tp.pr5.mv.controler.interactiveCommandInterpreter.CommandInterpreterException;
import tp.pr5.mv.controler.interactiveCommandInterpreter.CommandInterpreterRuntimeException;
import tp.pr5.mv.controler.interactiveCommandInterpreter.CommandParser;
import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.CpuIsHaltedException;
import tp.pr5.mv.model.CpuPCOverflowException;
import tp.pr5.mv.model.instruction.InstructionExecutionException;
import tp.pr5.mv.view.msg.Messages;

public class InteractiveControler extends Controler {

	public InteractiveControler(CPU cpu) {
		super(cpu);
	}

	@Override
	public void start() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in); //Se cierra en main

		CommandInterpreter.configureCommandInterpreter(cpu);

		String commandInstruction = ""; //$NON-NLS-1$
		CommandInterpreter parseredCommand;

		boolean out = false;

		do { //while (!out); mientras no se tenga un halt o error
			System.out.print("> "); //$NON-NLS-1$
			commandInstruction = sc.nextLine();

			parseredCommand = CommandParser
					.readCommandInstruction(commandInstruction);

			while (parseredCommand == null) {

				System.out.println(Messages.getString("InteractiveControler.0")); //$NON-NLS-1$
				System.out.print("> "); //$NON-NLS-1$

				commandInstruction = sc.nextLine();
				parseredCommand = CommandParser
						.readCommandInstruction(commandInstruction);
			}
			try {
				parseredCommand.executeCommand();
			} catch (CommandInterpreterRuntimeException e) {
				//System.err.println(e.getMessage());
				//se muestra error y se vuelve a pedir comando
			} catch (CommandInterpreterException e) {
				out = true;
				//throw new CommandExecuterException(e.getMessage());
			} catch (InstructionExecutionException e) {
				//System.err.println(e.getMessage());
			} catch (CpuIsHaltedException e) {
				out = true;
			} catch (CpuPCOverflowException e) {
				out = true;
				//throw new CommandExecuterException(e.getMessage());
			} catch (IOException e) {
				out = true;
				/*throw new CommandExecuterException(
						"Error en la entrada/salida del sistema: "
								+ e.getMessage());*/
			}

		} while (!out);
	
		quit();
	}

}
