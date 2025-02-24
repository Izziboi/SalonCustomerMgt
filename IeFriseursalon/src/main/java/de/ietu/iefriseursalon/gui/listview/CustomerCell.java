package de.ietu.iefriseursalon.gui.listview;

import de.ietu.iefriseursalon.model.Customer;
import de.ietu.iefriseursalon.settings.AppText;
import javafx.scene.control.ListCell;

/**
 * Diese Klasse implementiert die updateItem-Methode der Java ListCell-Klasse
 */
public class CustomerCell extends ListCell<Customer> {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    //Ende der Region Attribute

    //Region Konstruktoren
    //Ende der Region Konstruktoren

    //Region Methoden
    @Override
    protected void updateItem(Customer customer, boolean empty) {
        super.updateItem(customer, empty);

        if (customer == null && empty) {
            setText(null);
            setGraphic(null);
            return;
        }
        setText(String.format(AppText.TEMPLATE_CUSTOMER_DISPLAY,
                customer.getUsername(), customer.getPassword(), customer.getName(), customer.getAddress(),
                customer.getEmail(), customer.getPhone()));
    }
    //Ende der Region Methoden
}
