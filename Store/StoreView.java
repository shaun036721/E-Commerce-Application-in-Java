package Store;

/**
 * This class represents a store view
 *
 * @author Wilfrid Shaun Nortey Noye (101149803)
 * @author Sebastian Becerril (101152127)
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StoreView {
    private JFrame frame;
    private StoreManager sm;
    private int cartID;
    private Inventory iv;

    /**
     * A method to retrieve a random colour
     *
     * @return Color, a random colour
     */

    public static Color getColour() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        double luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);

        while (luma < 75) {
            r = (int) (Math.random() * 256);
            g = (int) (Math.random() * 256);
            b = (int) (Math.random() * 256);
            luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);
        }
        return new Color(r, g, b);
    }

    /**
     * Constructor for the store view class
     */

    public StoreView() {

        /**
         * Create a new store manager, and create a new CartID as well as create some products and add them to the
         * list of products.
         */
        sm = new StoreManager();
        cartID = sm.assignNewCartID();
        iv = new Inventory();

        Product banana = new Product("Banana", 1, 12.99);
        Product apple = new Product("Apple", 2, 12.99);
        Product cherry = new Product("Cherry", 3, 12.99);
        Product pineapple = new Product("Pineapple", 4, 10.99);
        Product pomegranate = new Product("Pomegranate", 5, 9.99);
        Product grapes = new Product("Grapes", 6, 12.99);
        Product blueberries = new Product("Blueberries", 7, 20.99);
        Product strawberries = new Product("Strawberries", 8, 21.99);
        Product avocado = new Product("Avocado", 8, 30.99);
        Product mango = new Product("Mango", 9, 20.99);
        Product dragonfruit = new Product("Dragonfruit", 10, 25.99);
        Product kiwi = new Product("Kiwi", 11, 13.99);
        Product orange = new Product("Orange", 12, 5.99);

        sm.addProduct(banana, 15);
        sm.addProduct(apple, 9);
        sm.addProduct(cherry, 10);
        sm.addProduct(pineapple, 10);
        sm.addProduct(pomegranate, 20);
        sm.addProduct(grapes, 18);
        sm.addProduct(blueberries, 25);
        sm.addProduct(strawberries, 1);
        sm.addProduct(avocado, 4);
        sm.addProduct(mango, 3);
        sm.addProduct(dragonfruit, 0);
        sm.addProduct(kiwi, 16);
        sm.addProduct(orange, 17);


        frame = new JFrame();


        JPanel contentPane = new JPanel(new BorderLayout()); //Create a JPanel with a BorderLayout as LayoutManager.
        contentPane.add(new JLabel("Welcome to the fruit store! (ID: " + sm.getCartID() + ")"), BorderLayout.NORTH);

        JPanel productArea = new JPanel(new GridLayout( (int)Math.ceil(sm.getInventory().getCarts().size()/2.0),2));

        ArrayList<Order> products = sm.getInventory().getCarts();
        for(Order o : products) {
            productArea.add(new OrderCell(o, this.sm));
        }

        JScrollPane productAreaScrolling = new JScrollPane(productArea);

        contentPane.add(productAreaScrolling, BorderLayout.CENTER);

        JPanel east = new JPanel(new BorderLayout());
        JPanel controlArea = new JPanel();
        BoxLayout box = new BoxLayout(controlArea, BoxLayout.Y_AXIS);
        controlArea.setLayout(box);

        JButton viewCart = new JButton("View Cart");
        viewCart.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlArea.add(viewCart);
        viewCart.setIcon(new ImageIcon(new ImageIcon("imgstore/checkout.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));

        /**
         * The action listener for the view cart button
         */

        viewCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, sm.cartStringForm(cartID, false), "Your Cart", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JButton checkout = new JButton("Checkout");

        checkout.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlArea.add(checkout);

        /**
         * The action listener for the checkout button
         */

        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, sm.checkout(cartID), "Your Cart", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        JButton quit = new JButton("Quit");
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlArea.add(quit);

        /**
         * The action listener for the quit button
         */

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure want to quit?", "Quit", JOptionPane.WARNING_MESSAGE);
                if (choice == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        east.add(controlArea, BorderLayout.NORTH);
        contentPane.add(east, BorderLayout.EAST);

        JPanel offer = new JPanel(new BorderLayout());

        JLabel bannerPic = new JLabel();
        bannerPic.setAlignmentX(Component.CENTER_ALIGNMENT);
        bannerPic.setIcon(new ImageIcon(new ImageIcon("imgstore/banner.jpg").getImage().getScaledInstance(250, 600, Image.SCALE_DEFAULT)));
        offer.add(bannerPic, BorderLayout.SOUTH);
        JPanel paddingPanel = new JPanel();
        paddingPanel.setPreferredSize(new Dimension(0, 200)); //add a gap panel to east panel's center...
        east.add(paddingPanel, BorderLayout.CENTER);
        east.add(offer,BorderLayout.SOUTH);
        frame.setContentPane(contentPane);
        frame.setTitle("Client Store.StoreView");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method to display the GUI
     */

    public void  displayGUI() {
        frame.setSize(800, 900);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        StoreView sv = new StoreView();
        sv.displayGUI();
    }
}