package tp.pr1.mv;

/**
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 *
 */
public class CPU {
	
	/*
	 * Constructora
	 * Operaciones de CPU
	 * Operaciones aritm�ticas
	 * Execute
	 * toString
	 */
	
	private Memory memory;
	private OperandStack stack;
	
	private boolean halt;
	
	/////////////////////////////////////////////
	
	public CPU(){
		this.memory = new Memory();
		this.stack = new OperandStack();
		this.halt = false;
	}
	
	/////////////////////////////////////////////
	/**
	 * M�todo que indica si se ha ejecutado halt
	 * @return valor de halt de la m�quina
	 */
	public boolean isHalt(){
		return this.halt;
	}
	
	/////////////////////////////////////////////
	
	////////////
	// ARITHM //
	////////////
	
	/**
	 * M�todo que suma la cima y subcima, apila el resultado
	 * @return true si se ha podido realizar la operaci�n (operandos suficientes)
	 */
	private boolean add() {
		int op_a, op_b;
		boolean out = true;
		if(this.stack.getSize() >= 2){
			op_b = stack.pop();
			op_a = stack.pop();
			this.stack.push(op_a + op_b);
		}
		else{
			out = false;
		}
		
		return out;
	}
	/**
	 * M�todo que resta a la subcima la cima y apila el resultado
	 * @return true si se ha podido realizar la operaci�n (operandos suficientes)
	 */
	private boolean sub() {
		int op_a, op_b;
		boolean out = true;
		if(this.stack.getSize() >= 2){
			op_b = stack.pop();
			op_a = stack.pop();
			this.stack.push(op_a - op_b);
		}
		else{
			out = false;
		}
		
		return out;
	}
	/**
	 * M�todo que multiplica la cima y la subcima, apila el resultado
	 * @return true si se ha podido realizar la operaci�n (operandos suficientes)
	 */
	private boolean mul() {
		int op_a, op_b;
		boolean out = true;
		if(this.stack.getSize() >= 2){
			op_b = stack.pop();
			op_a = stack.pop();
			this.stack.push(op_a * op_b);
		}
		else{
			out = false;
		}
		
		return out;
	}
	/**
	 * M�todo que divide la subcima entre la cima y apila el resultado
	 * @return true si se ha podido realizar la operaci�n (operandos suficientes, no div por 0)
	 */
	private boolean div() {
		int op_a, op_b;
		boolean out = true;
		
		if(this.stack.getSize() >= 2){
			op_b = stack.pop();
			op_a = stack.pop();
			
			if(op_b != 0){ //Si no div por 0
				this.stack.push(op_a / op_b);
			}
			else{ //Devolver operandos
				this.stack.push(op_a);
				this.stack.push(op_b);
				//Err
				out = false;
			}
		}
		else{
			out = false;
		}
		
		return out;
	}
	
	/////////////////////////////////////////////////////
	
	/**
	 * M�todo que ejecuta la instrucci�n pasada por par�metro
	 * @param instr instrucci�n a ejecutar
	 * @return si hay error o no de ejecuci�n
	 */
	public boolean execute(Instruction instr){
		boolean out = true;
		
			switch (instr.getInstrEnum()){
				case PUSH:
				{
					this.stack.push(instr.getParam());
					break;
				}
				case POP:
				{
					if(this.stack.getSize() >= 1){
						this.stack.pop();
					}
					else{
						out = false;
					}
					break;
				}
				case DUP:
				{
					if(this.stack.getSize() >= 1){
						int aux = this.stack.pop();
						this.stack.push(aux);
						this.stack.push(aux);
					}
					else{
						out = false;
					}
					break;
				}
				case FLIP:
				{
					if(this.stack.getSize() >= 2){
						int aux1 = this.stack.pop();
						int aux2 = this.stack.pop();
						
						this.stack.push(aux1);
						this.stack.push(aux2);
					}
					else{
						out = false;
					}
					break;
				}
				case LOAD:
				{
					int pos = instr.getParam();
					if(pos >= 0){
						this.stack.push(this.memory.read(pos));
					}
					else{
						out = false;
					}
					break;
				}
				case STORE:
				{
					int pos = instr.getParam();
					if(pos >= 0 && this.stack.getSize() >= 1){
						this.memory.write(pos, this.stack.pop());
					}
					else{
						out = false;
					}
					break;
				}
				case ADD:
				{
					out = this.add();
					break;
				}
				case SUB:
				{
					out = this.sub();
					break;
				}
				case MUL:
				{
					out = this.mul();
					break;
				}
				case DIV:
				{
					out = this.div();
					break;
				}
				case OUT:
				{
					if(this.stack.getSize() >= 1){
						int num = this.stack.pop();
						System.out.println((char)num);
					}
					else{
						out = false;
					}
	
					break;
				}
				case HALT:
				{
					this.halt = true;
					break;
				}
			
		}

		return out;
	}
	
	/////////////////////////////////////////////

	public String toString(){
		return "El estado de la maquina tras ejecutar la instrucci�n es:" + Main.SALTO_LINEA 
				+ this.memory.toString() + Main.SALTO_LINEA 
				+ this.stack.toString();
	}
}
