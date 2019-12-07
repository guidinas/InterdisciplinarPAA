package problemafluxomaximo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProblemaFluxoMaximo {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File pasta = new File("../max_flow");
        BufferedReader br;
        String linha;
        String[] auxiliar;
        int i;
        int[][] matriz;
        int tamanhoMatriz = 0;
        Grafo g;

        for (File arquivo : pasta.listFiles()) {
            i = 0;
            br = new BufferedReader(new FileReader(arquivo));

            if ((linha = br.readLine()) != null) {
                tamanhoMatriz = linha.split(" ").length;
            }
            matriz = new int[tamanhoMatriz][tamanhoMatriz];
            
            br = new BufferedReader(new FileReader(arquivo));
            
            while ((linha = br.readLine()) != null && tamanhoMatriz > 0) {
                auxiliar = linha.split(" ");
                for (int j = 0; j < tamanhoMatriz; j++) {
                    matriz[i][j] = Integer.valueOf(auxiliar[j]);
                }
                i++;
            }
            g = new Grafo(tamanhoMatriz);
            System.out.println("O máximo fluxo possível é " + g.fordFulkerson(matriz, 0, tamanhoMatriz-1));
        }
    }
}
