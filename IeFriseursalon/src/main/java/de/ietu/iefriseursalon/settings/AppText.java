package de.ietu.iefriseursalon.settings;

/**
 * Alle Konstanten werden in dieser Klasse deklariert.
 */
public class AppText {

    //Region Konstanten
    public static final String APP_NAME = "Friseursalon Kundendaten";

    public static final int MOUSE_CLICKS = 2;

    public static final String CUSTOMER_OVERVIEW_PAGE = "customerOverview.fxml";
    public static final String REGISTRATION_PAGE = "registration.fxml";
    public static final String PERSONAL_DATA_PAGE = "personalData.fxml";
    public static final String ADMIN_LOGIN_PAGE = "adminLogin.fxml";

    private static final String DB_ROUTE = "jdbc:mariadb://";
    private static final String DOMAIN = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "dbfriseur";
    public static final String CONNECTION_PATH =
            DB_ROUTE + DOMAIN + ":" + PORT + "/" + DB_NAME;
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static final String SQL_SELECT_ALL_FROM_CUSTOMERS = "SELECT * FROM dbfriseur.Customers;";

    public static final String SQL_DELETE_CUSTOMER =
            "DELETE FROM dbfriseur.Customers WHERE customerId=?;";

    public static final String SQL_INSERT_CUSTOMER_DATA = "INSERT INTO dbfriseur.Customers " +
            "(username, password, name, address, email, phone) VALUES (?,?,?,?,?,?);";

    public static final String SQL_UPDATE_CUSTOMERS =
            "UPDATE dbfriseur.Customers SET username=?, password=?, name=?, " +
                    "address=?, email=?, phone=? WHERE customerId=?;";

    public static final String SQL_ADMIN_LOGIN_QUERY =
            "SELECT * FROM dbfriseur.Admin WHERE BINARY a_username = ? AND BINARY a_password = ?;";

    public static final String SQL_CUSTOMER_LOGIN_QUERY =
            "SELECT * FROM dbfriseur.Customers WHERE BINARY username = ? AND BINARY password = ?;";

    public static final String SQL_USERNAME_OR_PASSWORD =
            "SELECT * FROM dbfriseur.Customers WHERE BINARY username = ? OR BINARY password = ?;";

    public static final String SQL_COMPARE_USERNAME_OR_EMAIL =
            "SELECT * FROM dbfriseur.Customers WHERE BINARY username = ? OR BINARY email = ?;";

    public static final String DB_CONNECTION_NOT_ESTABLISHED =
            "Datenbankverbindung könnte nicht aufgebaut werden!";
    public static final String SUBSTITUTION_SUCCESSFUL = "Ersatz erfolgreich!";

    public static final String CUSTOMER_ID = "customerId";
    public static final String CUSTOMER_USERNAME = "username";
    public static final String CUSTOMER_PASSWORD = "password";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";

    public static final String ERROR_EMPTY_FIELDS = "Leere Felde müssen befüllt werden";
    public static final String LOGIN_DETAILS_ERROR = "Ungültige Zugangsdaten";
    public static final String ERROR_PASSWORD_NOT_THE_SAME = "Passwort nicht gleich!";
    public static final String CONFIRM_CUSTOMER_DELETE = "Möchten Sie wirklich löschen?";
    public static final String DATA_SAVED_SUCCESSFULLY = "Daten erfolgreich gespeichert!";
    public static final String DATA_UPDATED_SUCCESSFULLY = "Daten erfolgreich aktualisiert!";
    public static final String DATA_DELETED_SUCCESSFULLY = "Daten erfolgreich gelöscht!";
    public static final String USERNAME_TOO_SHORT = "Benutzername muss länger als 2 Zeichen sein.";
    public static final String PASSWORD_TOO_SHORT = "Passwort muss länger als 5 Zeichen sein.";
    public static final String WRONG_EMAIL = "Ungültige E-Mail";
    public static final String USERNAME_ALREADY_EXISTS = "Benutzername schon vorkommt";
    public static final String EMAIL_ALREADY_EXISTS = "Email schon vorkommt";
    public static final String CONFIRM_CUSTOMER_SAVE = "Kunde wirklich speichern?";
    public static final String CONFIRM_CUSTOMER_UPDATE = "Kunde wirklich aktualisieren?";
    public static final String IMAGE_FILE = "searchIcon.png";
    public static final String TEMPLATE_CUSTOMER_DISPLAY = "%-18s %-18s %-18s %-18s %4s %16s";
    //Ende der Region Konstanten

}
