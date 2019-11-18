package problemafluxomaximo;

import java.util.LinkedList;


public class Grafo {
       
    static final int V = 8;   
    boolean bfs(int[][] matrizAdj, int s, int t, int[] vizinho) 
    { 
        
        boolean[] visitados = new boolean[V]; 
        for(int i=0; i<V; ++i) 
            visitados[i]=false; 
  
        
        LinkedList<Integer> filaPrioridade = new LinkedList<Integer>(); 
        filaPrioridade.add(s); 
        visitados[s] = true; 
        vizinho[s]=-1; 
  
       
        while (filaPrioridade.size()!=0) 
        { 
            int u = filaPrioridade.poll(); 
  
            for (int v=0; v<V; v++) 
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
  
        
        int[][] mGrafo = new int[V][V]; 
  
        for (u = 0; u < V; u++) 
            for (v = 0; v < V; v++) 
                mGrafo[u][v] = grafo[u][v]; 
  
        
        int[] pai = new int[V]; 
  
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
