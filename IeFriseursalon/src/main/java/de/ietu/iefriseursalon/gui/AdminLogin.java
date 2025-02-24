package de.ietu.iefriseursalon.gui;

import de.ietu.iefriseursalon.gui.alert.AlertManager;
import de.ietu.iefriseursalon.gui.switchscene.SceneManager;
import de.ietu.iefriseursalon.logic.db.DbControllerCustomer;
import de.ietu.iefriseursalon.settings.AppText;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Dies ist die Login-Klasse für den Admin
 */
public class AdminLogin {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    @FXML
    private TextField txtAdminUsername;
    @FXML
    private PasswordField txtAdminPassword;
    //Ende der Region Attribute

    //Region Konstruktoren
    //Ende der Region Konstruktoren

    //Region Methoden
    @FXML
    private void adminLogin() {

        String username = txtAdminUsername.getText();
        String password = txtAdminPassword.getText();

        if (username.isBlank() || password.isBlank()) {
            AlertManager.getInstance().errorMessage(AppText.ERROR_EMPTY_FIELDS);
        }
        else {

            try {

                PreparedStatement statement =
                        DbControllerCustomer.setupConnection().prepareStatement(AppText.SQL_ADMIN_LOGIN_QUERY);
                    statement.setString(1, username);
                    statement.setString(2, password);

                // Execute query
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    SceneManager.getInstance().showScene(AppText.CUSTOMER_OVERVIEW_PAGE);
                } else {
                    AlertManager.getInstance().errorMessage(AppText.LOGIN_DETAILS_ERROR);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    //Ende der Region Methoden
}
