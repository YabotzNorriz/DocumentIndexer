package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import include.DatFilter;
import include.HtmlParser;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormPesquisa {

    private JFrame frame;
    private JTextField txtBusca;
    private String pathInputDirectory;
    private String pathOutputFile;
    private DefaultListModel<String> listModel;
    private JList<String> listEncontrados;

    private Map<String, File> fileMapa;

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

    public Map<String, File> getFileMapa() {
        return fileMapa;
    }

    public void setFileMapa(Map<String, File> fileMapa) {
        this.fileMapa = fileMapa;
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
        frame.setTitle("Indexador de Arquivos HTML");

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
                        DatFilter df = new DatFilter(getPathOutputFile(), palavrasChave);
                        setFileMapa(df.getMapaNomePath());
                        addItemLista(getFileMapa());
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

        JMenuItem mntmDiretorio = new JMenuItem("Diretório Entrada");
        mntmDiretorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Selecione apenas um diretório", "html");
                chooser.setFileFilter(filtro);
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

        JMenuItem mntmSaida = new JMenuItem("Diretório Saída");
        mntmSaida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Selecione apenas diretórios", "dat");
                chooser.setFileFilter(filtro);
                int res = chooser.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File diretorio = chooser.getSelectedFile();

                    setPathOutputFile(diretorio.getAbsolutePath() + "/saida.dat");
                    JOptionPane.showMessageDialog(null,
                            "A saída de dados será no seguinte caminho: " + diretorio.getAbsolutePath() + "/saida.dat");
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

        JScrollPane panelScroll = new JScrollPane();
        panelScroll.setBounds(12, 68, 426, 195);
        panelSus.add(panelScroll);

        listModel = new DefaultListModel<>();
        listEncontrados = new JList<>(listModel);
        listEncontrados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String itemSelecionado = listEncontrados.getSelectedValue();
                    File file = getFileMapa().get(itemSelecionado);
                    if (file != null && file.exists()) {
                        try {
                            // Abre o HTML no aplicativo padrão para HTML (provavelmente o navegador)
                            Desktop.getDesktop().open(file);
                        } catch (IOException f) {
                            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo: " + f.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Arquivo não encontrado!");
                    }
                }
            }
        });
        panelScroll.setViewportView(listEncontrados);
    }

    public void addItemLista(Map<String, File> mapStrFile) {
        listModel.clear();
        for (Map.Entry<String, File> entrada : mapStrFile.entrySet()) {
            listModel.addElement(entrada.getKey());
        }
    }
}
