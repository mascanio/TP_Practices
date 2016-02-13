package tp.pr1.mv;

/**
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 *
 */
public class Parser {
	/**
	 * Determina si los parametros de la instrucci�n son correctos, comprobando si el n�mero de los mismos es el correcto y si son enteros
	 * @param s instruccion a probar
	 * @param params numero de par�metros que tiene que tener la instrucci�n
	 * @return si los par�metros son correctos
	 */
	private static boolean checkParams(String[] s, int params){
		boolean out = true;
		if(s.length - 1 == params){//Combropar si el n�mero de par�metros es el correcto
			if(params == 1){//S�lo comprobar si el par�metro es n�mero para instrucciones de 1 par�metro
				int i = 0;
				
				//Permitir - de negativos
				if(s[1].charAt(0) == '-'){
					out = s[1].length() > 1; //No permitir solo el '-'
					i++;
				}
				//Comprobar que es un n�mero
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
	 * parsea la instrucci�n
	 * @param in instrucci�n a parsear
	 * @return instrucci�n parseada (objeto de la clase Instruction)
	 */
	public static Instruction parse(String in){	
		//Si es vac�a
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
			split = in.split(" "); //split[0] contiene el nombre de la instrucci�n, split[1] contiene el par�metro (si procede)
			
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
				instrValid = false; //return null
			}
			//Num params
			if(instrValid){			
				if(checkParams(split, numOfParams)){ //si el n�mero de par�metros es correcto
					if(numOfParams == 0){ //devuelvo instrucci�n instrAux sin par�metro
						out = new Instruction(instrAux);
					}
					else if(numOfParams == 1){ //devuelvo instrucci�n instrAux con par�metro param_aux
						param_aux = Integer.parseInt(split[1]);
						out = new Instruction(instrAux, param_aux);
					}
				}
			} //else (instrucci�n no v�lida) return null

			return out;
		}
	}
}
