package tp.pr5.mv.view.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.controler.GUIControler;
import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.MemoryObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.StackObserver;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.msg.Messages;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements CPUObserver,
		MemoryObserver<Integer>, StackObserver<Integer>, ChangeableFontSize {

	private JLabel status, numInstr;
	private JCheckBox mem, stackCheck;

	public StatusBar(GUIControler ctrl, Observable<CPUObserver> cpu,
			Observable<StackObserver<Integer>> stack,
			Observable<MemoryObserver<Integer>> memory) {
		this.status = new JLabel();
		this.numInstr = new JLabel();
		numInstr.setText("0"); //$NON-NLS-1$

		cpu.addObserver(this);
		stack.addObserver(this);
		memory.addObserver(this);

		mem = new JCheckBox();
		mem.setEnabled(false);
		stackCheck = new JCheckBox();
		stackCheck.setEnabled(false);

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.add(status);
		this.add(new JLabel(Messages.getString("StatusBar.1"))); //$NON-NLS-1$
		this.add(numInstr);
		this.add(mem);
		this.add(new JLabel(Messages.getString("StatusBar.2"))); //$NON-NLS-1$
		this.add(stackCheck);
		this.add(new JLabel(Messages.getString("StatusBar.3"))); //$NON-NLS-1$

		this.setVisible(true);
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				status.setText(""); //$NON-NLS-1$
				status.repaint();
				mem.setSelected(false);
				stackCheck.setSelected(false);
			}
		});
	}

	@Override
	public void onEndInstrExecution(int pc, String cpu) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				numInstr.setText("" //$NON-NLS-1$
						+ (Integer.parseInt(numInstr.getText()) + 1));
				numInstr.repaint();
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
	public void onError(final String msg, String title) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				status.setText(msg + " "); //$NON-NLS-1$
				status.setForeground(Color.red);
				status.repaint();
			}
		});

	}

	@Override
	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				status.setText(Messages.getString("StatusBar.7")); //$NON-NLS-1$
				status.setForeground(Color.blue);
				status.repaint();
			}
		});
	}

	@Override
	public void onReset(ProgramMV program) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				numInstr.setText("0"); //$NON-NLS-1$
				numInstr.repaint();
				status.setText(""); //$NON-NLS-1$
				status.repaint();
				mem.setSelected(false);
				stackCheck.setSelected(false);
			}
		});

	}

	@Override
	public void onPush(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				stackCheck.setSelected(true);
			}
		});
	}

	@Override
	public void onPop(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				stackCheck.setSelected(true);
			}
		});

	}

	@Override
	public void onWrite(int index, Integer value) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				mem.setSelected(true);
			}
		});

	}

	@Override
	public void changeFontSize(final int size) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				status.setFont(new Font("Courier", Font.PLAIN, size)); //$NON-NLS-1$
				status.repaint();
				numInstr.setFont(new Font("Courier", Font.PLAIN, size)); //$NON-NLS-1$
				numInstr.repaint();
			}
		});

	}

	@Override
	public void onPause() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				status.setText(Messages.getString("StatusBar.12")); //$NON-NLS-1$
				status.setForeground(Color.MAGENTA);
			}
		});
	}

	@Override
	public void onResume() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				status.setText(""); //$NON-NLS-1$
			}
		});
	}

}
