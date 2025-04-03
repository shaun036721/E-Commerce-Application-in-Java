package Store;

/**
 * This class represents an inventory
 *
 * @author Wilfrid Shaun Nortey Noye (101149803)
 * @author Sebastian Becerril (101152127)
 * @version 1.0
 */

import java.util.ArrayList;

public class Inventory implements ProductStockContainer {

    private ArrayList<Order> carts;
    private int numOfProducts = 0;

    /**
     * This is a constructor for the inventory class
     */

    public Inventory() {
        carts = new ArrayList<Order>();
    }

    /**
     * This method will retrieve a product
     *
     * @param    p, the ID of the product
     * @return   Product, the product with the specified ID
     */

    public Product retrieveProduct(Product p){
        return findProduct(p.getId());
    }

    /**
     * Method to retrieve the stock count given an ID
     *
     * @param id Integer, the ID of the product
     * @return   Integer, the ID of the
     */

    public int getProductQuantity(int id) {
        for (int i=0; i < carts.size(); i++) {
            if (carts.get(i).product.id == id) {
                return carts.get(i).stock;
            }
        }
        return -1;
    }

    /**
     * This method will add stock
     *
     * @param p     Integer, The ID of the product
     * @param amount Integer, The amount of stock to add
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
     * Method to remove a certain amount of a product
     *
     * @param p       Product, the product to be removed.
     * @param amount  Integer, the quantity to be removed.
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
     * Method to add a product
     *
     * @param p     Product, the product you would like to add
     * @param stock Integer, the amount of stock you would like to add
     */

    public void addProduct(Product p, int stock) {
        //make sure this product doesn't exist
        if (findProduct(p.getId()) != null) {
            return;
        }

        //Check if stock count is valid, if not... just set it 0 and add it anyways.
        if (stock < 0) {
            stock = 0;
        }

        carts.add(new Order(p, stock));
        numOfProducts++;
    }

    /**
     * A method to find a product
     *
     * @param id String, the id of a product
     * @return   Store.Product,
     */


    private Product findProduct(int id) {
        for(int i=0; i < carts.size(); i++) {
            Order p = carts.get(i);
            if (p.product.id == id) {
                return p.product;
            }
        }

        return null;
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
     * Method to retrive the carts array
     *
     * @return ArrayList<Order>, an array of carts
     */

    public ArrayList<Order> getCarts() {
        return carts;
    }

}