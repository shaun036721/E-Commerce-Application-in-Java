package Store;

/**
 * This class represents a order cell
 *
 * @author Wilfrid Shaun Nortey Noye (101149803)
 * @author Sebastian Becerril (101152127)
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OrderCell extends JPanel {
    private StoreManager sm;
    private JLabel label;
    private int cartID;


    /**
     * This method is a constructor for the product cell class.
     *
     * @param o       Product, the product in the cell
     */

    public OrderCell (Order o, StoreManager sm){

        super(new BorderLayout());
        Color c = StoreView.getColour();
        this.setBackground(c);
        this.sm = sm;

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), o.product.getName()));
        label = new JLabel("($" + o.product.getPrice() + ") - Stock: " + sm.getStock(o.product.id));
        add(label, BorderLayout.NORTH);

        ImageIcon icon = new ImageIcon(new ImageIcon("imgstore/" + o.product.getName() + ".jpg").getImage().getScaledInstance(175, 175, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(icon);

        add(image, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(); //default layout is flow mgr.
        JButton addButton, removeButton;
        addButton = new JButton("+"); removeButton = new JButton("-");
        bottomPanel.add(addButton); bottomPanel.add(removeButton);
        bottomPanel.setBackground(c);

        /**
         * Action listener for the add button, within a product cell
         */

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (sm.addProductToCart(cartID, o.product, 1)) {
                    label.setText("($" + o.product.getPrice() + ")" + " - Stock: " + sm.getStock(o.product.id));
                }
            }
        });

        /**
         * Action listener for the remove button, within a product cell
         */

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (sm.removeProductFromCart(cartID, o.product, 1)) {
                    label.setText("($" + o.product.getPrice() + ")" + " - Stock: " + sm.getStock(o.product.id));
                }
            }
        });

        add(bottomPanel, BorderLayout.SOUTH);
    }
}