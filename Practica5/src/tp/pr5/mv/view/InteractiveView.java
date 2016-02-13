package tp.pr5.mv.view;

import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.msg.Messages;

public class InteractiveView implements CPUObserver, View {

	public InteractiveView(Observable<CPUObserver> cpu) {
		cpu.addObserver(this);
	}
	
	@Override
	public void onStartInstrExecution(Instruction instr) {
		System.out.println(Messages.getString("InteractiveView.0") //$NON-NLS-1$
				+ instr);
	}

	@Override
	public void onEndInstrExecution(int pc, String cpu) {
		System.out.println(cpu);
	}

	@Override
	public void onStartRun() {
		
	}

	@Override
	public void onEndRun() {
		System.out.println(Messages.getString("InteractiveView.1")); //$NON-NLS-1$
	}

	@Override
	public void onError(String msg, String title) {
		System.err.println(msg);
	}

	@Override
	public void onHalt() {
		//System.out.println(Messages.getString("InteractiveView.2")); //$NON-NLS-1$
	}

	@Override
	public void onReset(ProgramMV program) {
		//System.out.println(Messages.getString("InteractiveView.3")); //$NON-NLS-1$
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
