package problema.da.mochila;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 *
 * @author Waislan Sanches
 */
public class Backtracking {
    
    static int valorAuxiliar = 0;
    static int pesoAuxiliar = 0;
     
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*
        int[] pesos = {1140, 1131, 1174, 850, 500, 806, 1031, 114, 194, 239};
        int[] valores = {44, 18, 63, 72, 79, 4, 39, 54, 71, 78};
        int[] solucao = new int[pesos.length];
        int[] solucaoFinal = new int[pesos.length];
        int capacidade = 5956;
        */
        File pasta = new File("../tmp");
        BufferedReader br;
        LineNumberReader linhaLeitura;
        String linha;
        int i;

        for (File arquivo : pasta.listFiles()) {
            linhaLeitura = new LineNumberReader(new FileReader(arquivo));
            linhaLeitura.skip(arquivo.length());
            int capacidade = 0;
            int[] pesos = new int[linhaLeitura.getLineNumber()-1];
            int[] valores = new int[linhaLeitura.getLineNumber()-1];
            int[] solucao;
            int[] solucaoFinal;
            i = 0;
            br = new BufferedReader(new FileReader(arquivo));
            
            if ((linha = br.readLine()) != null) {
                ProgramacaoDinamica.capacidadeMochila = Integer.valueOf(linha);
                capacidade = Integer.valueOf(linha);
            }
            while ((linha = br.readLine()) != null) {
                pesos[i] = Integer.valueOf(linha.split(" ")[0]);
                valores[i] = Integer.valueOf(linha.split(" ")[1]);
                i++;
            }
            solucao = new int[pesos.length];
            solucaoFinal = new int[valores.length];
            
            System.out.println("---- Backtracking ----");
            start(pesos, valores, capacidade, 0, solucao, solucaoFinal);
            
            System.out.print("Itens na mochila: ");
            for (i = 0; i < solucaoFinal.length; i++) {
                if (solucaoFinal[i] != 0 && i < solucaoFinal.length-1){
                    System.out.print((i+1) + ", ");
                } else if (solucaoFinal[i] != 0){
                    System.out.println(i+1);
                }
            }
            System.out.println();
        }
        /*
        start(pesos, valores, capacidade, 0, solucao, solucaoFinal);    // removed the two parameters
        
        System.out.print("Itens na mochila: ");
        for (int i = 0; i < solucaoFinal.length; i++) {
            if (solucaoFinal[i] != 0 && i < solucaoFinal.length-1){
                System.out.print((i+1) + ", ");
            } else if (solucaoFinal[i] != 0){
                System.out.println(i+1);
            }
        }
        System.out.println();
        
        for (int i = 0; i < solucaoFinal.length; i++) {
            System.out.print(solucaoFinal[i] + " ");
        }
        */
    }
    
    public static void start(int[] pesos, int[] valores, int capacidade, int index, int[] solucao, int[] solucaoFinal) {  // removed the parameters
        solucao[index] = -1;
        int n = pesos.length;
        
        while (solucao[index] <= 1) {
            solucao[index] = solucao[index] + 1;
            if (soma(index, solucao, pesos) <= capacidade && index == n - 1) {
                atualiza(pesos, valores, capacidade, index, solucao, solucaoFinal);
                /*
                System.out.println("Solução: " + Arrays.toString(solucao));
                System.out.println("Peso = " + soma(index, solucao, pesos));
                System.out.println();
                */
            } else if (index < n - 1) {    // changed
                start(pesos, valores, capacidade, index + 1, solucao, solucaoFinal);
            }
        }
    }
  
    private static int soma(int index, int[] pesos, int[] solucao) {
        int res = 0;
//        for (int i = 0; i < index; i++) {   // thrown out
        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] < 0 ){
                System.out.println("Na soma: i = " + i + "   solução[i] = " + solucao[i]);
            }
            res += solucao[i] * pesos[i];
        }
        return res;
    }
  
    private static void atualiza(int[] pesos, int[] valores, int capacidade, int index, int[] solucao, int[] solucaoFinal) {  //removed the two parameters
        int valorTotal = 0;
        int pesoTotal = 0;
  
        for (int i = 0; i < pesos.length; i++) {
            if (solucao[i] == 1) {
                valorTotal += valores[i];
                pesoTotal += pesos[i];
            }
        }
  
        if (valorTotal > valorAuxiliar) {
            System.arraycopy(solucao, 0, solucaoFinal, 0, pesos.length);
            valorAuxiliar = valorTotal;
            pesoAuxiliar = pesoTotal;
            //System.out.println("Novo valor auxiliar: " + valorAuxiliar);   // changed
        } 
    }
}
