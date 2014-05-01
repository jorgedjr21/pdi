/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author marciovbarbosa
 */
public class FiltroGabor {

    private int tamKernel;
    private double theta, sigma, lambda, gamma, offset;
    private double matrizConvolucao[][];

    public FiltroGabor(int tamKernel, double theta, double sigma, double lambda, double gamma, double offset) {
        this.tamKernel = tamKernel;
        this.theta = theta;
        this.sigma = sigma;
        this.lambda = lambda;
        this.gamma = gamma;
        this.offset = offset;
        
        double xl, yl, xz, yz;
        for (int x = 0; x < tamKernel; x++) {
            for (int y = 0; y < tamKernel; y++) {
                xl = x - 2;
                yl = y - 2;
                xz = xl * Math.cos(theta) + yl * Math.sin(theta);
                yz = -xl * Math.sin(theta) + yl * Math.cos(theta);
                matrizConvolucao[x][y] = Math.exp(-(xz * xz + gamma * gamma * yz * yz) / (2 * sigma * sigma)) * Math.cos(2 * Math.PI * xz / lambda + offset);
            }
        }
        for (int x = 0; x < tamKernel; x++) {
            for (int y = 0; y < tamKernel; y++) {
                System.out.printf("%f ", matrizConvolucao[x][y]);
            }
            System.out.printf("\n)");
        }
    }
}
