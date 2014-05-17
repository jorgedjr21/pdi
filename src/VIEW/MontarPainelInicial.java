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
    private JLabel lbAngulo;
    private JTextField tfTheta;
    private JTextField tfSigma;
    private JTextField tfGamma;
    private JTextField tfLambda;
    private JTextField tfOffset;
    private JTextField tfAngulo;
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
        btSalva = addAButton("Save", "botaoSalva", buttonPanel, false, controlePrograma);
        addAButton("END", "botaoFim", buttonPanel, true, controlePrograma);

        // CONFIGURANDO A INTERFACE DOS PARÂMETROS DO FILTRO GABOR E DA ROTAÇÃO
        //Configurando botão de aplicação do filtro
        btAcao3 = new JButton("Aplicar Filtro");
        btAcao3.setActionCommand("botaoFiltroGabor");
        btAcao3.addActionListener(controlePrograma);


        //Configurando botão de aplicação da rotação
        btAcao1 = new JButton("Rotacionar");
        btAcao1.setActionCommand("botaoRotacionar");
        btAcao1.addActionListener(controlePrograma);

        //IINICIALIZANDO LABELS
        lbTheta = new JLabel("θ:");
        lbSigma = new JLabel("σ:");
        lbLambda = new JLabel("λ:");
        lbGamma = new JLabel("γ:");
        lbOffset = new JLabel("φ:");
        lbAngulo = new JLabel("Ângulo:");

        //Adicionando tooltips
        lbTheta.setToolTipText("Orientação do filtro em graus. Aceitável qualquer valor entre 0 e 360.");
        lbSigma.setToolTipText("Desvio padrão do componente Gaussiano. Aceitável qualquer valor maior que zero e menor que o tamanho do kernel utilizado.");
        lbLambda.setToolTipText("Comprimento de onda do componente cossenoidal do filtro. Especificado em pixels, aceitável qualquer valor maior que dois.");
        lbGamma.setToolTipText("Relação de aspecto. Define o quão elíptica é a função. Aceitável qualquer valor maior que 0 e menor ou igual a 1.");
        lbOffset.setToolTipText("Deslocamento da função cosseno. Zero por padrão, mas é aceitável qualquer valor.");
        lbAngulo.setToolTipText("Ângulo, em graus, pelo qual a imagem será rotacionada utilizando interpolação pelo método do vizinho mais próximo.");

        //INICIALIZANDO CAMPOS DE TEXTO
        tfTheta = new JTextField("0");
        tfSigma = new JTextField("1");
        tfLambda = new JTextField("3");
        tfGamma = new JTextField("0.5");
        tfOffset = new JTextField("0");
        tfAngulo = new JTextField("90");

        controlePanelAcao3 = new JPanel();
        controlePanelAcao3.setBackground(Color.lightGray);
        controlePanelAcao3.setMaximumSize(new Dimension(200, 200));
        outputPanelEsq.add(controlePanelAcao3);

        acao3Panel = new JPanel();
        acao3Panel.setPreferredSize(new Dimension(120, 150));
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
        controlePanelAcao3.add(btAcao3);
        controlePanelAcao3.setVisible(false);

        // CONFIGURANDO ÁREA DE CONTROLE DA ROTAÇÃO
        controlePanelAcao1 = new JPanel();
        controlePanelAcao1.setBackground(Color.lightGray);
        controlePanelAcao1.setMaximumSize(new Dimension(130, 110));
        controlePanelAcao1.setVisible(false);
        acao1Panel = new JPanel();
        acao1Panel.setPreferredSize(new Dimension(120, 45));
        acao1Panel.setLayout(new GridLayout(1, 2));

        acao1Panel.add(lbAngulo);
        acao1Panel.add(tfAngulo);
        acao1Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Rotação"));
        controlePanelAcao1.add(acao1Panel);
        controlePanelAcao1.add(btAcao1);
        outputPanelEsq.add(controlePanelAcao1);

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
        controlePanelAcao3.setVisible(true);
        controlePanelAcao1.setVisible(true);
        controlePanelVisualImagens.setVisible(true);
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
        btVisualNewImg.setSelected(true);
    }
    //******************************************************************************************

    public float getAngulo() {
        return Float.parseFloat(tfAngulo.getText());
    }

    public float getTheta() {
        return (float) (Float.parseFloat(tfTheta.getText()) * Math.PI / 180);
    }

    public float getSigma() {
        return Float.parseFloat(tfSigma.getText());
    }

    public float getGamma() {
        return Float.parseFloat(tfGamma.getText());
    }

    public float getLambda() {
        return Float.parseFloat(tfLambda.getText());
    }

    public float getOffset() {
        return Float.parseFloat(tfOffset.getText());
    }
}