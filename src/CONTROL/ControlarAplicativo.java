package CONTROL;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import VIEW.*;
import MODEL.FiltroGabor;
import java.awt.image.BufferedImage;

public class ControlarAplicativo implements ActionListener {

    private MontarPainelInicial pnCenario;
    private Graphics desenhoCen, desenhoDir;
    private ControlarImagem controleImagem;
    private String nomeArquivoImagemDada;
    private char[][] imagemCinza;
    private char[][] imagemAtual;
    private int nLinImageAtual, nColImageAtual;
    private int nLinImageInic, nColImageInic;
    private boolean estadoDesenho;
    private FiltroGabor filtroGabor;

    //*******************************************************************************************
    public ControlarAplicativo() {
        pnCenario = new MontarPainelInicial(this);
        pnCenario.showPanel();
        estadoDesenho = false;
    }

    //*******************************************************************************************
    // METODO PARA CONTROLE DOS BOTOES DO APLICATIVO
    public void actionPerformed(ActionEvent e) {
        String comando, nomeArquivo;

        comando = e.getActionCommand();

        // DEFINE AMBIENTE GRAFICO
        if (!estadoDesenho) {
            pnCenario.iniciarGraphics();
            desenhoCen = pnCenario.getDesenhoC();
            desenhoDir = pnCenario.getDesenhoD();
        }

        // ENDS THE PROGRAM
        if (comando.equals("botaoFim")) {
            System.exit(0);
        }

        // INICIA O PROGRAMA
        if (comando.equals("botaoImagem")) {

            // LE IMAGEM SOLICITADA
            nomeArquivoImagemDada = pnCenario.escolherArquivo(1);
            pnCenario.limpaPainelCen(desenhoCen);
            if (nomeArquivoImagemDada != null) {
                controleImagem = new ControlarImagem(nomeArquivoImagemDada, desenhoCen);
                estadoDesenho = true;
                imagemCinza = controleImagem.getImagemCinza();
                nLinImageInic = controleImagem.getNLin();
                nColImageInic = controleImagem.getNCol();

                pnCenario.mudarBotoes();
                pnCenario.limpaPainelDir(desenhoDir);
                controleImagem.mostrarImagemMatriz(imagemCinza, nLinImageInic, nColImageInic, desenhoDir);

                nLinImageAtual = nLinImageInic;
                nColImageAtual = nColImageInic;
                imagemAtual = controleImagem.copiarImagem(imagemCinza, nLinImageInic, nColImageInic);
            }
        }

        if (comando.equals("botaoFiltroGabor")) {
            if (pnCenario.getTipoVisualImage() == 2) {
                BufferedImage img;
                img = controleImagem.transformarMatriz2Buffer(imagemCinza, imagemCinza[0].length, imagemCinza.length);
                controleImagem.mostrarImagemBuffer(img, desenhoCen);
            }
            filtroGabor = new FiltroGabor(5, 3 * Math.PI / 4, 2, 2, 1, 0);
            imagemCinza = controleImagem.aplicarFiltroGabor(filtroGabor, imagemCinza);
            pnCenario.limpaPainelDir(desenhoDir);
            controleImagem.mostrarImagemMatriz(imagemCinza, imagemCinza[0].length, imagemCinza.length, desenhoDir);
            nLinImageAtual = imagemCinza[0].length;
            nColImageAtual = imagemCinza.length;
            imagemAtual = controleImagem.copiarImagem(imagemCinza, nLinImageAtual, nColImageAtual);
        }

        if (comando.equals("botaoRotacionar")) {
            float angulo = Float.parseFloat(JOptionPane.showInputDialog("Informe o Ângulo desejado:"));
            if (pnCenario.getTipoVisualImage() == 2) {
                BufferedImage img;
                img = controleImagem.transformarMatriz2Buffer(imagemCinza, imagemCinza[0].length, imagemCinza.length);
                controleImagem.mostrarImagemBuffer(img, desenhoCen);
            }
            imagemCinza = controleImagem.rotacionarImagem(angulo);
            pnCenario.limpaPainelDir(desenhoDir);
            controleImagem.mostrarImagemMatriz(imagemCinza, imagemCinza[0].length, imagemCinza.length, desenhoDir);
            nLinImageAtual = imagemCinza[0].length;
            nColImageAtual = imagemCinza.length;
            imagemAtual = controleImagem.copiarImagem(imagemCinza, nLinImageAtual, nColImageAtual);

        }

        if (comando.equals("botaoAcao11")) {
        }

        if (comando.equals("botaoAcao12")) {
        }

        if (comando.equals("botaoAcao13")) {
        }

        if (comando.equals("botaoAcao14")) {
        }

        if (comando.equals("botaoAcao15")) {
        }

        if (comando.equals("botaoAcao21")) {
        }

        if (comando.equals("botaoAcao22")) {
        }

        if (comando.equals("botaoAcao23")) {
        }

        if (comando.equals("botaoAcao24")) {
        }

        if (comando.equals("botaoAcao25")) {
        }

        if (comando.equals("botaoAcao26")) {
        }

        if (comando.equals("botaoSalva") && estadoDesenho) {
            nomeArquivo = pnCenario.escolherArquivo(2);
            controleImagem.gravarImagem(nomeArquivo, imagemAtual, nLinImageAtual, nColImageAtual);
        }

        if (comando.equals("botaoReset") && estadoDesenho) {
            pnCenario.limpaPainelCen(desenhoCen);
            controleImagem = new ControlarImagem(nomeArquivoImagemDada, desenhoCen);
            imagemCinza = controleImagem.getImagemCinza();
            nLinImageInic = controleImagem.getNLin();
            nColImageInic = controleImagem.getNCol();

            pnCenario.limpaPainelDir(desenhoDir);
            controleImagem.mostrarImagemMatriz(imagemCinza, nLinImageInic, nColImageInic, desenhoDir);

            nLinImageAtual = nLinImageInic;
            nColImageAtual = nColImageInic;
            imagemAtual = controleImagem.copiarImagem(imagemCinza, nLinImageInic, nColImageInic);
            pnCenario.ativarPainelAcao1();
            pnCenario.resetaSistema();
        }
    }

    //*******************************************************************************************
    private void controlarAcao3() {
    }
    //*******************************************************************************************
}
