package application.CommandTeacher;

import application.Command;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Classe utilizzata per il log out
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class LogoutTeacher implements Command {
    private Button exitEsamebtn;

    /**
     * Costruttore che contiene i parametri per il log out
     *
     * @param exitEsamebtn bottone per uscire dalla schermata corrente
     */
    public LogoutTeacher(Button exitEsamebtn) {
        this.exitEsamebtn = exitEsamebtn;
    }

    /**
     * Metodo utilizzato per il log out
     */
    @Override
    public void execute() {
        try {
            Parent root;
            Stage stage = new Stage();
            Scene scene;
            //Carico la scena successiva
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/Login.fxml")));
            scene = new Scene(root);
            stage.setTitle("LOGIN SEGRETERIA");
            stage.setMinWidth(500);
            stage.setMinHeight(380);
            stage.setScene(scene);
            //Chiudo la schermata corrente
            Stage currentStage = (Stage) exitEsamebtn.getScene().getWindow();
            currentStage.close();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
