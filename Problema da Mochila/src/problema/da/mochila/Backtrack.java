package problema.da.mochila;

/**
 *
 * @author Waislan Sanches
 */
public class Backtrack {
    
    public static void main(String[] args) throws Exception {
        int[] solucaoTemporaria = new int[10];
        int index = 0;
        int pesoAtual = 0;
        int valorAtual = 0;
        Item[] itens = new Item[10];
        
        itens[0] = new Item(1140, 44); 
        itens[1] = new Item(1131, 18);
        itens[2] = new Item(1174, 63);
        itens[3] = new Item(850, 72);
        itens[4] = new Item(500, 79);
        itens[5] = new Item(806, 4);
        itens[6] = new Item(1031, 39);
        itens[7] = new Item(114, 54);
        itens[8] = new Item(194, 71);
        itens[9] = new Item(239, 78);
        
        inicializar(5956, 10, itens);
        
        for (int i = 0; i < solucaoTemporaria.length; i++){
            solucaoTemporaria[i] = 0;
        }
        Backtrack backtrack = new Backtrack();
        backtrack.backtracking(solucaoTemporaria, index, pesoAtual, valorAtual);
        backtrack.printaResultado();
    }

    private static int capacidade;
    private static int quantidadeItens;
    private static int[] decisoes;
    private static int[] solucao;
    private static Item[] itens;

    public static void inicializar(int capacidade, int quantidadeItens, Item[] itens) throws Exception {
        if (quantidadeItens != itens.length) {
            throw new Exception("Quantidade de itens diferente de itens.length");
        }

        Backtrack.capacidade = capacidade;
        Backtrack.quantidadeItens = quantidadeItens;
        Backtrack.itens = new Item[itens.length];
        System.arraycopy(itens, 0, Backtrack.itens, 0, itens.length);
        Backtrack.solucao = new int[itens.length];
        
        for (int i = 0; i < itens.length; i++){
            solucao[i] = 0;
        }
    }

    public boolean ultrapassaCapacidade(int index, int pesoAtual) {
        return pesoAtual + itens[index].getPeso() > capacidade;
    }

    public void atualizaSolucao(int index) {
        solucao[index] = 1;
    }

    public boolean chegouNaFolha(int index) {
        return index == quantidadeItens-1;
    }

    public boolean solucaoOtima(int index, int valorSolucaoTemporaria, int valorSolucao) {
        return itens[index].getValor() + valorSolucaoTemporaria > valorSolucao;
    }

    public int pesoSolucao(){
        int retorno = 0;
        for (int i = 0; i < solucao.length; i++){
            if (solucao[i] == 1){
                retorno += itens[i].getPeso();
            }
        }
        return retorno;
    }
    
    public int valorSolucao(){
        int retorno = 0;
        for (int i = 0; i < solucao.length; i++){
            if (solucao[i] == 1){
                retorno += itens[i].getValor();
            }
        }
        return retorno;
    }
    
    public void printaResultado() {
        for (int i = 0; i < solucao.length; i++){
            System.out.print(solucao[i] + " ");
        }
    }

    // solucaoTemporaria deve ser inicializada com 0
    public void backtracking(int[] solucaoTemporaria, int index, int pesoAtual, int valorAtual) {
        
        if (ultrapassaCapacidade(index, pesoAtual)) {
            return;
        }
        if (chegouNaFolha(index)) {
            if (solucaoOtima(index, valorAtual, valorSolucao())) {
                atualizaSolucao(index);
                printaResultado();
                System.out.println("");
                pesoAtual += itens[index].getPeso();
                valorAtual += itens[index].getValor();
            }
            return;
        }
        backtracking(solucaoTemporaria, index+1, pesoAtual, valorAtual);
        
        solucaoTemporaria[index] = 1;
        pesoAtual += itens[index].getPeso();
        valorAtual += itens[index].getValor();
        backtracking(solucaoTemporaria, index+1, pesoAtual, valorAtual);
    }
}

  
