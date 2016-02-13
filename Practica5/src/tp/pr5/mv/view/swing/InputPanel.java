package tp.pr5.mv.view.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.controler.GUIControler;
import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.input.InMethod;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.msg.Messages;

@SuppressWarnings("serial")
public class InputPanel extends JPanel implements ChangeableFontSize,
		CPUObserver {

	private GUIControler controler;
	private JTextArea inputTextArea;
	private InputGUI inputGUI;
	private InMethod inputCPU;

	public class InputGUI implements InMethod {
		private int pos;
		protected StringBuilder content;

		private InputGUI() throws IOException {
			inputCPU.open();
			this.pos = 0;
			content = new StringBuilder();
			int read = inputCPU.readChar();

			while (read != -1) {
				content.append((char) read);
				read = inputCPU.readChar();
			}
		}

		@Override
		public void open() throws FileNotFoundException {
			inputCPU.open();
		}

		@Override
		public void reset() throws FileNotFoundException, IOException {
			inputCPU.reset();
			content = new StringBuilder();
			this.pos = 0;
			int read = inputCPU.readChar();

			while (read != -1) {
				content.append((char) read);
				read = inputCPU.readChar();
			}
		}

		@Override
		public void close() throws IOException {
			inputCPU.close();
		}

		@Override
		public int readChar() throws IOException {
			int c = -1;
			if (pos != content.length()) {
				c = content.charAt(pos);
				if (c != 10 && c != 13)
					content.setCharAt(pos, '*');
				updateView();
				pos++;
			}

			return c;
		}

		@Override
		public String getName() {
			return inputCPU.getName();
		}

	}

	public InputPanel(GUIControler ctrl, Observable<CPUObserver> cpu)
			throws IOException {
		cpu.addObserver(this);
		this.controler = ctrl;
		inputCPU = controler.getInput();
		inputGUI = new InputGUI();
		controler.setInput(inputGUI);
		initGUI();
		updateView();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());

		this.setBorder(new TitledBorder(Messages.getString("InputPanel.0") + inputCPU.getName())); //$NON-NLS-1$

		inputTextArea = new JTextArea();
		inputTextArea.setEditable(false);
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);
		inputTextArea.setFont(new Font("Courier", Font.PLAIN, 16)); //$NON-NLS-1$

		this.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
	}

	void updateView() {
		inputTextArea.setText(inputGUI.content.toString());
		this.setBorder(new TitledBorder(Messages.getString("InputPanel.0") + inputCPU.getName())); //$NON-NLS-1$
	}

	public void setInputGUIwithNewInput(InMethod newIn) throws IOException {
		inputCPU = newIn;
		inputGUI = new InputGUI();
		controler.setInput(inputGUI);
	}

	public void changeFontSize(final int size) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				inputTextArea.setFont(new Font("Courier", Font.PLAIN, size)); //$NON-NLS-1$
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
				inputTextArea.setText(inputGUI.content.toString());
				setBorder(new TitledBorder(Messages.getString("InputPanel.0") + inputCPU.getName())); //$NON-NLS-1$
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
