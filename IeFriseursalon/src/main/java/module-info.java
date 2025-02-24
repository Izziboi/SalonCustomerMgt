module de.ietu.iefriseursalon {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires com.sun.jna.platform;


    opens de.ietu.iefriseursalon to javafx.fxml;
    exports de.ietu.iefriseursalon;
    exports de.ietu.iefriseursalon.gui;
    opens de.ietu.iefriseursalon.gui to javafx.fxml;
    exports de.ietu.iefriseursalon.gui.alert;
    opens de.ietu.iefriseursalon.gui.alert to javafx.fxml;
    exports de.ietu.iefriseursalon.gui.switchscene;
    opens de.ietu.iefriseursalon.gui.switchscene to javafx.fxml;
}