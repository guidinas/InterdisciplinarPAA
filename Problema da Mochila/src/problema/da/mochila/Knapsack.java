package problema.da.mochila;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import static problema.da.mochila.Backtracking.start;
import problema.da.mochila.Item;

public class Knapsack {

    // items of our problem
    private Item[] items;
    // capacity of the bag
    private int capacity;

    public Knapsack(Item[] items, int capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    public void display() {
        if (items != null && items.length > 0) {
            System.out.println("Knapsack problem");
            System.out.println("Capacity : " + capacity);
            System.out.println("Items :");

            for (Item item : items) {
                System.out.println("- " + item.str());
            }
        }
    }

    // we write the solve algorithm
    public Solution solve() {
        int NB_ITEMS = items.length;
        // we use a matrix to store the max value at each n-th item
        int[][] matrix = new int[NB_ITEMS + 1][capacity + 1];

        // first line is initialized to 0
        for (int i = 0; i <= capacity; i++) {
            matrix[0][i] = 0;
        }

        // we iterate on items
        for (int i = 1; i <= NB_ITEMS; i++) {
            // we iterate on each capacity
            for (int j = 0; j <= capacity; j++) {
                if (items[i - 1].weight > j) {
                    matrix[i][j] = matrix[i - 1][j];
                } else // we maximize value at this rank in the matrix
                {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - items[i - 1].weight]
                            + items[i - 1].value);
                }
            }
        }

        int res = matrix[NB_ITEMS][capacity];
        int w = capacity;
        List<Item> itemsSolution = new ArrayList<>();

        for (int i = NB_ITEMS; i > 0 && res > 0; i--) {
            if (res != matrix[i - 1][w]) {
                itemsSolution.add(items[i - 1]);
                // we remove items value and weight
                res -= items[i - 1].value;
                w -= items[i - 1].weight;
            }
        }

        return new Solution(itemsSolution, matrix[NB_ITEMS][capacity]);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // we take the same instance of the problem displayed in the image
        Item[] items;

        File pasta = new File("../knapsack");
        BufferedReader br;
        LineNumberReader linhaLeitura;
        String linha;
        int i;

        for (File arquivo : pasta.listFiles()) {
            linhaLeitura = new LineNumberReader(new FileReader(arquivo));
            linhaLeitura.skip(arquivo.length());
            int capacidade = 0;
            int[] pesos = new int[linhaLeitura.getLineNumber() - 1];
            int[] valores = new int[linhaLeitura.getLineNumber() - 1];
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
            items = new Item[pesos.length];
            
            for (int j = 0; j < pesos.length; j++){
                items[j] = new Item(valores[j], pesos[j]);
            }
            Knapsack knapsack = new Knapsack(items, capacidade);
            knapsack.display();
            System.out.println("---- Backtracking ----");
            //System.out.print("Itens na mochila: ");
            Solution solution = knapsack.solve();
            solution.display();
        }
    }
}
