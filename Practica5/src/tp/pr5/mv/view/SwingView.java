package tp.pr5.mv.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import tp.pr5.mv.controler.GUIControler;
import tp.pr5.mv.model.CPUObserver;
import tp.pr5.mv.model.MemoryObserver;
import tp.pr5.mv.model.Observable;
import tp.pr5.mv.model.StackObserver;
import tp.pr5.mv.model.input.FileInput;
import tp.pr5.mv.model.input.NullInput;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionParseException;
import tp.pr5.mv.model.output.FileOutput;
import tp.pr5.mv.model.output.NullOutput;
import tp.pr5.mv.model.programMV.FileProgramLoader;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.msg.Messages;
import tp.pr5.mv.view.swing.ChangeableFontSize;
import tp.pr5.mv.view.swing.InputPanel;
import tp.pr5.mv.view.swing.MemoryPanel;
import tp.pr5.mv.view.swing.OutputPanel;
import tp.pr5.mv.view.swing.ProgramPanel;
import tp.pr5.mv.view.swing.StackPanel;
import tp.pr5.mv.view.swing.StatusBar;
import tp.pr5.mv.view.swing.ToolsBarPanel;

@SuppressWarnings("serial")
public class SwingView extends JFrame implements Runnable, CPUObserver, View {

	private GUIControler controler;
	private Observable<CPUObserver> cpu;
	private Observable<StackObserver<Integer>> stack;
	private Observable<MemoryObserver<Integer>> memory;

	private ProgramPanel programPanel;
	private ToolsBarPanel toolsBarPanel;
	private StackPanel stackPanel;
	private MemoryPanel memoryPanel;
	private InputPanel inputPanel;
	private OutputPanel outputPanel;

	private StatusBar status;

	private MyMenuBar menuBar;

	private class MyMenuBar extends JMenuBar {

		private Archivo archivo;
		private Fuente fuente;
		private Language language;
		private JTextField delay;

		private class Archivo extends JMenu {
			private JMenuItem abrirASM, entrada, salida;

