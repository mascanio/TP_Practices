package tp.pr1.mv;

import java.util.Scanner;

/**
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 *
 */
public class Main {
	
	public static String SALTO_LINEA = System.lineSeparator() ;

	/**
	 * Contiene el bulce principal de ejecui�n
	 * @param args
	 */
	public static void main(String[] args) {

		CPU cpu = new CPU();
		Instruction parseredInstruction;
		//Instancio objetos para lectura de teclado
		Scanner sc = new Scanner(System.in);
		String instr = "";
		
		//Bucle principal
		do{ // while(!cpu.isHalt());
			//Leo istrucci�n y parseo
			System.out.print("Siguiente instrucci�n: ");
			instr = sc.nextLine();			
			parseredInstruction = Parser.parse(instr);
			
			if(parseredInstruction != null)
			{
				System.out.println("Comienza la ejecuci�n de " + parseredInstruction.toString());
				if(cpu.execute(parseredInstruction)){
					System.out.println(cpu.toString());
				}
				else{
					System.out.println("Error en la ejecuci�n de la instrucci�n.");
				}
			}
			else{
				System.out.println("Error, instrucci�n no v�lida");
			}						

		} while(!cpu.isHalt());
		sc.close();
	}
}
