package VIEW;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import CONTROL.ControlarAplicativo;

public class MontarPainelInicial {

    private JFrame baseFrame;
    private JPanel basePanel;
    private JPanel outputPanel, outputPanelEsq, outputPanelCen, outputPanelDir;
    private JPanel controlePanelAcao1;
    private JPanel controlePanelAcao2;
    private JPanel controlePanelAcao3;
    private JPanel controlePanelVisualImagens;
    private JPanel controlePanelAcao4;
    private JButton btAcao3;
    private JButton btAcao1;
    private JButton btSalva;
    private JButton btReset;
    private JButton btAcao4;
    private JRadioButton btAcao31;
    private JRadioButton btAcao32;
    private ButtonGroup btRdAcao3;
    private JRadioButton btAcao11;
    private JRadioButton btAcao12;
    private JRadioButton btAcao13;
    private JRadioButton btAcao14;
    private JRadioButton btAcao15;
    private ButtonGroup btRdAcao1;
    private JRadioButton btAcao21;
    private JRadioButton btAcao22;
    private JRadioButton btAcao23;
    private JRadioButton btAcao24;
    private JRadioButton btAcao25;
    private JRadioButton btAcao26;
    private ButtonGroup btRdAcao2;
    private JRadioButton btVisualNewImg;
    private JRadioButton btVisualAllImg;
    private ButtonGroup btRdVisualImg;
    private JRadioButton btAcao41;
    private JRadioButton btAcao42;
    private ButtonGroup btRdAcao4;
    private JLabel lbTheta;
    private JLabel lbSigma;
    private JLabel lbLambda;
    private JLabel lbGamma;
    private JLabel lbOffset;
    private JTextField tfTheta;
    private JTextField tfSigma;
    private JTextField tfGamma;
    private JTextField tfLambda;
    private JTextField tfOffset;
    private Graphics desenhoCen;
    private Graphics desenhoDir;

    //*******************************************************************************************
    public MontarPainelInicial(ControlarAplicativo controlePrograma) {
        JPanel buttonPanel;
        JPanel titlePanel;
        JPanel acao3Panel;
        JPanel acao1Panel;
        JPanel acao2Panel;
        JPanel visualImagensPanel;
        JPanel acao4Panel;

        // LAYOUT
        baseFrame = new JFrame();
        baseFrame.setLayout(new BoxLayout(baseFrame.getContentPane(), BoxLayout.Y_AXIS));

        baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // FITS PANEL TO THE ACTUAL MONITOR SIZE
        baseFrame.setUndecorated(true);  // TURN OFF ALL THE PANEL BORDERS 

        basePanel = new JPanel();
        basePanel.setLayout(new BorderLayout());

        // TITLE PANEL
        titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(0, 50));
        titlePanel.setBackground(Color.gray);

