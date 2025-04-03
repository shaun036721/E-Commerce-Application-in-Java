package Store;

/**
 * This interface represents a product stock container
 *
 * @author Wilfrid Shaun Nortey Noye (101149803)
 * @author Sebastian Becerril (101152127)
 * @version 1.0
 */

public interface ProductStockContainer {

    /**
     * Blueprint of a method to retrieve the quantity of a product
     *
     * @param id Integer, the ID of the product the user wants the quantity of
     * @return   Integer, the quantity of that product
     */

    int getProductQuantity(int id);

    /**
     * Blueprint of a method to add quantity to a product
     *
     * @param p       Product, the product the user wants to add
     * @param amount  Integer, the amount to be added
     */

    public void addProductQuantity(Product p, int amount);

    /**
     * Blueprint of a method to remove a quantity of a product.
     *
     * @param p       Product, the product the user wants to remove
     * @param amount  Integer, the amount to be removed.
     * @return
     */

    public boolean removeProductQuantity(Product p, int amount);

    /**
     * Blueprint of a method to retrieve the amount of products.
     *
     * @return Integer, the number of products
     */

    public int getNumOfProducts();

}
