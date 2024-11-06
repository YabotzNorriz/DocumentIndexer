package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import include.DatFilter;
import include.HtmlParser;

import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class FormPesquisa {
	
	private JFrame frame;
	private JTextField txtBusca;
	private String pathInputDirectory;
	private String pathOutputFile;

	public String getPathInputDirectory() {
		return pathInputDirectory;
	}

	public void setPathInputDirectory(String pathInputDirectory) {
		System.out.println(pathInputDirectory);
		this.pathInputDirectory = pathInputDirectory;
	}

	public String getPathOutputFile() {
		return pathOutputFile;
	}

	public void setPathOutputFile(String pathOutputFile) {
		System.out.println(pathOutputFile);
		this.pathOutputFile = pathOutputFile;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPesquisa window = new FormPesquisa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormPesquisa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JPanel panelSus = new JPanel();
		frame.getContentPane().add(panelSus, BorderLayout.CENTER);
		panelSus.setLayout(null);
		
		txtBusca = new JTextField();
		txtBusca.setBounds(12, 33, 313, 23);
		panelSus.add(txtBusca);
		txtBusca.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new HtmlParser(getPathInputDirectory(), getPathOutputFile());
					try {
						String palavrasChave = txtBusca.getText();
						new DatFilter(getPathOutputFile(), palavrasChave);
					} catch (InaccessibleObjectException f) {
						JOptionPane.showMessageDialog(null, "ERRO! " + f.getMessage());
					}
				} catch (Exception g) {
					JOptionPane.showMessageDialog(null, "Diretório de entrada ou saída inválido!");
				}
			}
		});
		btnBuscar.setBounds(337, 33, 101, 23);
		panelSus.add(btnBuscar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, -2, 450, 23);
		panelSus.add(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmDiretorio = new JMenuItem("Diretório");
		mntmDiretorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int res = chooser.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION) {
					File diretorio = chooser.getSelectedFile();
					setPathInputDirectory(diretorio.getAbsolutePath());
					JOptionPane.showMessageDialog(null, "Você escolheu o diretório: " + diretorio.getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(null, "Você não escolheu nenhum diretório!");
				}
			}
		});
		mnArquivo.add(mntmDiretorio);
		
		JMenuItem mntmSaida = new JMenuItem("Saída");
		mntmSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int res = chooser.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION) {
					File diretorio = chooser.getSelectedFile();
					
					setPathOutputFile(diretorio.getAbsolutePath() + "/saida.dat");
					JOptionPane.showMessageDialog(null, "A saída de dados será no seguinte caminho: " + diretorio.getAbsolutePath() + "/saida.dat");
				} else {
					JOptionPane.showMessageDialog(null, "Você não escolheu nenhum diretório!");
				}
			}
		});
		mnArquivo.add(mntmSaida);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "O programa será finalizado!");
				System.exit(1);
			}
		});
		mnArquivo.add(mntmSair);
		
		JScrollPane painelScroll = new JScrollPane();
		painelScroll.setBounds(12, 68, 426, 195);
		panelSus.add(painelScroll);
		
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList listEncontrados = new JList<>(listModel);
		painelScroll.setViewportView(listEncontrados);
	}
}