        // OUTPUT PANEL
        outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());

        outputPanelEsq = new JPanel();
        outputPanelEsq.setPreferredSize(new Dimension(130, 0));
        outputPanelEsq.setLayout(new BoxLayout(outputPanelEsq, BoxLayout.Y_AXIS));
        outputPanelEsq.setBackground(Color.lightGray);

        outputPanelCen = new JPanel();
        outputPanelCen.setBackground(new Color(220, 220, 210));
        outputPanelCen.setLayout(new BorderLayout());

        outputPanelDir = new JPanel();
        outputPanelDir.setBackground(new Color(210, 200, 200));
        outputPanelDir.setPreferredSize(new Dimension(580, 0));
        outputPanelDir.setLayout(new BorderLayout());

        // BUTTON PANEL
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(0, 50));
        buttonPanel.setBackground(Color.gray);

        // PANEL TITLE
        JLabel titulo;
        titulo = new JLabel("IMAGE LAB PROCESSING");
        titulo.setForeground(Color.black);
        titulo.setFont(new Font("Dialog", Font.BOLD, 25));
        titlePanel.add(titulo);

        // ADDING BUTTONS
        addAButton("New Image", "botaoImagem", buttonPanel, true, controlePrograma);
        btReset = addAButton("Reset", "botaoReset", buttonPanel, false, controlePrograma);
        btAcao1 = addAButton("Rotacionar", "botaoRotacionar", buttonPanel, false, controlePrograma);
        btAcao3 = addAButton("Aplicar Filtro Gabor", "botaoFiltroGabor", buttonPanel, false, controlePrograma);
        btAcao4 = addAButton("Acao4", "botaoAcao4", buttonPanel, false, controlePrograma);
        btSalva = addAButton("Save", "botaoSalva", buttonPanel, false, controlePrograma);
        addAButton("END", "botaoFim", buttonPanel, true, controlePrograma);

        // CONFIGURANDO A INTERFACE DOS PARÂMETROS DO FILTRO GABOR
        //IINICIALIZANDO LABELS
        lbTheta = new JLabel("θ:");
        lbSigma = new JLabel("σ:");
        lbLambda = new JLabel("λ:");
        lbGamma = new JLabel("γ:");
        lbOffset = new JLabel("φ:");
        
        //INICIALIZANDO CAMPOS DE TEXTO
        tfTheta = new JTextField("0");
        tfSigma = new JTextField("1");
        tfLambda = new JTextField("3");
        tfGamma = new JTextField("0.5");
        tfOffset = new JTextField("0");
        
        controlePanelAcao3 = new JPanel();
        controlePanelAcao3.setBackground(Color.lightGray);
        controlePanelAcao3.setMaximumSize(new Dimension(200, 150));
        outputPanelEsq.add(controlePanelAcao3);

        acao3Panel = new JPanel();
        acao3Panel.setPreferredSize(new Dimension(120, 130));
        acao3Panel.setLayout(new GridLayout(5, 2));

        acao3Panel.add(lbSigma);
        acao3Panel.add(tfSigma);
        acao3Panel.add(lbGamma);
        acao3Panel.add(tfGamma);
        acao3Panel.add(lbTheta);
        acao3Panel.add(tfTheta);
        acao3Panel.add(lbLambda);
        acao3Panel.add(tfLambda);
        acao3Panel.add(lbOffset);
        acao3Panel.add(tfOffset);

        acao3Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Filtro Gabor"));

        controlePanelAcao3.add(acao3Panel);
        controlePanelAcao3.setVisible(false);

        // ADDING RADIO BUTTON PARA CONTROLE DA ACAO 1
        controlePanelAcao1 = new JPanel();
        controlePanelAcao1.setBackground(Color.lightGray);
        controlePanelAcao1.setMaximumSize(new Dimension(130, 115));
        outputPanelEsq.add(controlePanelAcao1);


        // ADDING RADIO BUTTON PARA CONTROLE DA ACAO 2
        controlePanelAcao2 = new JPanel();
        controlePanelAcao2.setBackground(Color.lightGray);
        controlePanelAcao2.setMaximumSize(new Dimension(130, 135));
        outputPanelEsq.add(controlePanelAcao2);

        btAcao21 = new JRadioButton(" Acao21 ", true);
        btAcao22 = new JRadioButton(" Acao22 ", false);
        btAcao23 = new JRadioButton(" Acao23 ", false);
        btAcao24 = new JRadioButton(" Acao24 ", false);
        btAcao25 = new JRadioButton(" Acao25 ", false);
        btAcao26 = new JRadioButton(" Acao26 ", false);

        btRdAcao2 = new ButtonGroup();
        btRdAcao2.add(btAcao21);
        btRdAcao2.add(btAcao22);
        btRdAcao2.add(btAcao23);
        btRdAcao2.add(btAcao24);
        btRdAcao2.add(btAcao25);
        btRdAcao2.add(btAcao26);

        btAcao21.addActionListener(controlePrograma);
        btAcao22.addActionListener(controlePrograma);
        btAcao23.addActionListener(controlePrograma);
        btAcao24.addActionListener(controlePrograma);
        btAcao25.addActionListener(controlePrograma);
        btAcao26.addActionListener(controlePrograma);

        btAcao21.setActionCommand("botaoAcao21");
        btAcao22.setActionCommand("botaoAcao22");
        btAcao23.setActionCommand("botaoAcao23");
        btAcao24.setActionCommand("botaoAcao24");
        btAcao25.setActionCommand("botaoAcao25");
        btAcao26.setActionCommand("botaoAcao26");

        acao2Panel = new JPanel();
        acao2Panel.setPreferredSize(new Dimension(120, 130));
        acao2Panel.setLayout(new GridLayout(6, 1));

        acao2Panel.add(btAcao21);
        acao2Panel.add(btAcao22);
        acao2Panel.add(btAcao23);
        acao2Panel.add(btAcao24);
        acao2Panel.add(btAcao25);
        acao2Panel.add(btAcao26);

        acao2Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ACAO 2"));

        controlePanelAcao2.add(acao2Panel);
        controlePanelAcao2.setVisible(false);

        // ADDING RADIO BUTTON PARA CONTROLE DO TIPO DA ACAO 4
        controlePanelAcao4 = new JPanel();
        controlePanelAcao4.setBackground(Color.lightGray);
        controlePanelAcao4.setMaximumSize(new Dimension(130, 60));
        outputPanelEsq.add(controlePanelAcao4);

        btAcao41 = new JRadioButton("  Acao 41 ", true);
        btAcao42 = new JRadioButton("  Acao 42 ", false);

        btRdAcao4 = new ButtonGroup();
        btRdAcao4.add(btAcao41);
        btRdAcao4.add(btAcao42);

        btAcao41.addActionListener(controlePrograma);
        btAcao42.addActionListener(controlePrograma);

        acao4Panel = new JPanel();
        acao4Panel.setPreferredSize(new Dimension(120, 55));
        acao4Panel.setLayout(new GridLayout(2, 1));

        acao4Panel.add(btAcao41);
        acao4Panel.add(btAcao42);

        acao4Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ACAO 4"));

        controlePanelAcao4.add(acao4Panel);
        controlePanelAcao4.setVisible(false);

        // ADDING RADIO BUTTON PARA CONTROLE DA VISUALIZACAO DAS IMAGENS
        controlePanelVisualImagens = new JPanel();
        controlePanelVisualImagens.setBackground(Color.lightGray);
        controlePanelVisualImagens.setMaximumSize(new Dimension(130, 65));
        outputPanelEsq.add(controlePanelVisualImagens);

        btVisualNewImg = new JRadioButton(" new image", true);
        btVisualAllImg = new JRadioButton("transitions", false);

        btRdVisualImg = new ButtonGroup();
        btRdVisualImg.add(btVisualNewImg);
        btRdVisualImg.add(btVisualAllImg);

        btVisualNewImg.addActionListener(controlePrograma);
        btVisualAllImg.addActionListener(controlePrograma);

        visualImagensPanel = new JPanel();
        visualImagensPanel.setPreferredSize(new Dimension(120, 55));
        visualImagensPanel.setLayout(new GridLayout(2, 1));

        visualImagensPanel.add(btVisualNewImg);
        visualImagensPanel.add(btVisualAllImg);

        visualImagensPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Image Control"));

        controlePanelVisualImagens.add(visualImagensPanel);
        controlePanelVisualImagens.setVisible(false);

        // VISIBLE PANELS
        outputPanel.add(outputPanelEsq, BorderLayout.LINE_START);
        outputPanel.add(outputPanelCen, BorderLayout.CENTER);
        outputPanel.add(outputPanelDir, BorderLayout.LINE_END);

        basePanel.add(titlePanel, BorderLayout.PAGE_START);
        basePanel.add(outputPanel, BorderLayout.CENTER);
        basePanel.add(buttonPanel, BorderLayout.PAGE_END);

        baseFrame.add(basePanel);
        baseFrame.setVisible(true);
    }

    //*******************************************************************************************
    public void limpaPainelCen(Graphics desenho) {
        outputPanelCen.removeAll();
        outputPanelCen.update(desenho);
    }

    //*******************************************************************************************
    public void limpaPainelDir(Graphics desenho) {
        outputPanelDir.removeAll();
        outputPanelDir.update(desenho);
    }

    //*******************************************************************************************
    // METODO UTILIZADO PARA ADICIONAR UM BOTAO A UM CONTAINER DO PROGRAMA
    private JButton addAButton(String textoBotao,
            String textoControle,
            Container container,
            boolean estado,
            ControlarAplicativo controlePrograma) {
        JButton botao;

        botao = new JButton(textoBotao);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(botao);

        botao.setEnabled(estado);

        botao.setActionCommand(textoControle);

        botao.addActionListener(controlePrograma);

        return (botao);
    }

    //*******************************************************************************************
    public void mudarBotoes() {
        btAcao3.setEnabled(true);
        btAcao1.setEnabled(true);
        btSalva.setEnabled(true);
        btReset.setEnabled(true);
        btAcao4.setEnabled(true);
        controlePanelAcao3.setVisible(true);
        controlePanelAcao1.setVisible(true);
        controlePanelAcao2.setVisible(true);
        controlePanelVisualImagens.setVisible(true);
        controlePanelAcao4.setVisible(true);
    }

    //*******************************************************************************************
    // METODO PARA APRESENTAR O MENU DE ESCOLHA DE ARQUIVOS
    // 1 - PARA LEITURA
    // 2 - PARA GRAVACAO
    public String escolherArquivo(int operacao) {
        int retorno;
        String caminhoArquivo;
        JFileChooser arquivo;

        retorno = 0;
        arquivo = new JFileChooser(new File("."));

        // TIPO DE OPERACAO A SER REALIZADA
        switch (operacao) {
            case 1:
                retorno = arquivo.showOpenDialog(null);
                break;

            case 2:
                retorno = arquivo.showSaveDialog(null);
        }

        // OPERACAO
        caminhoArquivo = null;

        if (retorno == JFileChooser.APPROVE_OPTION) {
            try {
                caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("erro: " + e);
            }
        }

        return (caminhoArquivo);
    }

    //*******************************************************************************************
    // METODO PARA MOSTRAR O FRAME BASICO
    public void showPanel() {
        basePanel.setVisible(true);
    }

    //*******************************************************************************************
    public void ativarPainelAcao3() {
        controlePanelAcao3.setVisible(true);
    }

    //*******************************************************************************************
    public void desativarPainelAcao3() {
        controlePanelAcao3.setVisible(false);
    }

    //*******************************************************************************************
    public void ativarPainelAcao1() {
        controlePanelAcao1.setVisible(true);
    }

    //*******************************************************************************************
    public void desativarPainelAcao1() {
        controlePanelAcao1.setVisible(false);
    }

    //*******************************************************************************************
    public void iniciarGraphics() {
        desenhoCen = outputPanelCen.getGraphics();
        desenhoDir = outputPanelDir.getGraphics();
    }

    //*******************************************************************************************
    public Graphics getDesenhoC() {
        return (desenhoCen);
    }

    //*******************************************************************************************
    public Graphics getDesenhoD() {
        return (desenhoDir);
    }

    //******************************************************************************************
    public int getTipoVisualImage() {
        int tipo;

        tipo = 1;
        if (btVisualAllImg.isSelected()) {
            tipo = 2;
        }

        return (tipo);
    }

    //******************************************************************************************
    public void resetaSistema() {
        btAcao11.setSelected(true);
        btAcao21.setSelected(true);
        btAcao31.setSelected(true);
        btVisualNewImg.setSelected(true);
    }
    //******************************************************************************************
}
