package tp.pr1.mv;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 *
 */
public class Instruction{
	
	private Integer param;
	private Instruction_enum name;
	
	/////////////////////////////////////////////
	
	public Instruction(Instruction_enum s, int num){
		this.name = s;
		this.param = num;
	}
	
	public Instruction(Instruction_enum s){
		this.name = s;
		this.param = null;
	}
	
	//////////////////////////////////////////////
	
	/**
	 * Método que devuelve el parámetro de la instrucción
	 * @return el parámetro de la instrucción
	 */
	public int getParam(){
		return this.param;
	}
	/**
	 * Método que devuelve el enumerado de la instrucción
	 * @return el enumerado de la instrucción
	 */
	public Instruction_enum getInstrEnum(){
		return this.name;
	}
	
	public String toString(){
		return this.name.name() + (this.param == null ? "" : " " +  this.param);
	}
}