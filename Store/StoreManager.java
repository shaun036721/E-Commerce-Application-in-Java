package Store;

/**
 * This class represents a store manager
 *
 * @author Wilfrid Shaun Nortey Noye (101149803)
 * @author Sebastian Becerril (101152127)
 * @version 1.0
 */

import java.util.ArrayList;

public class StoreManager {
    private Inventory inventory;
    private static int nextCartId = 0;
    private ArrayList<ShoppingCart> carts;

    /**
     * Contructor for the Store Manager class
     */

    public StoreManager() {
        this.inventory = new Inventory();
        this.carts = new ArrayList<ShoppingCart>();
    }

    /**
     * This method retrives the stock of a product
     *
     * @param  id Integer, the id of the product to check the stock for
     * @return   Integer, the amount of that product
     */

    public int getStock(int id) {
        return this.inventory.getProductQuantity(id);
    }

    /**
     * Method to add a product
     *
     * @param p     Store.Product, the product to be added
     * @param stock Integer, the amount to be added
     */

    public void addProduct(Product p, int stock) {

        try {
            if (p == null) {
                throw new Exception();
            }
        }
        catch (Exception e) {
            System.out.println("The product is null/does not exist");
        }
        inventory.addProduct(p, stock);
    }

    /**
     * Method to assign a new cart ID
     *
     * @return Integer, the ID of the new cart created
     */

    public int assignNewCartID() {
        ShoppingCart cart = new ShoppingCart(nextCartId);
        carts.add(cart);
        nextCartId++;
        return cart.getCartID();
    }

    /**
     * Method to find a cart
     *
     * @param cID Integer, the cart ID to look for
     * @return the shopping cart with the same ID
     */

    private ShoppingCart findCart(int cID) {
        for(ShoppingCart s : carts) {
            if (s.getCartID() == cID) {
                return s;
            }
        }

        return null;
    }

    /**
     * Method to show a Shopping cart's characteristics in string form
     *
     * @param pick boolean, determines whether (0), (1), (2).... shows up
     * @return     String, the characteristics of a shopping cart
     */

    public String cartStringForm(int cartId, boolean pick) {
        StringBuilder sb = new StringBuilder();
        Inventory i = new Inventory();
        Product p;
        double total = 0;
        int j = 0;

        for(Order o : i.getCarts()) {
            p = this.inventory.retrieveProduct(o.product);
            sb.append(p.getName() + " $" + p.getPrice());
            total+= p.getPrice();

            if (pick) {
                sb.append("\t (" + j + ")");
            }
            j++;
            sb.append("\n");
        }

        sb.append("\nTotal: $" + Math.round(total));
        return sb.toString();
    }

    /**
     * Prints the bill for the items in the cart (checking out!)
     *
     * @param cID Integer, the ID of the cart that will be checked out
     * @return    String, a recipt of items bought
     */

    public String checkout(int cID) {
        StringBuilder sb = new StringBuilder();
        double discount = 0;
        sb.append("==== Your Bill ====\n");
        double total = 0;
        ShoppingCart cart = findCart(cID);;
        for(int i=0; i < cart.getNumOfProducts(); i++) {
            sb.append(cart.getCarts().get(i).product.getName() + " 1x @ $" + cart.getCarts().get(i).product.getPrice() +"\n");
            total += cart.getCarts().get(i).product.getPrice();
        }

        if (total > 60) {
            discount = Math.round(total * 100.0) / 100.0 * 0.1;
            sb.append("Total: $" + Math.round(total * 100.0) / 100.0 + "\n10% off ($" + (Math.round(discount * 100.0) / 100.0) + " ): $" + (Math.round(total * 100.0) / 100.0-Math.round(discount * 100.0) / 100.0)+"\n");
            total -= discount;
        } else {
            sb.append("Total: $" + Math.round(total * 100) / 100 + "\n");
        }

        sb.append("Thanks for shopping with us!\n");
        return sb.toString();
    }

    /**
     * A method to retrive the inventory
     *
     * @return
     */

    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Method
     * @return
     */

    public int getCartID() {
        return nextCartId;
    }

    /**
     * Method to add a product to cart
     *
     * @param cID        Integer, the cart ID
     * @param p          Product, the product to be added
     * @param amount     Integer, the amount of the product to be added
     *
     * @return returns true of successful and false otherwise
     */

    public boolean addProductToCart(int cID, Product p, int amount) {
        if (inventory.removeProductQuantity(p, amount)) {
            for (ShoppingCart cart : carts) {
                if (cart.getCartID() == cID) {
                    cart.addProductQuantity(p, amount);
                    return true;
                }
            }
            inventory.addProductQuantity(p, amount);
        }
        return false;
    }

    /**
     * Method to remove products from cart
     *
     * @param cID        Integer, the cart ID
     * @param p          Product, the product to be removed
     * @param amount     Integer, the amount of the product to be removed
     *
     * @return returns true of successful and false otherwise
     */

    public boolean removeProductFromCart(int cID, Product p, int amount) {
            for (ShoppingCart cart : carts) {
                if (cart.getCartID() == cID) {
                    if (cart.removeProductQuantity(p, amount)) {
                        this.inventory.addProductQuantity(p, amount);
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }

}
