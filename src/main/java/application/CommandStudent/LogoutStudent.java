package application.CommandStudent;

import application.Command;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe utilizzata per effettuare il log out
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class LogoutStudent implements Command {
    private Button exitbtn;

    /**
     * Costruttore che contiene i parametri per il log out
     *
     * @param exitbtn bottone per uscire dalla schermata
     */
    public LogoutStudent(Button exitbtn) {
        this.exitbtn = exitbtn;
    }

    /**
     * Metodo utilizzato per effettuare il log out e tornare al login
     */
    @Override
    public void execute() {
        try {
            Parent root;
            Stage stage = new Stage();
            Scene scene;
            //Cambio scena
            root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
            scene = new Scene(root);
            stage.setTitle("LOGIN SEGRETERIA");
            stage.setMinWidth(500);
            stage.setMinHeight(380);
            stage.setScene(scene);
            //Chiudo la schermata corrente
            Stage currentStage = (Stage) exitbtn.getScene().getWindow();
            currentStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
