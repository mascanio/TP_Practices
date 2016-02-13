package tp.pr1.mv;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 *
 */
public class Parser {
	/**
	 * Determina si los parametros de la instrucción son correctos, comprobando si el número de los mismos es el correcto y si son enteros
	 * @param s instruccion a probar
	 * @param params numero de parámetros que tiene que tener la instrucción
	 * @return si los parámetros son correctos
	 */
	private static boolean checkParams(String[] s, int params){
		boolean out = true;
		if(s.length - 1 == params){//Combropar si el número de parámetros es el correcto
			if(params == 1){//Sólo comprobar si el parámetro es número para instrucciones de 1 parámetro
				int i = 0;
				
				//Permitir - de negativos
				if(s[1].charAt(0) == '-'){
					out = s[1].length() > 1; //No permitir solo el '-'
					i++;
				}
				//Comprobar que es un número
				while(i < s[1].length() && out){
					out = s[1].charAt(i) >= '0' &&  s[1].charAt(i) <= '9' ;
					i++;
				}
			}
		}
		else{
			out = false;
		}
		return out;
	}
	/**
	 * parsea la instrucción
	 * @param in instrucción a parsear
	 * @return instrucción parseada (objeto de la clase Instruction)
	 */
	public static Instruction parse(String in){	
		//Si es vacía
		if(in.isEmpty()) {
			return null;
		}
		else{
			int param_aux, numOfParams = 0;
			boolean instrValid = true;
			
			Instruction_enum instrAux = null;
			Instruction out = null;			
			
			//Divido la string a parsear
			String[] split;	
			split = in.split(" "); //split[0] contiene el nombre de la instrucción, split[1] contiene el parámetro (si procede)
			
			//1 param. instructions
			if(split[0].equalsIgnoreCase("PUSH")){
				numOfParams = 1;
				instrAux = Instruction_enum.valueOf("PUSH");
			}
			else if(split[0].equalsIgnoreCase("LOAD")){
				numOfParams = 1;
				instrAux = Instruction_enum.valueOf("LOAD");
			}
			else if(split[0].equalsIgnoreCase("STORE")){
				numOfParams = 1;
				instrAux = Instruction_enum.valueOf("STORE");
			}
			//No param instructions, num_of_params = 0
			else if(split[0].equalsIgnoreCase("POP")){
				instrAux = Instruction_enum.valueOf("POP");
			}
			else if(split[0].equalsIgnoreCase("DUP")){
				instrAux = Instruction_enum.valueOf("DUP");
			}
			else if(split[0].equalsIgnoreCase("FLIP")){
				instrAux = Instruction_enum.valueOf("FLIP");				
			}
			else if(split[0].equalsIgnoreCase("OUT")){
				instrAux = Instruction_enum.valueOf("OUT");				
			}
			else if(split[0].equalsIgnoreCase("HALT")){
				instrAux = Instruction_enum.valueOf("HALT");
			}
			//Arithm instructions
			else if(split[0].equalsIgnoreCase("ADD")){
				instrAux = Instruction_enum.valueOf("ADD");
			}
			else if(split[0].equalsIgnoreCase("SUB")){
				instrAux = Instruction_enum.valueOf("SUB");
			}
			else if(split[0].equalsIgnoreCase("MUL")){
				instrAux = Instruction_enum.valueOf("MUL");
			}
			else if(split[0].equalsIgnoreCase("DIV")){
				instrAux = Instruction_enum.valueOf("DIV");
			}
			else{
				System.out.println("Error, instrucción no válida.");
				instrValid = false;
			}
			//Num params
			if(instrValid){			
				if(checkParams(split, numOfParams)){ //si el número de parámetros es correcto
					if(numOfParams == 0){ //devuelvo instrucción instrAux sin parámetro
						out = new Instruction(instrAux);
					}
					else if(numOfParams == 1){ //devuelvo instrucción instrAux con parámetro param_aux
						param_aux = Integer.parseInt(split[1]);
						out = new Instruction(instrAux, param_aux);
					}
				}
				else if (numOfParams == 1){
					System.out.println("Error, se esperaba un único parametro tipo entero.");
				}
				else if (numOfParams == 0){
					System.out.println("Error, la instrucción no admite parámetros.");
				}
			} //else (instrucción no válida) return null

			return out;
		}
	}
}