			private Archivo() {
				super(Messages.getString("SwingView.0")); //$NON-NLS-1$

				abrirASM = new JMenuItem("ASM..."); //$NON-NLS-1$

				abrirASM.setMnemonic(KeyEvent.VK_A);
				abrirASM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
						ActionEvent.ALT_MASK));
				abrirASM.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser fc = new JFileChooser();
						if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							if (SwingView.askForReset() == 0)
								try {
									controler.setProgram(FileProgramLoader
											.getProgram(fc.getSelectedFile()));
									controler.reset();
								} catch (FileNotFoundException
										| InstructionParseException e) {

									SwingView.reportError(
											e.getMessage()
													+ Messages
															.getString("SwingView.1"), //$NON-NLS-1$
											Messages.getString("SwingView.2")); //$NON-NLS-1$
									controler.quit();
								}
						}
					}
				});
				this.add(abrirASM);

				entrada = new JMenuItem(Messages.getString("SwingView.3")); //$NON-NLS-1$
				entrada.setMnemonic(KeyEvent.VK_I);
				entrada.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
						ActionEvent.ALT_MASK));
				entrada.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Object[] options = {
								Messages.getString("SwingView.4"), "NULL" }; //$NON-NLS-1$ //$NON-NLS-2$
						if (0 == JOptionPane.showOptionDialog(
								new JFrame(),
								Messages.getString("SwingView.7"), "", //$NON-NLS-1$ //$NON-NLS-2$
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								null)) {
							JFileChooser fc = new JFileChooser();
							if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
								if (0 == JOptionPane.showOptionDialog(
										new JFrame(),
										Messages.getString("SwingView.6"), //$NON-NLS-1$
										"", JOptionPane.YES_NO_OPTION, //$NON-NLS-1$
										JOptionPane.QUESTION_MESSAGE, null,
										null, null)) {
									try {
										controler.getInput().close();
										inputPanel
												.setInputGUIwithNewInput(new FileInput(
														fc.getSelectedFile()
																.getName()));
									} catch (IOException e) {
										try {
											inputPanel
													.setInputGUIwithNewInput(new NullInput());
										} catch (IOException e1) {
											SwingView.reportError(
													e.getMessage()
															+ Messages
																	.getString("SwingView.8"), //$NON-NLS-1$
													Messages.getString("SwingView.9")); //$NON-NLS-1$
											controler.quit();
										}
										SwingView.reportError(
												e.getMessage()
														+ Messages
																.getString("SwingView.10"), //$NON-NLS-1$
												Messages.getString("SwingView.11")); //$NON-NLS-1$
									}
									controler.reset();
								}
							}
						} else {
							if (SwingView.askForReset() == 0) {
								try {
									controler.getInput().close();
									inputPanel
											.setInputGUIwithNewInput(new NullInput());
								} catch (IOException e) {
									SwingView.reportError(
											e.getMessage()
													+ Messages
															.getString("SwingView.12"), //$NON-NLS-1$
											Messages.getString("SwingView.5")); //$NON-NLS-1$
									controler.quit();
								}
								controler.reset();
							}
						}
					}
				});
				this.add(entrada);

				salida = new JMenuItem(Messages.getString("SwingView.14")); //$NON-NLS-1$
				salida.setMnemonic(KeyEvent.VK_O);
				salida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
						ActionEvent.ALT_MASK));
				salida.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						Object[] options = {
								Messages.getString("SwingView.15"), "NULL" }; //$NON-NLS-1$ //$NON-NLS-2$
						if (0 == JOptionPane.showOptionDialog(
								new JFrame(),
								Messages.getString("SwingView.16"), "", //$NON-NLS-1$ //$NON-NLS-2$
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								null)) {
							JFileChooser fc = new JFileChooser();
							if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
								if (SwingView.askForReset() == 0) {
									try {
										controler.getOutput().close();
										outputPanel
												.setOutputGUIwithNewoutput(new FileOutput(
														fc.getSelectedFile()
																.getName()));
									} catch (IOException e) {
										try {
											outputPanel
													.setOutputGUIwithNewoutput(new NullOutput());
										} catch (IOException e1) {
											SwingView.reportError(
													e.getMessage()
															+ Messages
																	.getString("SwingView.17"), //$NON-NLS-1$
													Messages.getString("SwingView.18")); //$NON-NLS-1$
											controler.quit();
										}
										SwingView.reportError(
												e.getMessage()
														+ Messages
																.getString("SwingView.19"), //$NON-NLS-1$
												Messages.getString("SwingView.20")); //$NON-NLS-1$
										controler.reset();
									}
									controler.reset();
								}
							}
						} else {
							if (SwingView.askForReset() == 0) {
								try {
									controler.getOutput().close();
									outputPanel
											.setOutputGUIwithNewoutput(new NullOutput());
								} catch (IOException e) {
									SwingView.reportError(
											e.getMessage()
													+ Messages
															.getString("SwingView.21"), //$NON-NLS-1$
											Messages.getString("SwingView.22")); //$NON-NLS-1$
									controler.quit();
								}
								controler.reset();
							}
						}
					}
				});

				this.add(salida);
			}
		}

		private class Fuente extends JMenu {

			private class SizeSelector extends JMenu implements ActionListener {

				private ChangeableFontSize elem;
				private final String[] sizes = {
						Messages.getString("SwingView.23"), "12", Messages.getString("SwingView.24"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						"14", Messages.getString("SwingView.25"), "16" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				private JMenuItem[] items;

				private SizeSelector(String name, ChangeableFontSize elem) {
					super(name);
					this.elem = elem;
					items = new JMenuItem[sizes.length / 2];

					for (int i = 0; i < items.length; i++) {
						items[i] = new JMenuItem(sizes[2 * i]);
						items[i].addActionListener(this);
						this.add(items[i]);
					}
				}

				@Override
				public void actionPerformed(ActionEvent arg0) {
					JMenuItem item = (JMenuItem) arg0.getSource();
					int i = 0;

					while (items[i / 2] != item)
						i += 2;

					elem.changeFontSize(Integer.parseInt(sizes[i + 1]));
				}
			}

			private Fuente() {
				super(Messages.getString("SwingView.26")); //$NON-NLS-1$

				this.add(new SizeSelector(
						Messages.getString("SwingView.27"), inputPanel)); //$NON-NLS-1$
				this.add(new SizeSelector(
						Messages.getString("SwingView.28"), outputPanel)); //$NON-NLS-1$
				this.add(new SizeSelector(
						Messages.getString("SwingView.29"), programPanel)); //$NON-NLS-1$
				this.add(new SizeSelector(
						Messages.getString("SwingView.30"), memoryPanel)); //$NON-NLS-1$
				this.add(new SizeSelector(
						Messages.getString("SwingView.31"), stackPanel)); //$NON-NLS-1$
				this.add(new SizeSelector(
						Messages.getString("SwingView.32"), status)); //$NON-NLS-1$
			}
		}

		private class Language extends JMenu {
			private JMenuItem spanish, english;

			private Language() {
				super(Messages.getString("SwingView.38")); //$NON-NLS-1$
				spanish = new JMenuItem(Messages.getString("SwingView.39")); //$NON-NLS-1$
				english = new JMenuItem(Messages.getString("SwingView.40")); //$NON-NLS-1$

				spanish.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						tp.pr5.mv.view.msg.Messages.setLanguae(tp.pr5.mv.view.msg.Messages.LANGUAGE_SPANISH);
						repaintAll();
					}
				});
				english.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						tp.pr5.mv.view.msg.Messages.setLanguae(tp.pr5.mv.view.msg.Messages.LANGUAGE_ENGLISH);
						repaintAll();
					}
				});

				this.add(english);
				this.add(spanish);
			}
		}

		private MyMenuBar() {
			archivo = new Archivo();
			fuente = new Fuente();
			language = new Language();
			delay = new JTextField("200"); //$NON-NLS-1$
			delay.setHorizontalAlignment(JTextField.RIGHT);
			delay.setMaximumSize(new Dimension(50, 100));

			delay.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int ms = Integer.parseInt(delay.getText());
					if (ms >= 0)
						controler.setCpuDelay(ms);
				}
			});

			this.add(archivo);
			this.add(fuente);
			this.add(language);
			this.add(new JLabel(Messages.getString("SwingView.33"))); //$NON-NLS-1$
			this.add(delay);
			this.add(new JLabel(" ms ")); //$NON-NLS-1$
		}
	}

	public static void reportError(String msg, String title) {
		JOptionPane.showMessageDialog(new JFrame(), msg, title,
				JOptionPane.ERROR_MESSAGE);
	}

	public static int askForReset() {
		return JOptionPane.showOptionDialog(new JFrame(), Messages
				.getString("SwingView.34"), Messages.getString("SwingView.35"), //$NON-NLS-1$ //$NON-NLS-2$
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);
	}

	public SwingView(GUIControler ctrl, Observable<CPUObserver> cpu,
			Observable<StackObserver<Integer>> stack,
			Observable<MemoryObserver<Integer>> memory) {
		super.setName(Messages.getString("SwingView.36")); //$NON-NLS-1$
		this.controler = ctrl;
		this.cpu = cpu;
		this.memory = memory;
		this.stack = stack;

		cpu.addObserver(this);
	}

	protected void initGUI() {

		this.setSize(new Dimension(800, 550));
		this.setMinimumSize(new Dimension(800, 550));

		programPanel = new ProgramPanel(controler, cpu);
		toolsBarPanel = new ToolsBarPanel(controler, cpu);
		stackPanel = new StackPanel(controler, stack, cpu);
		memoryPanel = new MemoryPanel(controler, memory, cpu);
		try {
			inputPanel = new InputPanel(controler, cpu);
			outputPanel = new OutputPanel(controler, cpu);
		} catch (IOException e) {
			SwingView.reportError(
					e.getMessage() + Messages.getString("SwingView.37"), //$NON-NLS-1$
					Messages.getString("SwingView.38")); //$NON-NLS-1$
			controler.reset();
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
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.75;
		secondaryPanel.add(stackPanel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		secondaryPanel.add(memoryPanel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 0.25;
		secondaryPanel.add(inputPanel, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		secondaryPanel.add(outputPanel, c);

		mainPanel.add(secondaryPanel, BorderLayout.CENTER);

		this.status = new StatusBar(controler, cpu, stack, memory);
		this.add(status, BorderLayout.PAGE_END);

		menuBar = new MyMenuBar();
		this.setJMenuBar(menuBar);
	}

	private void repaintAll() {
		initGUI();
		revalidate();
	}

	protected void updateError(String e) {
		// errorArea.setText(e);
	}

	@Override
	public void run() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
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

	}

	@Override
	public void onEndRun() {

	}

	@Override
	public void onError(final String msg, final String title) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				reportError(msg, title);
			}
		});
	}

	@Override
	public void onHalt() {

	}

	@Override
	public void onReset(ProgramMV program) {

	}

	@Override
	public void start() {
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void onPause() {

	}

	@Override
	public void onResume() {

	}
}
