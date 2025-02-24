package de.ietu.iefriseursalon.logic;

import de.ietu.iefriseursalon.logic.db.DbControllerCustomer;
import de.ietu.iefriseursalon.model.Customer;
import de.ietu.iefriseursalon.settings.AppText;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Diese Klasse implementiert die Anzeige von Kundendaten in der ListView. Sie implementiert auch das Hinzufügen,
 * Aktualisieren und Löschen von Kundendaten durch den Aufruf der entsprechenden Methoden in der Klasse
 * „DbControllerCustomer“.
 * Sie ist ein Singleton.
 */
public class CustomerController {
    //Region Attribute
    private static CustomerController instance;

    private static ObservableList<Customer> customers;
    //Ende der Region Attribute
    
    //Region Konstruktoren
    private CustomerController() {

        customers = FXCollections.observableList(
                //Kundenattribute werden in 'ObservableList' eingetragen
                DbControllerCustomer.getInstance().readCustomer(), customer -> new Observable[] {
           customer.usernameProperty(), customer.passwordProperty(), customer.nameProperty(),
                        customer.addressProperty(), customer.emailProperty(), customer.phoneProperty()
        });

        customers.addListener((ListChangeListener<Customer>) change -> {
            //System.out.println(change);
            while (change.next()) {

                if (change.wasReplaced()) {

                    System.out.println(AppText.SUBSTITUTION_SUCCESSFUL);
                }
                else if (change.wasAdded()) {
                    //Daten Hinzufügen
                    Customer customerToAdd = change.getAddedSubList().getFirst();
                    DbControllerCustomer.getInstance().addCustomer(customerToAdd);
                    //System.out.println(customerToAdd);
                }
                else if (change.wasUpdated()) {
                    //Daten Aktualisieren
                    Customer customerToUpdate = change.getList().get(change.getFrom());
                    DbControllerCustomer.getInstance().updateCustomer(customerToUpdate);
                    //System.out.println(customerToUpdate);
                }
                else if (change.wasRemoved()) {
                    //Daten Löschen
                    Customer customerToRemove = change.getRemoved().getFirst();
                    DbControllerCustomer.getInstance().deleteCustomer(customerToRemove);
                    //System.out.println(customerToRemove);
                }
            }
        });
    }
    //Ende der Region Konstruktoren
    
    //Region Methoden
    public static synchronized CustomerController getInstance() {
        if (instance == null) {
            instance = new CustomerController();
        }
        return instance;
    }


    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    //Bei dieser Methode wird die Groß- und Kleinschreibung nicht beachtet
    public ObservableList<Customer> searchCustomersBy(String anyName) {
        return customers.filtered(customer -> customer.getUsername().toLowerCase().contains(anyName.toLowerCase())
        || customer.getPassword().toLowerCase().contains(anyName.toLowerCase())
        || customer.getName().toLowerCase().contains(anyName.toLowerCase())
        || customer.getAddress().toLowerCase().contains(anyName.toLowerCase())
        || customer.getEmail().toLowerCase().contains(anyName.toLowerCase())
        || customer.getPhone().toLowerCase().contains(anyName.toLowerCase()));
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }
    //Ende der Region Methoden
}