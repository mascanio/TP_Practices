package tp.pr5.mv.controler;

import tp.pr5.mv.model.CPU;

public class BatchControler extends Controler {

	public BatchControler(CPU cpu) {
		super(cpu);
	}

	@Override
	public void start() {
		cpu.run();
		quit();
	}

}
