package application.CommandSecretary;

import application.Command;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Classe utilizzate per efettuare il log out
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class LogoutSecretary implements Command {

    private Button escibtn;

    /**
     * Costruttore che contiene i parametri per il log out
     *
     * @param escibtn bottone per uscire dalla schermata
     */
    public LogoutSecretary(Button escibtn){
        this.escibtn = escibtn;
    }

    /**
     * Metodo utilizzato per uscire dalla schermata corrente e ritornare alla fase di login
     * Utilizzo try e catch per evitare problematiche con il cambio di scena
     */
    @Override
    public void execute() {
        try {
            Parent root;
            Stage stage = new Stage();
            Scene scene;

            //Cambio della scena
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/Login.fxml")));
            scene = new Scene(root);
            stage.setTitle("LOGIN SEGRETERIA");
            stage.setMinWidth(500);
            stage.setMinHeight(380);
            stage.setScene(scene);
            //Chiudo la schermata corrente
            Stage currentStage = (Stage) escibtn.getScene().getWindow();
            currentStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
