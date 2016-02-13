package tp.pr4.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr4.mv.output.OutMethod;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel implements ChangeableFontSize {

	private GUIControler controler;
	private OutputGUI outputGUI;
	private OutMethod outputCPU;
	private JTextArea outputTextArea;
	
	class OutputGUI implements OutMethod {

		protected String content;

		private OutputGUI() throws IOException {
			outputCPU.open();
			this.content = "";
		}

		@Override
		public void open() throws IOException {
			outputCPU.open();
		}

		@Override
		public void reset() throws IOException {
			outputCPU.reset();
			content = "";
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

	OutputPanel(GUIControler c) throws IOException {
		this.controler = c;
		outputCPU = controler.getOutput();
		outputGUI = new OutputGUI();
		controler.setOutput(outputGUI);
		initGUI();
		updateView();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Output: " + outputGUI.getName()));

		outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		outputTextArea.setLineWrap(true);
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setFont(new Font("Courier", Font.PLAIN, 16));

		this.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
	}

	void updateView() {
		outputTextArea.setText(outputGUI.content);
		this.setBorder(new TitledBorder("Output: " + outputGUI.getName()));
	}

	void setOutputGUIwithNewoutput(OutMethod newOut) throws IOException {
		outputCPU = newOut;
		outputGUI = new OutputGUI();
		controler.setOutput(outputGUI);
	}

	@Override
	public void changeFontSize(int size) {
		outputTextArea.setFont(new Font("Courier", Font.PLAIN, size));
		updateView();
	}

}
