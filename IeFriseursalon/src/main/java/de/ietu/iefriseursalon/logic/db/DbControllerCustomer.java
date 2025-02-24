package de.ietu.iefriseursalon.logic.db;

import de.ietu.iefriseursalon.gui.alert.AlertManager;
import de.ietu.iefriseursalon.logic.dao.CustomerDao;
import de.ietu.iefriseursalon.model.Customer;
import de.ietu.iefriseursalon.settings.AppText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Dies ist ein Singleton. Es implementiert das Hinzufügen, Lesen, Aktualisieren und Löschen von Kundendaten
 * unter Verwendung der entsprechenden Methoden in der Klasse „CustomerDao“.
 */
public class DbControllerCustomer {
    //Region Attribute
    private static DbControllerCustomer instance;

    private CustomerDao customerDao;
    //Ende der Region Attribute
    
    //Region Konstruktoren
    private DbControllerCustomer() {
        customerDao = new CustomerDao();
    }
    //Ende der Region Konstruktoren
    
    //Region Methoden
    public static synchronized DbControllerCustomer getInstance() {
        if (instance == null) {
            instance = new DbControllerCustomer();
        }
        return instance;
    }

    //Stellt die erste Verbindung zur Datenbank her.
    public static synchronized Connection setupConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(AppText.CONNECTION_PATH, AppText.USERNAME, AppText.PASSWORD);
            //System.out.println("Inside setupConnection() in the DbControllerCustomer");
        } catch (SQLException e) {
            e.printStackTrace();

            AlertManager.getInstance().errorMessage(AppText.DB_CONNECTION_NOT_ESTABLISHED);
        }
        return connection;
    }


    public void addCustomer(Customer customer) {
        customerDao.createCustomerData(setupConnection(), customer);
    }

    public List<Customer> readCustomer() {
        return customerDao.readCustomerData(setupConnection());
    }

    public void updateCustomer(Customer customer) {
        customerDao.updateCustomerData(setupConnection(), customer);
    }

    public void deleteCustomer(Customer customer) {
        customerDao.deleteCustomerData(setupConnection(), customer);
    }
    //Ende der Region Methoden
}