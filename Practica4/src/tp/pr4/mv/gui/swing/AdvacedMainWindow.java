package tp.pr4.mv.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import tp.pr4.mv.input.FileInput;
import tp.pr4.mv.input.NullInput;
import tp.pr4.mv.instruction.InstructionParseException;
import tp.pr4.mv.output.FileOutput;
import tp.pr4.mv.output.NullOutput;
import tp.pr4.mv.programMV.FileProgramLoader;

@SuppressWarnings("serial")
public class AdvacedMainWindow extends MainWindow {

	public AdvacedMainWindow(GUIControler controler) {
		super(controler);
	}

	private MenuBar menuBar;

	private class MenuBar extends JMenuBar {

		private Archivo archivo;
		private Fuente fuente;

		private class Archivo extends JMenu {
			private JMenuItem abrirASM, entrada, salida;

			private Archivo() {
				super("Archivo");

				abrirASM = new JMenuItem("ASM...");

				abrirASM.setMnemonic(KeyEvent.VK_A);
				abrirASM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
						ActionEvent.ALT_MASK));
				abrirASM.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser fc = new JFileChooser();
						if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							if (controler.askForReset() == 0)
								try {
									controler.setProgram(FileProgramLoader
											.getProgram(fc.getSelectedFile()));
									controler.reset();
								} catch (FileNotFoundException
										| InstructionParseException e) {
									controler.stop(true);
									controler.reportError(e.getMessage(),
											"Error al cargar el programa");
								}
						}
						updateView();
					}
				});
				this.add(abrirASM);

				entrada = new JMenuItem("Input...");
				entrada.setMnemonic(KeyEvent.VK_I);
				entrada.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
						ActionEvent.ALT_MASK));
				entrada.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Object[] options = { "Cargar", "NULL" };
						if (0 == JOptionPane.showOptionDialog(new JFrame(),
								"¿Cargar archivo o poner entrada NULL?", "",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								null)) {
							JFileChooser fc = new JFileChooser();
							if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
								if (0 == JOptionPane
										.showOptionDialog(
												new JFrame(),
												"Se reiniciará la máquina. ¿Continuar?",
												"", JOptionPane.YES_NO_OPTION,
												JOptionPane.QUESTION_MESSAGE,
												null, null, null)) {
									try {
										controler.getInput().close();
										inputPanel
												.setInputGUIwithNewInput(new FileInput(
														fc.getSelectedFile()
																.getName()));
									} catch (IOException e) {
										controler.stop(true);
										controler.reportError(e.getMessage(),
												"Error de I/O");
									}
									controler.reset();
								}
							}
						} else {
							if (controler.askForReset() == 0) {
								try {
									controler.getInput().close();
									inputPanel
											.setInputGUIwithNewInput(new NullInput());
								} catch (IOException e) {
									controler.stop(true);
									controler.reportError(e.getMessage(),
											"Error de I/O");
								}
								controler.reset();
							}
						}

						updateView();
					}
				});
				this.add(entrada);

				salida = new JMenuItem("Output...");
				salida.setMnemonic(KeyEvent.VK_O);
				salida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
						ActionEvent.ALT_MASK));
				salida.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						Object[] options = { "Crear", "NULL" };
						if (0 == JOptionPane.showOptionDialog(new JFrame(),
								"¿Crear archivo o poner salida NULL?", "",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								null)) {
							JFileChooser fc = new JFileChooser();
							if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
								if (controler.askForReset() == 0) {
									try {
										controler.getOutput().close();
										outputPanel
												.setOutputGUIwithNewoutput(new FileOutput(
														fc.getSelectedFile()
																.getName()));
									} catch (IOException e) {
										controler.stop(true);
										controler.reportError(e.getMessage(),
												"Error de I/O");
									}
									controler.reset();
								}
							}
						} else {
							if (controler.askForReset() == 0) {
								try {
									controler.getOutput().close();
									outputPanel
											.setOutputGUIwithNewoutput(new NullOutput());
								} catch (IOException e) {
									controler.stop(true);
									controler.reportError(e.getMessage(),
											"Error de I/O");
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
				private final String[] sizes = {"Pequeña", "12", "Normal", "14", "Grande", "16"};
				private JMenuItem[] items;

				private SizeSelector(String name, ChangeableFontSize elem) {
					super(name);
					this.elem = elem;
					items = new JMenuItem[sizes.length/2];
					
					for (int i = 0; i < items.length; i++) {
						items[i] = new JMenuItem(sizes[2*i]);
						items[i].addActionListener(this);
						this.add(items[i]);
					}
				}

				@Override
				public void actionPerformed(ActionEvent arg0) {
					JMenuItem item = (JMenuItem) arg0.getSource();
					int i = 0;
					
					while (items[i/2] != item)
						i+=2;
					
					elem.changeFontSize(Integer.parseInt(sizes[i+1]));
				}			
			}
			
			private Fuente() {
				super("Fuente");		
				
				this.add(new SizeSelector("Input", inputPanel));
				this.add(new SizeSelector("Output", outputPanel));
				this.add(new SizeSelector("Program", programPanel));
				this.add(new SizeSelector("Memory", memoryPanel));
				this.add(new SizeSelector("Stack", stackPanel));
			}
		}

		private MenuBar() {
			archivo = new Archivo();
			fuente = new Fuente();

			this.add(archivo);
			this.add(fuente);

		}
	}

	@Override
	protected void initGUI() {
		super.initGUI();

		menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
	}
}
