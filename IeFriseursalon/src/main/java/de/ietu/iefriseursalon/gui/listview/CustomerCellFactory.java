package de.ietu.iefriseursalon.gui.listview;

import de.ietu.iefriseursalon.model.Customer;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Diese Klasse implementiert die Java-Callback-Klasse und gibt die CustomerCell-Klasseninstanz zurück.
 */
public class CustomerCellFactory implements Callback<ListView<Customer>, ListCell<Customer>> {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    //Ende der Region Attribute

    //Region Konstruktoren
    //Ende der Region Konstruktoren

    //Region Methoden
    @Override
    public ListCell<Customer> call(ListView<Customer> param) {
        return new CustomerCell();
    }
    //Ende der Region Methoden
}
