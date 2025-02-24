package de.ietu.iefriseursalon.logic.dao;

import de.ietu.iefriseursalon.gui.PersonalData;
import de.ietu.iefriseursalon.logic.daointerface.CustomerDataDao;
import de.ietu.iefriseursalon.model.Customer;
import de.ietu.iefriseursalon.settings.AppText;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse implementiert die Methoden der 'CustomerDataDao' Schnittstelle.
 */
public class CustomerDao implements CustomerDataDao<Customer> {
    //Region Methoden
    @Override
    public void createCustomerData(Connection connection, Customer customer) {

        try (
                PreparedStatement statement =
                        connection.prepareStatement(AppText.SQL_INSERT_CUSTOMER_DATA,
                                PreparedStatement.RETURN_GENERATED_KEYS)
                ){

            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getName());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getEmail());
            statement.setString(6, customer.getPhone());

            //statement.executeUpdate();
            // Execute the insert
            int affectedRows = statement.executeUpdate();

            // Retrieve and set the generated customerId
            if (affectedRows > 0) {

                ResultSet generatedKeys = statement.getGeneratedKeys();

                //generatedKeys.getString(customer.getUsername());
                //generatedKeys.getString(customer.getEmail());

                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); //Auch "insert_id"
                    customer.setCustomerId(generatedId);
                    //System.out.println("Inside createCustomerData() method in the CustomerDao class; Generated customer ID: " + generatedId);
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<Customer> readCustomerData(Connection connection) {
        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(AppText.SQL_SELECT_ALL_FROM_CUSTOMERS)){

            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {

                Customer customer = new Customer(
                        resultSet.getInt(AppText.CUSTOMER_ID),
                        resultSet.getString(AppText.CUSTOMER_USERNAME),
                        resultSet.getString(AppText.CUSTOMER_PASSWORD),
                        resultSet.getString(AppText.NAME),
                        resultSet.getString(AppText.ADDRESS),
                        resultSet.getString(AppText.EMAIL),
                        resultSet.getString(AppText.PHONE)
                );

                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }


    @Override
    public  void updateCustomerData(Connection connection, Customer customer) {
        try (
                PreparedStatement statement =
                     connection.prepareStatement(AppText.SQL_UPDATE_CUSTOMERS)
        ){
            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getName());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getEmail());
            statement.setString(6, customer.getPhone());
            statement.setInt(7, customer.getCustomerId());

            statement.executeUpdate();
            //System.out.println("Inside updateCustomerData() in the CustomerDao class; Updating customer with ID: " + customer.getCustomerId());
            //System.out.println("Inside updateCustomerData() in the CustomerDao class; Executing update: " + statement); // Log SQL statement
            //int rowsUpdated = statement.executeUpdate();
            //System.out.println("Inside updateCustomerData() in the CustomerDao class; Rows updated: " + rowsUpdated);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteCustomerData(Connection connection, Customer customer) {
        try (
                PreparedStatement statement = connection.prepareStatement(AppText.SQL_DELETE_CUSTOMER)
                ){

            statement.setInt(1, customer.getCustomerId());

            statement.executeUpdate();
            //System.out.println("Inside deleteCustomerData() in the CustomerDao class; Executing delete: " + statement); // Log SQL statement
            //int rowsDeleted = statement.executeUpdate();
            //System.out.println("Inside deleteCustomerData() in the CustomerDao class; Rows deleted: " + rowsDeleted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Ende der Region Methoden
}
