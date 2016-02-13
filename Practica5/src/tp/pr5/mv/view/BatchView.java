package tp.pr5.mv.view;

import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.programMV.ProgramMV;

public class BatchView implements View, CPUObserver {

	
	public BatchView(Observable<CPUObserver> cpu) {
		cpu.addObserver(this);
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {
				
	}

	@Override
	public void onEndInstrExecution(int pc, String cpu) {
		
	}

	@Override
	public void onStartRun() {
		
	}

	@Override
	public void onEndRun() {
		
	}

	@Override
	public void onError(String msg, String title) {
		System.err.println(title + ": " + msg); //$NON-NLS-1$
	}

	@Override
	public void onHalt() {
		
	}

	@Override
	public void onReset(ProgramMV program) {
		
	}

	@Override
	public void start() {
		
	}

	@Override
	public void onPause() {
	
	}

	@Override
	public void onResume() {
		
	}

}
