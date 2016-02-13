package tp.pr5.mv.view.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.controler.GUIControler;
import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.msg.Messages;

@SuppressWarnings("serial")
public class ProgramPanel extends JPanel implements ChangeableFontSize,
		CPUObserver {

	private GUIControler controler;
	private JList<Pair> pointerArea;
	private DefaultListModel<Pair> model;
	private int pc;

	private char VOID_CHAR = ' ', POINTER_CHAR = '*';

	private class Pair {
		public char pointer;
		public String s;

		private Pair() {
			this.pointer = VOID_CHAR;
			this.s = ""; //$NON-NLS-1$
		}

		private Pair(char c, String s) {
			this.pointer = c;
			this.s = s;
		}

		public String toString() {
			return pointer + " " + s; //$NON-NLS-1$
		}
	}

	public ProgramPanel(GUIControler ctrl, Observable<CPUObserver> cpu) {
		this.controler = ctrl;
		cpu.addObserver(this);

		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder(Messages.getString("ProgramPanel.0"))); //$NON-NLS-1$

		pc = 0;
		model = new DefaultListModel<>();
		pointerArea = new JList<Pair>(model);
		model.setSize(controler.getprogramSize()+2);
		pointerArea.setFont(new Font("Courier", Font.PLAIN, 16)); //$NON-NLS-1$

		read();
		this.model.set(0, new Pair(POINTER_CHAR, model.get(pc).s));

		this.add(new JScrollPane(pointerArea));
	}

	private void read() {
		String[] split = controler.getCpuProgram()
				.split(System.lineSeparator());

		for (int i = 0; i < split.length; i++) {
			model.set(i, new Pair(VOID_CHAR, split[i]));
		}
	}

	@Override
	public void changeFontSize(final int size) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				pointerArea.setFont(new Font("Courier", Font.PLAIN, size)); //$NON-NLS-1$
				pointerArea.repaint();
			}
		});

	}

	@Override
	public void onStartInstrExecution(Instruction instr) {

	}

	@Override
	public void onEndInstrExecution(int pc, String cpu) {

		final int actualPC = this.pc, newPC = pc;
		this.pc = pc;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				model.set(actualPC, new Pair(VOID_CHAR, model.get(actualPC).s));
				try {
					model.set(newPC, new Pair(POINTER_CHAR, model.get(newPC).s));
				} catch (NullPointerException e) {
				} // Solo si se sale del programas
			}
		});

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

		this.pc = 0;

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				model.clear();
				model.setSize(controler.getprogramSize()+2);
				read();

				model.set(0, new Pair(POINTER_CHAR, model.get(0).s));
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
