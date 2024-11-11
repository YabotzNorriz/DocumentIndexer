package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormAjuda {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAjuda window = new FormAjuda();
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
	public FormAjuda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.POPUP);
		frame.setResizable(false);
		frame.setBounds(100, 100, 300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JPanel panelInfo = new JPanel();
		frame.getContentPane().add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(null);
		
		JLabel lblNome = new JLabel("Feito por: João Pedro Paes");
		lblNome.setBounds(10, 100, 414, 14);
		panelInfo.add(lblNome);
		
		JLabel lblVersion = new JLabel("Versão: 0.0.2 SNAPSHOT");
		lblVersion.setBounds(10, 50, 414, 14);
		panelInfo.add(lblVersion);
		
		JLabel lblTitulo = new JLabel("INDEXADOR DE ARQUIVOS HTML");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 264, 25);
		panelInfo.add(lblTitulo);
		
		JLabel lblDataCompilacao = new JLabel("Compilado em: 11/11/2024");
		lblDataCompilacao.setBounds(10, 75, 414, 14);
		panelInfo.add(lblDataCompilacao);
		
		JButton btnFechar = new JButton("FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnFechar.setBounds(10, 125, 264, 25);
		panelInfo.add(btnFechar);
	}
}
