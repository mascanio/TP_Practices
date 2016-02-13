package tp.pr4.mv.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ToolsBarPanel extends JPanel {

	private GUIControler controler;
	private JButton run, step, exit, reset;
	
	ToolsBarPanel(final GUIControler c) {
		this.controler = c;
		
		step = new JButton(createImageIcon("step.png"));
		step.setToolTipText("Step");
		step.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.step();
			}
		});
		this.add(step);

		run = new JButton(createImageIcon("run.png"));
		run.setToolTipText("Run");
		run.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.run();				
			}
		});
		this.add(run);
		
		reset = new JButton(createImageIcon("reset.png"));
		reset.setToolTipText("Reset");
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.reset();				
			}
		});
		this.add(reset);
		
		exit = new JButton(createImageIcon("exit.png"));
		exit.setToolTipText("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controler.quit();				
			}
		});
		this.add(exit);
	}

	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ToolsBarPanel.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	void disableButtons() {
		run.setEnabled(false);
		step.setEnabled(false);
	}
	void enableButtons() {
		run.setEnabled(true);
		step.setEnabled(true);
	}

	void updateView() {
		
	}
}
