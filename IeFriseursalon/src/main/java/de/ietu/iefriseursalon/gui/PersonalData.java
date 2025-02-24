package de.ietu.iefriseursalon.gui;

import com.sun.jna.platform.mac.SystemB;
import de.ietu.iefriseursalon.gui.alert.AlertManager;
import de.ietu.iefriseursalon.gui.switchscene.SceneManager;
import de.ietu.iefriseursalon.logic.CustomerController;
import de.ietu.iefriseursalon.logic.db.DbControllerCustomer;
import de.ietu.iefriseursalon.model.Customer;
import de.ietu.iefriseursalon.settings.AppText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 * Diese Klasse verwaltet die Seite „Persönliche Daten eingeben“ (Datei „personalData.fxml“).
 */

public class PersonalData {
    //Region Attribute
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtCustomerAddress;
    @FXML
    private TextField txtCustomerEmail;
    @FXML
    private TextField txtCustomerPhone;
    @FXML
    private Button btnDelete;

    private Customer chosenCustomer;
    //Ende der Region Attribute

    //Region Konstruktoren
    public PersonalData() {}
    //Ende der Region Konstruktoren

    //Region Methoden

    //Holt ausgewählte Kundendaten und setzt sie auf die entsprechenden TextFields
    public void chosenCustomerDetails(Customer customer) {
        chosenCustomer = customer;

        if (chosenCustomer != null) {
            txtUsername.setText(chosenCustomer.getUsername());
            txtPassword.setText(chosenCustomer.getPassword());
            txtCustomerName.setText(chosenCustomer.getName());
            txtCustomerAddress.setText(chosenCustomer.getAddress());
            txtCustomerEmail.setText(chosenCustomer.getEmail());
            txtCustomerPhone.setText(chosenCustomer.getPhone());
            btnDelete.setDisable(false);
        }
    }


    @FXML
    private void savePersonalData() {
        try (
                Connection connection = DbControllerCustomer.setupConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(AppText.SQL_COMPARE_USERNAME_OR_EMAIL)

        ) {
            preparedStatement.setString(1, txtUsername.getText());
            preparedStatement.setString(2, txtCustomerEmail.getText());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String existingUsername = resultSet.getString(AppText.CUSTOMER_USERNAME);
                String existingEmail = resultSet.getString(AppText.CUSTOMER_PASSWORD);

                if (existingUsername.equals(txtUsername.getText())) {
                    AlertManager.getInstance().errorMessage(AppText.USERNAME_ALREADY_EXISTS);

                } else if (existingEmail.equals(txtCustomerEmail.getText())) {
                    AlertManager.getInstance().errorMessage(AppText.EMAIL_ALREADY_EXISTS);

                }
            }
            else if (chosenCustomer == null) {
                setupCustomer(txtUsername.getText(), txtPassword.getText(),
                        txtConfirmPassword.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(),
                        txtCustomerEmail.getText(), txtCustomerPhone.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupCustomer(String username, String password, String confirmPassword,
                               String name, String address,
                               String email, String phone) {
        String emailFormat = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (username.isBlank() || password.isBlank() ||
                confirmPassword.isBlank() || name.isBlank() ||
                address.isBlank() || email.isBlank() || phone.isBlank()) {

            AlertManager.getInstance().errorMessage(AppText.ERROR_EMPTY_FIELDS);
        }

        else if (username.length() < 3) {
            AlertManager.getInstance().errorMessage(AppText.USERNAME_TOO_SHORT);
        }

        else if (password.length() < 6) {
            AlertManager.getInstance().errorMessage(AppText.PASSWORD_TOO_SHORT);
        }

        else if (!password.equals(confirmPassword)) {
            AlertManager.getInstance().errorMessage(AppText.ERROR_PASSWORD_NOT_THE_SAME);
        }

        else if (!email.matches(emailFormat)) {
            AlertManager.getInstance().errorMessage(AppText.WRONG_EMAIL);
        }

        else {
            Customer customer = new Customer(username, password, name, address, email, phone);
            CustomerController.getInstance().addCustomer(customer);

            clear();

            AlertManager.getInstance().successMessage(AppText.DATA_SAVED_SUCCESSFULLY);
        }
    }


    private void updateChosenCustomer(String username, String password, String name, String address,
                                      String email, String phone) {

        if (!username.equals(chosenCustomer.getUsername())) chosenCustomer.setUsername(username);
        if (!password.equals(chosenCustomer.getPassword())) chosenCustomer.setPassword(password);
        if (!name.equals(chosenCustomer.getName())) chosenCustomer.setName(name);
        if (!address.equals(chosenCustomer.getAddress())) chosenCustomer.setAddress(address);
        if (!email.equals(chosenCustomer.getEmail())) chosenCustomer.setEmail(email);
        if (!phone.equals(chosenCustomer.getPhone())) chosenCustomer.setPhone(phone);

        // Aktualisiere Kunde in der Datenbank
        DbControllerCustomer.getInstance().updateCustomer(chosenCustomer);
        clear();
        AlertManager.getInstance().successMessage(AppText.DATA_UPDATED_SUCCESSFULLY);
    }


    @FXML
    private void clearTextFields() {
        clear();
    }


    @FXML
    private void updateData() {

        updateChosenCustomer(txtUsername.getText(), txtPassword.getText(), txtCustomerName.getText(),
                txtCustomerAddress.getText(), txtCustomerEmail.getText(), txtCustomerPhone.getText());
    }


    @FXML
    private void deleteData() {
        Optional<ButtonType> buttonType = AlertManager.getInstance().confirmationMessage(
                AppText.CONFIRM_CUSTOMER_DELETE, chosenCustomer.toString());

        if (buttonType.isEmpty() || buttonType.get() != ButtonType.OK) return;
        // Löschen Sie Kunde von der Datenbank
        CustomerController.getInstance().removeCustomer(chosenCustomer);
        DbControllerCustomer.getInstance().deleteCustomer(chosenCustomer);
        clear();
        AlertManager.getInstance().successMessage(AppText.DATA_DELETED_SUCCESSFULLY);

    }


    @FXML
    private void backToLogin() {
        SceneManager.getInstance().showScene(AppText.REGISTRATION_PAGE);
    }


    private void clear() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerEmail.setText("");
        txtCustomerPhone.setText("");
    }
    //Ende der Region Methoden
}
