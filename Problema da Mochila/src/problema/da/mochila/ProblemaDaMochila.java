package problema.da.mochila;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

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
        
        File pasta = new File("../knapsack");
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
            i = 0;
            br = new BufferedReader(new FileReader(arquivo));
            
            if ((linha = br.readLine()) != null) {
                ProgramacaoDinamica.capacidadeMochila = Integer.valueOf(linha);
            }
            ProgramacaoDinamica.mochila = new int[ProgramacaoDinamica.quantidadeItens + 1][ProgramacaoDinamica.capacidadeMochila + 1];
            
            while ((linha = br.readLine()) != null) {
                ProgramacaoDinamica.peso[i] = Integer.valueOf(linha.split(" ")[0]);
                ProgramacaoDinamica.valor[i] = Integer.valueOf(linha.split(" ")[1]);
                i++;
            }
            ProgramacaoDinamica.start();
        }
        
    }
    
}
