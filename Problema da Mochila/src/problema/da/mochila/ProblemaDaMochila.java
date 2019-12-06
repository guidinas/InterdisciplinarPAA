package problema.da.mochila;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import static problema.da.mochila.Backtracking.start;

/**
 *
 * @author Waislan Sanches
 */
public class ProblemaDaMochila {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        File pasta = new File("../tmp");
        BufferedReader br;
        LineNumberReader linhaLeitura;
        String linha;
        int i;

        for (File arquivo : pasta.listFiles()) {
            linhaLeitura = new LineNumberReader(new FileReader(arquivo));
            linhaLeitura.skip(arquivo.length());
            ProgramacaoDinamica.quantidadeItens = linhaLeitura.getLineNumber();
            ProgramacaoDinamica.peso = new int[ProgramacaoDinamica.quantidadeItens];
            ProgramacaoDinamica.valor = new int[ProgramacaoDinamica.quantidadeItens];
            int backtrackingCapacidade = 0;
            int[] backtrackingPesos = new int[linhaLeitura.getLineNumber()-1];
            int[] backtrackingValores = new int[linhaLeitura.getLineNumber()-1];
            int[] backtrackingSolucao;
            int[] backtrackingSolucaoFinal;
            i = 0;
            br = new BufferedReader(new FileReader(arquivo));
            
            if ((linha = br.readLine()) != null) {
                ProgramacaoDinamica.capacidadeMochila = Integer.valueOf(linha);
                backtrackingCapacidade = Integer.valueOf(linha);
            }
            ProgramacaoDinamica.mochila = new int[ProgramacaoDinamica.quantidadeItens + 1][ProgramacaoDinamica.capacidadeMochila + 1];
            
            while ((linha = br.readLine()) != null) {
                ProgramacaoDinamica.peso[i] = Integer.valueOf(linha.split(" ")[0]);
                ProgramacaoDinamica.valor[i] = Integer.valueOf(linha.split(" ")[1]);
                backtrackingPesos[i] = Integer.valueOf(linha.split(" ")[0]);
                backtrackingValores[i] = Integer.valueOf(linha.split(" ")[1]);
                i++;
            }
            backtrackingSolucao = new int[backtrackingPesos.length];
            backtrackingSolucaoFinal = new int[backtrackingValores.length];
            
            System.out.println("---- Programação Dinâmica ----");
            ProgramacaoDinamica.start();
            
            System.out.println();
            System.out.println("---- Backtracking ----");
            Backtracking.start(backtrackingPesos, backtrackingValores, backtrackingCapacidade, 0, backtrackingSolucao, backtrackingSolucaoFinal);
            
            System.out.print("Itens na mochila: ");
            for (i = 0; i < backtrackingSolucaoFinal.length; i++) {
                if (backtrackingSolucaoFinal[i] != 0 && i < backtrackingSolucaoFinal.length-1){
                    System.out.print((i+1) + ", ");
                } else if (backtrackingSolucaoFinal[i] != 0){
                    System.out.println(i+1);
                }
            }
            System.out.println();
        }
    }
    
}
