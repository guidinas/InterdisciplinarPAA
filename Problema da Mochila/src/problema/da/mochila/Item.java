package problema.da.mochila;

/**
 *
 * @author Waislan Sanches
 */
public class Item {
    public int value;
    public int weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public String str() {
        return "value = " + value + ", weight = " + weight ;
    }
}
