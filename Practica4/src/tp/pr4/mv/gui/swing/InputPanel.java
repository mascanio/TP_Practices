package tp.pr4.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr4.mv.input.InMethod;

@SuppressWarnings("serial")
public class InputPanel extends JPanel implements ChangeableFontSize {

	private GUIControler controler;
	private JTextArea inputTextArea;
	private InputGUI inputGUI;
	private InMethod inputCPU;

	class InputGUI implements InMethod {
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
				if(c != 10 && c != 13)
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

	InputPanel(GUIControler c) throws IOException {
		this.controler = c;
		inputCPU = controler.getInput();
		inputGUI = new InputGUI();
		controler.setInput(inputGUI);
		initGUI();
		updateView();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		this.setBorder(new TitledBorder("Input: " + inputCPU.getName()));

		inputTextArea = new JTextArea();
		inputTextArea.setEditable(false);
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);
		inputTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		
		this.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
	}

	void updateView() {
		inputTextArea.setText(inputGUI.content.toString());
		this.setBorder(new TitledBorder("Input: " + inputCPU.getName()));
	}
	
	void setInputGUIwithNewInput(InMethod newIn) throws IOException {
		inputCPU = newIn;
		inputGUI = new InputGUI();
		controler.setInput(inputGUI);
	}
	
	public void changeFontSize(int size) {
		inputTextArea.setFont(new Font("Courier", Font.PLAIN, size));
		updateView();
	}

}
