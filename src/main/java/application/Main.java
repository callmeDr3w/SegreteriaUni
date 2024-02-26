package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main, esegue il programma mostrando la schermata di login per proseguire con l'accesso al sistema
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Metodo utilizzato per startare la schermata di login
     *
     * @param stage parametro che richiama la creazione dello stage
     * @throws IOException eccezione in caso di errore
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 380);
        stage.setTitle("LOGIN SEGRETERIA");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodo del main per startare il programma
     *
     * @param args parametro di input per lanciare il programma
     */
    public static void main(String[] args) {
        launch();
    }
}