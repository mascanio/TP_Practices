package tp.pr1.mv;

/**
 * 
 * @author Miguel Ascanio G�mez
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
	 * M�todo que devuelve el par�metro de la instrucci�n
	 * @return el par�metro de la instrucci�n
	 */
	public int getParam(){
		return this.param;
	}
	/**
	 * M�todo que devuelve el enumerado de la instrucci�n
	 * @return el enumerado de la instrucci�n
	 */
	public Instruction_enum getInstrEnum(){
		return this.name;
	}
	
	public String toString(){
		return this.name.name() + (this.param == null ? "" : " " +  this.param);
	}
}