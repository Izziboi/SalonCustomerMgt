package de.ietu.iefriseursalon.gui;

import de.ietu.iefriseursalon.Main;
import de.ietu.iefriseursalon.gui.listview.CustomerCellFactory;
import de.ietu.iefriseursalon.gui.switchscene.SceneManager;
import de.ietu.iefriseursalon.logic.CustomerController;
import de.ietu.iefriseursalon.model.Customer;
import de.ietu.iefriseursalon.settings.AppText;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Diese Klasse intialisiert die ListView, ImageView und Suchleiste
 */
public class CustomerOverview {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    @FXML
    private ListView<Customer> customerListView;
    @FXML
    private ImageView searchIcon;
    @FXML
    private TextField txtSearchBar;
    //Ende der Region Attribute

    //Region Konstruktoren
    //Ende der Region Konstruktoren

    //Region Methoden
    @FXML
    private void initialize() {
        customerListView.setCellFactory(new CustomerCellFactory());

        customerListView.setItems(CustomerController.getInstance().getCustomers());

        customerListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == AppText.MOUSE_CLICKS) {
                    openPersonalDataWithCustomer();
                }
            }
        });

        customerListView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                openPersonalDataWithCustomer();
        });

        //ImageView Konfiguration
        String imageUrl = Main.class.getResource(AppText.IMAGE_FILE).toString();
        Image image = new Image(imageUrl);
        searchIcon.setImage(image);
        searchIcon.setVisible(true);

        //Such Funktion
        txtSearchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                customerListView.setItems(CustomerController.getInstance().searchCustomersBy(newValue));
            }
        });
    }

    @FXML
    private void goBackToLogin() {
        SceneManager.getInstance().showScene(AppText.REGISTRATION_PAGE);
    }

    @FXML
    private void goToPersonalDataPage() {
        SceneManager.getInstance().showScene(AppText.PERSONAL_DATA_PAGE);
    }

    //Lädt die ListView mit dem gewählten Kunden
    private void openPersonalDataWithCustomer() {
        Customer choiceCustomer = customerListView.getSelectionModel().getSelectedItem();
        SceneManager.getInstance().showCustomerDetailScene(choiceCustomer);
    }
    //Ende der Region Methoden
}
