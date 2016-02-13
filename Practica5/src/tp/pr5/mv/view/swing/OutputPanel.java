package tp.pr5.mv.view.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.controler.GUIControler;
import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.output.OutMethod;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.msg.Messages;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel implements ChangeableFontSize,
		CPUObserver {

	private GUIControler controler;
	private OutputGUI outputGUI;
	private OutMethod outputCPU;
	private JTextArea outputTextArea;

	public class OutputGUI implements OutMethod {

		protected String content;

		private OutputGUI() throws IOException {
			outputCPU.open();
			this.content = ""; //$NON-NLS-1$
		}

		@Override
		public void open() throws IOException {
			outputCPU.open();
		}

		@Override
		public void reset() throws IOException {
			outputCPU.reset();
			content = ""; //$NON-NLS-1$
		}

		@Override
		public void close() throws IOException {
			outputCPU.close();
		}

		@Override
		public void writeChar(int c) {
			outputCPU.writeChar(c);
			content = content + Character.toString((char) c);
			updateView();
		}

		@Override
		public String getName() {
			return outputCPU.getName();
		}

	}

	public OutputPanel(GUIControler ctrl, Observable<CPUObserver> cpu)
			throws IOException {
		cpu.addObserver(this);

		this.controler = ctrl;
		outputCPU = controler.getOutput();
		outputGUI = new OutputGUI();
		controler.setOutput(outputGUI);
		initGUI();
		updateView();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder(Messages.getString("OutputPanel.0") + outputGUI.getName())); //$NON-NLS-1$

		outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		outputTextArea.setLineWrap(true);
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setFont(new Font("Courier", Font.PLAIN, 16)); //$NON-NLS-1$

		this.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
	}

	void updateView() {
		outputTextArea.setText(outputGUI.content);
		this.setBorder(new TitledBorder(Messages.getString("OutputPanel.0") + outputGUI.getName())); //$NON-NLS-1$
	}

	public void setOutputGUIwithNewoutput(OutMethod newOut) throws IOException {
		outputCPU = newOut;
		outputGUI = new OutputGUI();
		controler.setOutput(outputGUI);
	}

	@Override
	public void changeFontSize(final int size) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				outputTextArea.setFont(new Font("Courier", Font.PLAIN, size)); //$NON-NLS-1$
				updateView();
			}
		});
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

	}

	@Override
	public void onHalt() {

	}

	@Override
	public void onReset(ProgramMV program) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				outputTextArea.setText(outputGUI.content);
				setBorder(new TitledBorder(Messages.getString("OutputPanel.0") //$NON-NLS-1$
						+ outputGUI.getName()));
			}
		});

	}

	@Override
	public void onPause() {

	}

	@Override
	public void onResume() {
		
	}

}
