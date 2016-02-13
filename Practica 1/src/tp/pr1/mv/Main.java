package tp.pr1.mv;

import java.util.Scanner;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 *
 */
public class Main {
	
	public static String SALTO_LINEA = System.lineSeparator() ;

	/**
	 * Contiene el bulce principal de ejecuión
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
			//Leo istrucción y parseo
			System.out.print("Siguiente instrucción: ");
			instr = sc.nextLine();			
			parseredInstruction = Parser.parse(instr);
			
			if(parseredInstruction != null) //si es válida
			{
				System.out.println("Comienza la ejecución de " + parseredInstruction.toString());
				if(cpu.execute(parseredInstruction)){
					System.out.println(cpu.toString());
				}
				else{
					System.out.println("Error en la ejecución de la instrucción.");
				}
			} //errores mostrados en la ejecución del parser
		} while(!cpu.isHalt());
	}
}
