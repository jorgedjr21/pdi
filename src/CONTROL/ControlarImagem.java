package CONTROL;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import static java.lang.StrictMath.*;
import javax.imageio.ImageIO;
import MODEL.FiltroGabor;

public class ControlarImagem {

    private char[][] imagemCinza;
    private int nLinImagem;
    private int nColImagem;
    private BufferedImage imagemDada;

    //*******************************************************************************************
    public ControlarImagem(String nomeArquivoImagemDada, Graphics desenho) {
        imagemDada = lerImagem(nomeArquivoImagemDada);
        if (imagemDada != null) {
            mostrarImagemBuffer(imagemDada, desenho);
            criarImagemCinza(imagemDada);
        }
    }

    public char[][] rotacionarImagem(float angulo) {
        char[][] imagemRotacionada = null;
        char[][] imagemOriginal = getImagemCinza();
        /*ROTACIONAR 90º SENTIDO HORARIO */
        if (angulo == 90.0 || angulo == -270.0) {
            imagemRotacionada = horario(imagemOriginal);
        }
        /*ROTACIONAR 90º SENTIDO ANTIHORARIO */
        if (angulo == -90.0 || angulo == 270.0) {
            imagemRotacionada = antiHorario(imagemOriginal);
        }
        /*ROTACIONAR 180º SENTIDO HORARIO */
        if (angulo == 180 || angulo == -180) {
            imagemRotacionada = horario(imagemOriginal);
            imagemRotacionada = horario(imagemRotacionada);
        }
        /*ROTACIONAR 180º SENTIDO ANTI-HORARIO */
        if (angulo % 90 != 0) {
            imagemRotacionada = rotacionar(angulo, imagemCinza);
        }
        imagemCinza = imagemRotacionada;
        return imagemRotacionada;
    }

    private char[][] horario(char[][] imagem) {
        int nLin, nCol;
        nLin = imagem.length;
        nCol = imagem[0].length;

        char[][] rotacionada = new char[nCol][nLin];

        for (int i = 0; i < nLin; i++) {
            for (int j = 0; j < nCol; j++) {
                rotacionada[nCol - 1 - j][i] = imagem[i][j];
            }
        }
        setnColImagem(nLin);
        setnLinImagem(nCol);
        return rotacionada;
    }

    private char[][] antiHorario(char[][] imagem) {
        int nLin, nCol;
        nLin = imagem.length;
        nCol = imagem[0].length;

        char[][] rotacionada = new char[nCol][nLin];

        for (int i = 0; i < nLin; i++) {
            for (int j = 0; j < nCol; j++) {
                rotacionada[j][nLin - 1 - i] = imagem[i][j];
            }
        }
        setnColImagem(nLin);
        setnLinImagem(nCol);
        return rotacionada;
    }

    /*ROTACIONAR EM UM ANGULO QUALQUER
     */
    private char[][] rotacionar(float angulo, char[][] imagem) {
        int largura, altura;
        largura = imagem.length;
        altura = imagem[0].length;

        System.out.println("largura = " + largura + " altura = " + altura);
        int r = (int) Math.round(sqrt((largura * largura) + (altura * altura)));
        char[][] rotacionada = new char[r][r];

        double sena, cosa;
        angulo = angulo * -1;
        sena = Math.sin(angulo * Math.PI / 180);
        cosa = Math.cos(angulo * Math.PI / 180);

        for (int yl = 0; yl < r; yl++) {
            for (int xl = 0; xl < r; xl++) {
                int x = (int) Math.round((xl - r / 2) * cosa + (yl - r / 2) * sena + altura / 2);
                int y = (int) Math.round(-(xl - r / 2) * sena + (yl - r / 2) * cosa + largura / 2);
                if (x < altura && x >= 0 && y < largura && y >= 0) {
                    rotacionada[yl][xl] = imagem[y][x];
                }
            }
        }
        return rotacionada;
    }

    public char[][] aplicarFiltroGabor(FiltroGabor filtro, char[][] imagem) {
         int nLin, nCol;
        nLin = imagem.length;
        nCol = imagem[0].length;

        char resultado[][] = new char[nLin][nCol];
        double kernel[][] = filtro.getMatrizConvolucao();
        int tamKernel = filtro.getTamKernel();
        int ii, jj, nn, mm;

        for (int i = 0; i < nLin; i++) {
            for (int j = 0; j < nCol; j++) {

                for (int m = 0; m < tamKernel; m++) {
                    mm = tamKernel - 1 - m;
                    for (int n = 0; n < tamKernel; n++) {
                        nn = tamKernel - 1 - n;

                        ii = i + (m - tamKernel);
                        jj = j + (n - tamKernel);

                        if (ii >= 0 && ii < nLin && jj >= 0 && jj < nCol) {
                            resultado[i][j] += imagem[ii][jj] * kernel[mm][nn];
                        }
                    }
                }
            }
        }
        imagemCinza = resultado;
        return resultado;
    }

