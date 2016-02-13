package tp.pr2.mv.instruction.Expresion.component;

import tp.pr2.mv.instruction.Expresion.Result;

public abstract class Component {

	protected Result result;
	
	public Result getResult() {
		return this.result;
	}
	
	abstract public void solve();
	
	public String toString() {
		return this.result.toString();
	}
}
