package tp.pr4.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr4.mv.Memory;

@SuppressWarnings("serial")
public class MemoryPanel extends JPanel implements ChangeableFontSize {
	
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
			this.add(new JLabel("Position: "));
			this.add(pos);
			
			value = new JTextField(5);
			value.setEditable(true);
			this.add(new JLabel("Value: "));
			this.add(value);
			
			set = new JButton("Set");
			set.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					controler.memorySet(pos.getText(), value.getText());					
				}
			});
			this.add(set);		
			
		}
	}
	
	private class Model extends AbstractTableModel {

		private String[] columnNames = {"Address", "Value"};
		private int[][] rowData;
		private int numRows; 
		
		protected Model() {
			refresh();
		}
		
		@SuppressWarnings("unchecked")
		protected void refresh() {
			Object[] cells = controler.getMemoryCells();
			numRows = cells.length;
			rowData = new int[numRows][2];
			
			for (int i = 0; i < numRows; i++) {
				rowData[i][0] = (((Memory.MemCell<Integer>)cells[i]).getPos());
				rowData[i][1] = (((Memory.MemCell<Integer>)cells[i]).getValue());
			}
			
			fireTableDataChanged();
		}
		public String getColumnName(int col) {
			return columnNames[col].toString();
		}

		public int getRowCount() {
			return rowData.length;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public Object getValueAt(int row, int col) {
			return rowData[row][col];
		}

	}
	
	MemoryPanel(GUIControler controler) {
		this.controler = controler;
		this.initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());	
		this.setBorder(new TitledBorder("Memory"));
		
		tableModel = new Model();
		memoryTable = new JTable(tableModel);
		memoryTable.setFillsViewportHeight(true);
		memoryTable.setFont(new Font("Courier", Font.PLAIN, 16));
		
		bottomPanel = new BottomPanel();
		this.add(bottomPanel, BorderLayout.PAGE_END);		
		this.add(new JScrollPane(memoryTable), BorderLayout.CENTER);
	}

	void updateView() {
		tableModel.refresh();		
	}

	@Override
	public void changeFontSize(int size) {
		memoryTable.setFont(new Font("Courier", Font.PLAIN, size));		
		updateView();
	}

}
