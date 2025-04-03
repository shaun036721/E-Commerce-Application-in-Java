package Store;

/**
 * This class represents a shopping cart
 *
 * @author Wilfrid Shaun Nortey Noye (101149803)
 * @author Sebastian Becerril (101152127)
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements ProductStockContainer {

    private ArrayList<Order> carts = new ArrayList<Order>();
    private int numOfProducts = 0;
    private int id;

    /**
     * Constructor for the shopping carts class
     *
     * @param id Integer, id of the shopping carts
     */

    public ShoppingCart(int id) {
        this.id = id;
    }

    /**
     * A method to get the product quantity
     *
     * @param id, Integer, the ID of the product
     * @return    Integer, the quantity of the specified product.
     */

    public int getProductQuantity(int id) {
        for (Order cart : carts) {
            if (cart.product.id == id) {
                return cart.stock;
            }
        }
        return -1;
    }

    /**
     * Add a certain quantity of a product
     *
     * @param p      Product, the product to be added
     * @param amount Integer, the amount of the specified product to be added
     */

    public void addProductQuantity(Product p, int amount) {
        if (p.id < 0) return;

        for (int i = 0; i < carts.size(); i++) {
            Order pair = carts.get(i);
            if (pair.product.id == p.id) {
                carts.set(i, new Order(p, pair.stock + amount));
            }
        }
        carts.add(new Order(p, amount));
        numOfProducts++;
    }

    /**
     * Remove a certain quantity of a product
     *
     * @param p       Product, the product to be removed
     * @param amount  Integer, the amount of the specified product to be removed
     * @return
     */

    public boolean removeProductQuantity(Product p, int amount) {
        for (int i = 0; i < carts.size(); i++) {
            Order pair = carts.get(i);
            if (pair.product.id == p.id) {
                if (pair.stock >= amount) {
                    carts.set(i, new Order(pair.product, pair.stock - amount));
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * Method to return the number of products in the shopping cart
     *
     * @return integer, the amount of items in the shopping carts
     */

    public int getNumOfProducts() {
        return numOfProducts;
    }

    /**
     *
     * @return
     */
    public List<Order> getCarts() {
        return new ArrayList<>(carts);
    }


    /**
     * Method to retrieve the cart ID
     *
     * @return Integer, return the ID of the cart
     */

    public int getCartID() { return id; }

}