package tp.pr5.mv.model;

import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.programMV.ProgramMV;

public interface CPUObserver {
	void onStartInstrExecution(Instruction instr);
	void onEndInstrExecution(int pc, String cpu);
	void onStartRun();
	void onEndRun();
	void onError(String msg, String title);
	void onHalt();
	void onReset(ProgramMV program);
	void onPause();
	void onResume();
}
