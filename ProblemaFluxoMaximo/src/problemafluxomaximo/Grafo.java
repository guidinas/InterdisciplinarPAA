package problemafluxomaximo;

import java.util.LinkedList;


public class Grafo {
       
    private int vertices;

    public Grafo(int vertice) {
        this.vertices = vertice;
    }
    
    boolean bfs(int[][] matrizAdj, int s, int t, int[] vizinho) 
    { 
        
        boolean[] visitados = new boolean[vertices]; 
        for(int i=0; i<vertices; ++i) 
            visitados[i]=false; 
  
        
        LinkedList<Integer> filaPrioridade = new LinkedList<Integer>(); 
        filaPrioridade.add(s); 
        visitados[s] = true; 
        vizinho[s]=-1; 
  
       
        while (filaPrioridade.size()!=0) 
        { 
            int u = filaPrioridade.poll(); 
  
            for (int v=0; v<vertices; v++) 
            { 
                if (visitados[v]==false && matrizAdj[u][v] > 0) 
                { 
                    filaPrioridade.add(v); 
                    vizinho[v] = u; 
                    visitados[v] = true; 
                } 
            } 
        } 
  
       
        return (visitados[t] == true);
    }
    
    
    int fordFulkerson(int[][] grafo, int s, int t) 
    { 
        int u, v; 
  
        
        int[][] mGrafo = new int[vertices][vertices]; 
  
        for (u = 0; u < vertices; u++) 
            for (v = 0; v < vertices; v++) 
                mGrafo[u][v] = grafo[u][v]; 
  
        
        int[] pai = new int[vertices]; 
  
        int fluxoMaximo = 0;  
        
        while (bfs(mGrafo, s, t, pai)) 
        { 
             
            int caminhoMaximo = Integer.MAX_VALUE; 
            for (v=t; v!=s; v=pai[v]) 
            { 
                u = pai[v]; 
                caminhoMaximo = Math.min(caminhoMaximo, mGrafo[u][v]); 
            } 
  
            
            for (v=t; v != s; v=pai[v]) 
            { 
                u = pai[v]; 
                mGrafo[u][v] -= caminhoMaximo; 
                mGrafo[v][u] += caminhoMaximo; 
            } 
  
            
            fluxoMaximo += caminhoMaximo; 
        } 
  
        
        return fluxoMaximo; 
    } 
}
