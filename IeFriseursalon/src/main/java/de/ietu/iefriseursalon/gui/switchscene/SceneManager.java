package de.ietu.iefriseursalon.gui.switchscene;

import de.ietu.iefriseursalon.Main;
import de.ietu.iefriseursalon.gui.PersonalData;
import de.ietu.iefriseursalon.model.Customer;
import de.ietu.iefriseursalon.settings.AppText;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Diese Klasse verwaltet die Szenen und bestimmt, welche Szene angezeigt wird,
 * wenn eine Schaltfläche angeklickt wird.
 */

public class SceneManager {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    private static SceneManager instance;

    private Stage mainStage;
    //Ende der Region Attribute

    //Region Konstruktoren
    private SceneManager() {}
    //Ende der Region Konstruktoren

    //Region Methoden
    public static synchronized SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }


    public void setupMainStage(Stage stage) {
        mainStage = stage;
        mainStage.setTitle(AppText.APP_NAME);
        showScene(AppText.REGISTRATION_PAGE);
    }


    public void showScene(String file) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(file));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showCustomerDetailScene(Customer choiceCustomer) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(AppText.PERSONAL_DATA_PAGE));
            Scene scene = new Scene(fxmlLoader.load());

            if (fxmlLoader.getController() instanceof PersonalData personalData) {
                personalData.chosenCustomerDetails(choiceCustomer);
            }

            mainStage.setScene(scene);
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Ende der Region Methoden
}