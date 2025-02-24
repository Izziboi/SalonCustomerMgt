package de.ietu.iefriseursalon.gui.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Dieses Singleton verwaltet die Fehler-, Bestätigungs- und Erfolgsmeldungen, die beim Auftreten von
 * Ereignissen angezeigt werden.
 */
public class AlertManager {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    private static AlertManager instance;
    //Ende der Region Attribute

    //Region Konstruktoren
    private AlertManager() {}
    //Ende der Region Konstruktoren

    //Region Methoden
    public static synchronized AlertManager getInstance() {
        if (instance == null) instance = new AlertManager();
        return instance;
    }

    private Alert setupAlert(Alert.AlertType type, String headerAlert, String bodyAlert) {
        Alert alert = new Alert(type);
        alert.setHeaderText(headerAlert);
        alert.setContentText(bodyAlert);
        return  alert;
    }


    public Optional<ButtonType> errorMessage(String headerAlert) {
        return setupAlert(Alert.AlertType.ERROR, headerAlert, null).showAndWait();
    }


    public Optional<ButtonType> confirmationMessage(String headerAlert, String bodyAlert) {
        return setupAlert(Alert.AlertType.CONFIRMATION, headerAlert, bodyAlert).showAndWait();
    }

    public Optional<ButtonType> successMessage(String headerAlert) {
        return setupAlert(Alert.AlertType.INFORMATION, headerAlert, null).showAndWait();
    }
    //Ende der Region Methoden
}
