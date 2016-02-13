package tp.pr5.mv.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.controler.GUIControler;
import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.msg.Messages;

@SuppressWarnings("serial")
public class ToolsBarPanel extends JPanel implements CPUObserver {

	private GUIControler controler;
	private JButton run, step, exit, reset, pause;

	public ToolsBarPanel(GUIControler ctrl, Observable<CPUObserver> cpu) {
		this.controler = ctrl;
		cpu.addObserver(this);

		step = new JButton(createImageIcon("step.png")); //$NON-NLS-1$
		step.setToolTipText(Messages.getString("ToolsBarPanel.1")); //$NON-NLS-1$
		step.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.step();
			}
		});
		this.add(step);

		run = new JButton(createImageIcon("run.png")); //$NON-NLS-1$
		run.setToolTipText(Messages.getString("ToolsBarPanel.3")); //$NON-NLS-1$
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] Options = {
						Messages.getString("ToolsBarPanel.4"), Messages.getString("ToolsBarPanel.5") }; //$NON-NLS-1$ //$NON-NLS-2$
				if (0 == JOptionPane.showOptionDialog(new JFrame(),
						Messages.getString("ToolsBarPanel.6"), //$NON-NLS-1$
						"", JOptionPane.YES_NO_OPTION, //$NON-NLS-1$
						JOptionPane.QUESTION_MESSAGE, null, Options, null)) {
					controler.reset();
				}
				controler.run();
			}
		});
		this.add(run);

		reset = new JButton(createImageIcon("reset.png")); //$NON-NLS-1$
		reset.setToolTipText(Messages.getString("ToolsBarPanel.9")); //$NON-NLS-1$
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.reset();
			}
		});
		this.add(reset);

		pause = new JButton(createImageIcon("pause.png")); //$NON-NLS-1$
		pause.setToolTipText(Messages.getString("ToolsBarPanel.11")); //$NON-NLS-1$
		pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.pasue();
			}
		});
		pause.setEnabled(false);
		this.add(pause);

		exit = new JButton(createImageIcon("exit.png")); //$NON-NLS-1$
		exit.setToolTipText(Messages.getString("ToolsBarPanel.13")); //$NON-NLS-1$
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (0 == JOptionPane.showOptionDialog(
						new JFrame(),
						Messages.getString("ToolsBarPanel.14"), Messages.getString("ToolsBarPanel.15"), //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null)) {
					controler.quit();
				}
			}
		});
		this.add(exit);

	}

	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ToolsBarPanel.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println(Messages.getString("ToolsBarPanel.16") + path); //$NON-NLS-1$
			return null;
		}
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {

	}

	@Override
	public void onEndInstrExecution(int pc, String cpu) {

	}

	@Override
	public void onStartRun() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				run.setEnabled(false);
				step.setEnabled(false);
				reset.setEnabled(false);
				pause.setEnabled(true);
			}
		});
	}

	@Override
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				run.setEnabled(false);
				step.setEnabled(false);
				reset.setEnabled(true);
				pause.setEnabled(false);
			}
		});
	}

	@Override
	public void onError(String msg, String title) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				run.setEnabled(true);
				step.setEnabled(true);
				reset.setEnabled(true);
				pause.setEnabled(false);
			}
		});
	}

	@Override
	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				run.setEnabled(false);
				step.setEnabled(false);
				reset.setEnabled(true);
				pause.setEnabled(false);
			}
		});
	}

	@Override
	public void onReset(ProgramMV program) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				run.setEnabled(true);
				step.setEnabled(true);
				reset.setEnabled(true);
				pause.setEnabled(false);
			}
		});
	}

	@Override
	public void onPause() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				run.setEnabled(false);
				step.setEnabled(true);
				reset.setEnabled(true);
				pause.setEnabled(true);
				pause.setIcon(createImageIcon("run.png")); //$NON-NLS-1$
			}
		});
	}

	@Override
	public void onResume() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				run.setEnabled(false);
				step.setEnabled(false);
				reset.setEnabled(false);
				pause.setEnabled(true);
				pause.setIcon(createImageIcon("pause.png")); //$NON-NLS-1$
			}
		});
	}
}
