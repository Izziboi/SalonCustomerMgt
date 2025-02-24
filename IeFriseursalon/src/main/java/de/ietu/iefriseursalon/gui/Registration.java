package de.ietu.iefriseursalon.gui;

import de.ietu.iefriseursalon.gui.alert.AlertManager;
import de.ietu.iefriseursalon.gui.switchscene.SceneManager;
import de.ietu.iefriseursalon.logic.CustomerController;
import de.ietu.iefriseursalon.logic.db.DbControllerCustomer;
import de.ietu.iefriseursalon.model.Customer;
import de.ietu.iefriseursalon.settings.AppText;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Diese Klasse verwaltet die Anmeldeaktivitäten der Kunden. Sie bindet sich an die Seite 'Kundenanmeldung'
 * (Datei 'registration.fxml').
 */

public class Registration {
    //Region Attribute
    @FXML
    private TextField txtRegUsername;
    @FXML
    private PasswordField txtRegPassword;
    //Ende der Region Attribute

    //Region Konstruktoren
    public Registration() {}
    //Ende der Region Konstruktoren

    //Region Methoden
    @FXML
    private void login() {

        String username = txtRegUsername.getText();
        String password = txtRegPassword.getText();

        if (username.isBlank() || password.isBlank()) {
            AlertManager.getInstance().errorMessage(AppText.ERROR_EMPTY_FIELDS);
        }
        else {

            try {

                PreparedStatement statement =
                        DbControllerCustomer.setupConnection().prepareStatement(AppText.SQL_CUSTOMER_LOGIN_QUERY);
                statement.setString(1, username);
                statement.setString(2, password);

                // Execute query
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // If a record is found, the user credentials are correct
                    Customer loggedInCustomer = new Customer(
                    resultSet.getInt(AppText.CUSTOMER_ID),
                    resultSet.getString(AppText.CUSTOMER_USERNAME),
                    resultSet.getString(AppText.CUSTOMER_PASSWORD),
                    resultSet.getString(AppText.NAME),
                    resultSet.getString(AppText.ADDRESS),
                    resultSet.getString(AppText.EMAIL),
                    resultSet.getString(AppText.PHONE)
                    );

                    SceneManager.getInstance().showCustomerDetailScene(loggedInCustomer);

                } else {
                    // Invalid credentials
                    AlertManager.getInstance().errorMessage(AppText.LOGIN_DETAILS_ERROR);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void goToCreateAccountScene() {
        SceneManager.getInstance().showScene(AppText.PERSONAL_DATA_PAGE);
    }


    @FXML
    private void goToAdminScene() {
        SceneManager.getInstance().showScene(AppText.ADMIN_LOGIN_PAGE);
    }
    //Ende der Region Methoden
}