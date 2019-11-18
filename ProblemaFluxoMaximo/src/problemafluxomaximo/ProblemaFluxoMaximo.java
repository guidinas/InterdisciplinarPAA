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
        
//        File arquivos[];
//        File diretorio = new File("C:\\Users\\Luziane Freitas\\ProjetosGit\\InterdisciplinarPAA\\max_flow");
//        arquivos = diretorio.listFiles();
//        for (int i = 0; i < arquivos.length; i++) {
//            BufferedReader br = new BufferedReader(new FileReader(arquivos[i]));
//            String vetor[] = null;
//            String linha;
//            int vetorInt[] = null;
//            ArrayList<Integer> vetorInteiro = new ArrayList(); 
//            
//            while (br.ready()) {
//                linha = br.readLine();
//                vetor = linha.split(" ");
//            }
//            br.close();
//        }
           // Let us create a graph shown in the above example 
        int graph[][] =new int[][] { {0,0,0,100,0,0,0,0},
            {26,0,0,0,0,0,0,80},
            {50,40,0,15,0,0,39,0},
            {80,0,85,0,0,0,0,0},
            {24,0,0,0,0,0,100,18},
            {0,0,0,56,99,0,0,90},
            {0,0,0,0,0,26,0,0},
            {0,0,0,0,0,0,22,0} 
                                   }; 
        Grafo m = new Grafo(); 
  
        System.out.println("The maximum possible flow is " +m.fordFulkerson(graph, 0, 7)); 
  


    }
}
