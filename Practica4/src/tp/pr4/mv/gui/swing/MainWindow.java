package tp.pr4.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements Runnable {

	protected GUIControler controler;
	
	protected ProgramPanel programPanel;
	protected ToolsBarPanel toolsBarPanel;
	protected StackPanel stackPanel;
	protected MemoryPanel memoryPanel;
	protected InputPanel inputPanel;
	protected OutputPanel outputPanel;
	protected JTextArea errorArea;
	
	public MainWindow(GUIControler controler) {
		super.setName("Virtual Machine");
		this.controler = controler;
	}
	
	protected void initGUI() {
		
		this.setSize(new Dimension(800, 550));
		this.setMinimumSize(new Dimension(800, 550));
		
		programPanel = new ProgramPanel(controler);
		toolsBarPanel = new ToolsBarPanel(controler);
		stackPanel = new StackPanel(controler);
		memoryPanel = new MemoryPanel(controler);
		try {
			inputPanel = new InputPanel(controler);
			outputPanel = new OutputPanel(controler);
		} catch (IOException e) {
			controler.reportError(e.getMessage(), "Error de I/O");
			controler.stop(true);
		}
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				controler.quit();
			}
		});
		this.setVisible(true);
		
		mainPanel.add(toolsBarPanel, BorderLayout.PAGE_START);
		mainPanel.add(programPanel, BorderLayout.LINE_START);
		
		JPanel secondaryPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0; c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.75;
		secondaryPanel.add(stackPanel, c);
		
		c.gridx = 1; c.gridy = 0;
		c.weightx = 0.5;
		secondaryPanel.add(memoryPanel, c);
		
		c.gridx = 0; c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 0.25;
		secondaryPanel.add(inputPanel, c);
		
		c.gridx = 0; c.gridy = 2;
		c.gridwidth = 2;
		secondaryPanel.add(outputPanel, c);
		
		mainPanel.add(secondaryPanel, BorderLayout.CENTER);
		
		errorArea = new JTextArea();
		errorArea.setBorder(new TitledBorder("Errors"));
		errorArea.setFont(new Font("Courier", Font.PLAIN, 12));
		
		this.add(errorArea, BorderLayout.PAGE_END);		
	}
	
	protected void updateView() {
		programPanel.updateView();
		toolsBarPanel.updateView();
		stackPanel.updateView();
		memoryPanel.updateView();
		inputPanel.updateView();
		outputPanel.updateView();
	}
	
	protected void updateError(String e) {
		errorArea.setText(e);
	}
	
	protected void disableButtons() {
		toolsBarPanel.disableButtons();
	}
	
	protected void enableButtons() {
		toolsBarPanel.enableButtons();
	}
	
	@Override
	public void run() {
		initGUI();		
	}
}
