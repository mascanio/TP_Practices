package tp.pr5.mv.controler;

import java.io.IOException;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.input.InMethod;
import tp.pr5.mv.model.output.OutMethod;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.swing.InputPanel;
import tp.pr5.mv.view.swing.OutputPanel;

public class GUIControler extends Controler {

	public GUIControler(CPU cpu) {
		super(cpu);
	}

	@Override
	public void run() {
		cpu.threadRun();
	}

	@Override
	public void start() {
		cpu.setDelay(200);
	}

	public int getprogramSize() {
		return cpu.getProgramSize();
	}

	public void setCpuDelay(int ms) {
		cpu.setDelay(ms);
	}

	public String getCpuProgram() {
		return cpu.getStringProgram();
	}

	public InMethod getInput() {
		return cpu.getInput();
	}

	public void setInput(InputPanel.InputGUI inputGUI) {
		cpu.setInput(inputGUI);
	}

	public OutMethod getOutput() {
		return cpu.getOutput();
	}

	public void setOutput(OutputPanel.OutputGUI outputGUI) {
		cpu.setOutput(outputGUI);
	}

	public void setProgram(ProgramMV program) {
		cpu.loadProgram(program);
		try {
			cpu.reset();
		} catch (IOException e) {
			quit();
		}
	}
}
