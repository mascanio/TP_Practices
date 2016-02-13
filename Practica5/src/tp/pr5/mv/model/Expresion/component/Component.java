package tp.pr5.mv.model.Expresion.component;

import tp.pr5.mv.model.Expresion.Result;

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