    //*******************************************************************************************
    // METODO PARA GERAR A IMAGEM RASTER EM NIVEIS DE CINZA A PARTIR DA IMAGEM BUFERIZADA COLORIDA
    private void criarImagemCinza(BufferedImage imagem) {
        int x, y, r, g, b;
        Raster imagemRasterEntrada;
        char valorSaida;

        // DIMENSOES DA MATRIZ CINZA
        setnColImagem(imagem.getWidth(null));
        setnLinImagem(imagem.getHeight(null));
        imagemCinza = new char[nColImagem][nLinImagem];

        // DEFININDO IMAGENS INTERMEDIARIAS
        imagemRasterEntrada = imagem.getRaster();

        // CRIANDO IMAGEM CINZA
        for (y = 0; y < nLinImagem; y++) {
            for (x = 0; x < nColImagem; x++) {

                // LENDA DADOS DAS BANDAS DA IMAGEM DADA
                r = imagemRasterEntrada.getSample(x, y, 0);  // le dado da banda 0 da imagem de entrada 

                try {
                    g = imagemRasterEntrada.getSample(x, y, 1);  // le dado da banda 1 da imagem de entrada 
                    b = imagemRasterEntrada.getSample(x, y, 2);  // le dado da banda 2 da imagem de entrada
                } catch (ArrayIndexOutOfBoundsException e) {
                    g = r;
                    b = r;
                }

                //  GERANDO NIVEL DE CINZA 
                valorSaida = (char) ((r + g + b) / 3);
                imagemCinza[x][y] = valorSaida;
            }
        }
    }

    //******************************************************************************************
    public char[][] copiarImagem(char[][] imagemC, int nLinImg, int nColImg) {
        int x, y;
        char[][] imagemR;

        imagemR = new char[nColImg][nLinImg];

        for (x = 0; x < nColImg; x++) {
            for (y = 0; y < nLinImg; y++) {
                imagemR[x][y] = imagemC[x][y];
            }
        }

        return (imagemR);
    }

    //*******************************************************************************************
    private BufferedImage lerImagem(String nomeArquivo) {
        File arquivoImagem;
        BufferedImage imagem;

        // INICIALIZANDO VARIAVEIS
        imagem = null;
        arquivoImagem = new File(nomeArquivo);

        // LEITURA DA IMAGEM
        try {
            imagem = ImageIO.read(arquivoImagem);
        } catch (IOException e) {
            System.out.println("imagem " + nomeArquivo + " nao existe");
        }

        return (imagem);
    }

    //*******************************************************************************************
    // MOSTRAR IMAGEM BUFERIZADA
    public void mostrarImagemBuffer(BufferedImage imagem, Graphics desenho) {
        int imageWidth, imageHeight, x, sx, y, sy, cell, dx, dy;
        int cells[] = {0, 1, 2, 3};

        imageWidth = imagem.getWidth(null);
        imageHeight = imagem.getHeight(null);

        for (x = 0; x < 2; x++) {
            sx = x * imageWidth;
            for (y = 0; y < 2; y++) {
                sy = y * imageHeight;
                cell = cells[x * 2 + y];
                dx = (cell / 2) * imageWidth;
                dy = (cell % 2) * imageHeight;
                desenho.drawImage(imagem, dx, dy, x + imageWidth, dy + imageHeight,
                        sx, sy, sx + imageWidth, sy + imageHeight, null);
            }
        }
    }

    //*******************************************************************************************
    // MOSTRAR IMAGEM DO TIPO MATRIZ DE BYTES
    public void mostrarImagemMatriz(char[][] imagemM, int nLin, int nCol, Graphics desenho) {
        BufferedImage imagemB;

        imagemB = transformarMatriz2Buffer(imagemM, nLin, nCol);
        desenho.drawImage(imagemB, 0, 0, nCol, nLin, null);
    }

    //*******************************************************************************************
    private BufferedImage transformarMatriz2Buffer(char[][] imagemM, int nLin, int nCol) {
        int x, y;
        char valorSaida;
        WritableRaster imagemRasterSaida;
        BufferedImage imagemB;

        imagemB = new BufferedImage(nCol, nLin, BufferedImage.TYPE_BYTE_GRAY);
        imagemRasterSaida = imagemB.getRaster();

        for (y = 0; y < nLin; y++) {
            for (x = 0; x < nCol; x++) {
                valorSaida = imagemM[x][y];
                imagemRasterSaida.setSample(x, y, 0, valorSaida);
            }
        }

        return (imagemB);
    }

    //*******************************************************************************************
    public void gravarImagem(String nomeArquivo,
            char[][] imagemM,
            int nLin,
            int nCol) {
        File arquivoImagem;
        BufferedImage imagemB;

        imagemB = transformarMatriz2Buffer(imagemM, nLin, nCol);

        // INICIALIZANDO VARIAVEIS
        arquivoImagem = new File(nomeArquivo + ".jpg");

        // LEITURA DA IMAGEM
        try {
            ImageIO.write(imagemB, "jpg", arquivoImagem);
        } catch (IOException e) {
            System.out.println("imagem " + nomeArquivo + " nao existe");
        }
    }

    //*******************************************************************************************
    public char[][] getImagemCinza() {
        return (imagemCinza);
    }

    //*******************************************************************************************
    public int getNLin() {
        return (nLinImagem);
    }

    //*******************************************************************************************
    public int getNCol() {
        return (nColImagem);
    }
    //*******************************************************************************************

    /**
     * @param nLinImagem the nLinImagem to set
     */
    public void setnLinImagem(int nLinImagem) {
        this.nLinImagem = nLinImagem;
    }

    /**
     * @param nColImagem the nColImagem to set
     */
    public void setnColImagem(int nColImagem) {
        this.nColImagem = nColImagem;
    }
}
