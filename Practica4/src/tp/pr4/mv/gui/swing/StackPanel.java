package tp.pr4.mv.gui.swing;

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
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class StackPanel extends JPanel implements ChangeableFontSize {

	private GUIControler controler;
	private JList<Integer> stackArea;
	private DefaultListModel<Integer> listModel;
	private BottomPanel bottomPanel;

	private class BottomPanel extends JPanel {
		private JTextField stackElem;
		private JButton push, pop;

		protected BottomPanel() {

			push = new JButton("Push");
			push.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controler.push(stackElem.getText());
				}
			});
			pop = new JButton("Pop");
			pop.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					controler.pop();
				}
			});

			stackElem = new JTextField(5);
			stackElem.setEditable(true);

			this.add(new JLabel("Value: "));
			this.add(stackElem);
			this.add(push);
			this.add(pop);
		}
	}

	StackPanel(GUIControler controler) {
		this.controler = controler;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Operand Stack"));

		listModel = new DefaultListModel<Integer>();
		stackArea = new JList<Integer>(listModel);
		stackArea.setFont(new Font("Courier", Font.PLAIN, 16));
		bottomPanel = new BottomPanel();

		this.add(new JScrollPane(stackArea), BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.PAGE_END);
	}

	void updateView() {
		listModel.clear();
		for (int i = controler.getStackElements().length-1; i >= 0; i--) {
			listModel.addElement((Integer)controler.getStackElements()[i]);
		}
	}

	@Override
	public void changeFontSize(int size) {
		stackArea.setFont(new Font("Courier", Font.PLAIN, size));
		updateView();
	}

}
