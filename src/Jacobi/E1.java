/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Jacobi;

/**
 *
 * @author alons
 */
public class E1 {
    public static final double EPSILON = 0.0001;
    public static final int MAX_ITERATIONS = 100;

    public static void main(String[] args) {
        double[][] coefficients = {{2, -1, 0}, {-1, 2, -1}, {0, -1, 2}}; 
        double[] constants = {1, 0, 1}; 

        double[] solution = solveJacobi(coefficients, constants);
        
        System.out.println("Solución encontrada:");
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solution[i]);
        }
    }

    public static double[] solveJacobi(double[][] coefficients, double[] constants) {
        int n = constants.length;
        double[] solution = new double[n];
        double[] nextSolution = new double[n];
        int iterations = 0;
        boolean converged = false;

        while (!converged && iterations < MAX_ITERATIONS) {
            for (int i = 0; i < n; i++) {
                double sum = constants[i];
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= coefficients[i][j] * solution[j];
                    }
                }
                nextSolution[i] = sum / coefficients[i][i];
            }

            double maxDifference = 0.0;
            for (int i = 0; i < n; i++) {
                double difference = Math.abs(nextSolution[i] - solution[i]);
                if (difference > maxDifference) {
                    maxDifference = difference;
                }
            }

            if (maxDifference < EPSILON) {
                converged = true;
            }

            for (int i = 0; i < n; i++) {
                solution[i] = nextSolution[i];
            }

            iterations++;
        }

        if (iterations == MAX_ITERATIONS) {
            System.out.println("El método no converge después de " + MAX_ITERATIONS + " iteraciones.");
        }

        return solution;
    }
}