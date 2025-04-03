package Store;

/**
 * This class represents a product
 *
 * @author Wilfrid Shaun Nortey Noye (101149803)
 * @author Sebastian Becerril (101152127)
 * @version 1.0
 */

public class Product {
    public String name;   //Name of the product
    public int id;     //ID of product
    public double price; //Price of product

    /**
     * This is a constructor for the product class
     *
     * @param name  String, the name of the product
     * @param id    Integer, the ID of the product
     * @param price double, the price of the product
     */

    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
     * A method to retrieve the ID
     *
     * @return Integer, the ID of a product
     */

    public int getId() {
        return this.id;
    }

    /**
     * A method to retrieve the name
     *
     * @return String, the name of a product
     */

    public String getName() {
        return name;
    }


    /**
     * A method to get the price
     *
     * @return double, the price of a product
     */

    public double getPrice() {
        return price;
    }
}
