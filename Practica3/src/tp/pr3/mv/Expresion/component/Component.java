package tp.pr3.mv.Expresion.component;

import tp.pr3.mv.Expresion.Result;

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
