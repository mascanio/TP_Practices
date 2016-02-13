package tp.pr5.mv.view.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr5.mv.controler.GUIControler;
import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.MemoryObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.SwingView;
import tp.pr5.mv.view.msg.Messages;

@SuppressWarnings("serial")
public class MemoryPanel extends JPanel implements ChangeableFontSize,
		CPUObserver, MemoryObserver<Integer> {

	private GUIControler controler;
	private JTable memoryTable;
	private Model tableModel;
	private BottomPanel bottomPanel;

	private class BottomPanel extends JPanel {
		private JTextField pos, value;
		private JButton set;

		protected BottomPanel() {

			pos = new JTextField(5);
			pos.setEditable(true);
			this.add(new JLabel(Messages.getString("MemoryPanel.0"))); //$NON-NLS-1$
			this.add(pos);

			value = new JTextField(5);
			value.setEditable(true);
			this.add(new JLabel(Messages.getString("MemoryPanel.1"))); //$NON-NLS-1$
			this.add(value);

			set = new JButton(Messages.getString("MemoryPanel.2")); //$NON-NLS-1$
			set.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						controler.memorySet(Integer.parseInt(pos.getText()),
								Integer.parseInt(value.getText()));
					} catch (NumberFormatException e1) {
						SwingView
								.reportError(
										Messages.getString("MemoryPanel.3"), //$NON-NLS-1$
										Messages.getString("MemoryPanel.4")); //$NON-NLS-1$
					}
				}
			});
			this.add(set);

		}
	}

	private class Model extends AbstractTableModel {

		private String[] columnNames = { Messages.getString("MemoryPanel.5"), Messages.getString("MemoryPanel.6") }; //$NON-NLS-1$ //$NON-NLS-2$

		private TreeMap<Integer, Integer> content;

		protected Model() {
			content = new TreeMap<Integer, Integer>();
		}

		public void setValue(int index, int value) {
			content.put(index, value);
			fireTableDataChanged();
		}

		public void reset() {
			content.clear();
			fireTableDataChanged();
		}

		public String getColumnName(int col) {
			return columnNames[col].toString();
		}

		public int getRowCount() {
			return content.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public Object getValueAt(int row, int col) {

			Set<Integer> aux = content.keySet();

			Iterator<Integer> iterator = aux.iterator();

			int i = 0;
			Integer r = null;
			while (iterator.hasNext() && i <= row) {
				r = iterator.next();
				i++;
			}

			return col == 0 ? r : content.get(r);
		}
	}

	public MemoryPanel(GUIControler ctrl,
			Observable<MemoryObserver<Integer>> memory,
			Observable<CPUObserver> cpu) {
		this.controler = ctrl;

		memory.addObserver(this);
		cpu.addObserver(this);

		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder(Messages.getString("MemoryPanel.7"))); //$NON-NLS-1$

		tableModel = new Model();
		memoryTable = new JTable(tableModel);
		memoryTable.setFillsViewportHeight(true);
		memoryTable.setFont(new Font("Courier", Font.PLAIN, 16)); //$NON-NLS-1$

		bottomPanel = new BottomPanel();
		this.add(bottomPanel, BorderLayout.PAGE_END);
		this.add(new JScrollPane(memoryTable), BorderLayout.CENTER);
	}

	@Override
	public void changeFontSize(final int size) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				memoryTable.setFont(new Font("Courier", Font.PLAIN, size)); //$NON-NLS-1$
				memoryTable.repaint();

			}
		});

	}

	@Override
	public void onWrite(final int index, final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				tableModel.setValue(index, value);

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
				bottomPanel.set.setEnabled(false);
			}
		});

	}

	@Override
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bottomPanel.set.setEnabled(true);
			}
		});

	}

	@Override
	public void onError(String msg, String title) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bottomPanel.set.setEnabled(true);
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
				bottomPanel.set.setEnabled(true);
				tableModel.reset();
			}
		});

	}

	@Override
	public void onPause() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bottomPanel.set.setEnabled(true);
			}
		});
	}

	@Override
	public void onResume() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				bottomPanel.set.setEnabled(false);
			}
		});
	}

}
