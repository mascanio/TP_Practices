package tp.pr5.mv.view.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.controler.GUIControler;
import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.StackObserver;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.SwingView;
import tp.pr5.mv.view.msg.Messages;

@SuppressWarnings("serial")
public class StackPanel extends JPanel implements ChangeableFontSize,
		CPUObserver, StackObserver<Integer> {

	private GUIControler controler;

	private JList<Integer> stackArea;
	private DefaultListModel<Integer> listModel;
	private BottomPanel bottomPanel;

	private class BottomPanel extends JPanel {
		private JTextField stackElem;
		private JButton push, pop;

		protected BottomPanel() {

			push = new JButton(Messages.getString("StackPanel.0")); //$NON-NLS-1$
			push.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						controler.push(Integer.parseInt(stackElem.getText()));
					} catch (NumberFormatException e1) {
						SwingView
								.reportError(
										Messages.getString("StackPanel.1"), //$NON-NLS-1$
										Messages.getString("StackPanel.2")); //$NON-NLS-1$
					}
				}
			});
			pop = new JButton(Messages.getString("StackPanel.3")); //$NON-NLS-1$
			pop.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controler.pop();
				}
			});

			stackElem = new JTextField(5);
			stackElem.setEditable(true);

			this.add(new JLabel(Messages.getString("StackPanel.4"))); //$NON-NLS-1$
			this.add(stackElem);
			this.add(push);
			this.add(pop);
		}
	}

	public StackPanel(GUIControler ctrl,
			Observable<StackObserver<Integer>> stack,
			Observable<CPUObserver> cpu) {
		this.controler = ctrl;

		stack.addObserver(this);
		cpu.addObserver(this);

		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder(Messages.getString("StackPanel.5"))); //$NON-NLS-1$

		listModel = new DefaultListModel<Integer>();
		stackArea = new JList<Integer>(listModel);
		stackArea.setFont(new Font("Courier", Font.PLAIN, 16)); //$NON-NLS-1$
		bottomPanel = new BottomPanel();

		this.add(new JScrollPane(stackArea), BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.PAGE_END);
	}

	/*
	 * void updateView() { listModel.clear(); for (int i =
	 * controler.getStackElements().length - 1; i >= 0; i--) {
	 * listModel.addElement((Integer) controler.getStackElements()[i]); } }
	 */
	@Override
	public void changeFontSize(final int size) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				stackArea.setFont(new Font("Courier", Font.PLAIN, size)); //$NON-NLS-1$
				stackArea.repaint();
			}
		});

	}

	@Override
	public void onPush(final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				listModel.add(0, value);

			}
		});

	}

	@Override
	public void onPop(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				listModel.remove(0);
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
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bottomPanel.pop.setEnabled(false);
				bottomPanel.push.setEnabled(false);
			}
		});

	}

	@Override
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bottomPanel.pop.setEnabled(true);
				bottomPanel.push.setEnabled(true);
			}
		});
	}

	@Override
	public void onError(String msg, String title) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bottomPanel.push.setEnabled(true);
				bottomPanel.pop.setEnabled(true);
			}
		});
	}

	@Override
	public void onHalt() {

	}

	@Override
	public void onReset(ProgramMV program) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				while (!listModel.isEmpty())
					listModel.remove(0);
				bottomPanel.pop.setEnabled(true);
				bottomPanel.push.setEnabled(true);
			}
		});
	}

	@Override
	public void onPause() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bottomPanel.pop.setEnabled(true);
				bottomPanel.push.setEnabled(true);
			}
		});
	}

	@Override
	public void onResume() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bottomPanel.pop.setEnabled(false);
				bottomPanel.push.setEnabled(false);
			}
		});
	}

}
