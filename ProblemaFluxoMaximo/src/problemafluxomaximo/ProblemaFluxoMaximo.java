package problemafluxomaximo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemaFluxoMaximo {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        File arquivos[];
        File diretorio = new File("C:\\Users\\Luziane Freitas\\ProjetosGit\\InterdisciplinarPAA\\max_flow");
        arquivos = diretorio.listFiles();
        for (int i = 0; i < arquivos.length; i++) {
            BufferedReader br = new BufferedReader(new FileReader(arquivos[i]));
            String vetor[] = null;
            String linha;
            int vetorInt[] = null;
            ArrayList<Integer> vetorInteiro = new ArrayList(); 

            while (br.ready()) {
                linha = br.readLine();
                vetor = linha.split(" ");
                for(int j = 0; j < vetor.length; j++){
                    vetorInteiro.add(Integer.parseInt(vetor[j]));
                }

            }
            //Aqui criar o grafo e inicializar a 
            for(int k = 0; k < vetorInteiro.size(); k++){
                System.out.printf(vetorInteiro.get(k)+" ");
            }
            System.out.println();
            
//            g = new Grafo(vetorInteiro.size());
//            g.inicializaMatriz(vetorInteiro);
//            g.preencheMatriz(vetorInteiro);
//            g.exibirmatriz();
            
            br.close();
        }
    }
}
