package Store;

/**
 * This class represents an order
 *
 * @author Wilfrid Shaun Nortey Noye (101149803)
 * @author Sebastian Becerril (101152127)
 * @version 1.0
 */

public class Order {
    public Product product;
    public int stock;

    /**
     * This method is a constructor for the order class
     *
     * @param product Product, the product
     * @param stock   Integer, stock of the specified product
     */

    public Order(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }
}

