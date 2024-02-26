package application.Models.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * Classe utilizzata per mostrare messaggi di errore o successo
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class AlertUtil {

    /**
     * Metodo utilizzato per il messaggio di errore
     *
     * @param message messaggio
     */
    public static void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /**
     * Metodo utilizzato per il messaggio di successo
     *
     * @param message messaggio
     */
    public static void showSuccessAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
