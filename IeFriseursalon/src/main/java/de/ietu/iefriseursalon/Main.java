package de.ietu.iefriseursalon;

import de.ietu.iefriseursalon.gui.switchscene.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Dies ist die Hauptklasse, von der aus die App ausgeführt wird.
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.getInstance().setupMainStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}