package tp.pr4.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ProgramPanel extends JPanel implements ChangeableFontSize {

	private GUIControler controler;
	private JTextArea programTextArea;

	ProgramPanel(GUIControler controler) {
		this.controler = controler;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Program"));
		programTextArea = new JTextArea(18, 15);

		programTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		programTextArea.setEditable(false);

		this.add(new JScrollPane(programTextArea));
		updateView();
	}

	void updateView() {
		if (controler.isCpuPcCorrect())
			programTextArea.setText(parseProgram());
	}

	private String parseProgram() {
		String[] split = controler.getCpuProgram()
				.split(System.lineSeparator());
		
		String s = "";
		int pc = controler.getProgramCounter();
		for (int i = 0; i < pc; i++) {
			s = s + "   " + split[i] + System.lineSeparator();
		}
		try {
			s = s + " * " + split[pc] + System.lineSeparator();
		} catch (IndexOutOfBoundsException e) {
			//if reached end of program after halt
		}
		for (int i = pc+1; i < split.length; i++) {
			s = s + "   " + split[i] + System.lineSeparator();
		}
		return s;
	}

	@Override
	public void changeFontSize(int size) {
		programTextArea.setFont(new Font("Courier", Font.PLAIN, size));
		updateView();		
	}
}
