package pacoteBase08.MODEL;

public class FiltroGabor {

    private int tamKernel;
    private double theta, sigma, lambda, gamma, offset;
    private double matrizConvolucao[][];

    public double[][] getMatrizConvolucao() {
        return matrizConvolucao;
    }

    public int getTamKernel() {
        return tamKernel;
    }

    public FiltroGabor(int tamKernel, double theta, double sigma, double lambda, double gamma, double offset) {
        this.tamKernel = tamKernel;
        this.theta = theta;
        this.sigma = sigma;
        this.lambda = lambda;
        this.gamma = gamma;
        this.offset = offset;
        matrizConvolucao = new double[tamKernel][tamKernel];

        double xl, yl, xz, yz;
        for (int x = 0; x < tamKernel; x++) {
            for (int y = 0; y < tamKernel; y++) {
                xl = x - (tamKernel / 2);
                yl = y - (tamKernel / 2);
                xz = xl * Math.cos(theta) + yl * Math.sin(theta);
                yz = -xl * Math.sin(theta) + yl * Math.cos(theta);
                matrizConvolucao[x][y] = Math.exp(-(xz * xz + gamma * gamma * yz * yz) / (2 * sigma * sigma)) * Math.cos(2 * Math.PI * xz / lambda + offset);
            }
        }
        double sum = 0;
        for (int x = 0; x < tamKernel; x++) {
            for (int y = 0; y < tamKernel; y++) {
                sum += matrizConvolucao[x][y];
            }
        }

        for (int x = 0; x < tamKernel; x++) {
            for (int y = 0; y < tamKernel; y++) {
                matrizConvolucao[x][y] /= sum;

            }
        }
    }
}
