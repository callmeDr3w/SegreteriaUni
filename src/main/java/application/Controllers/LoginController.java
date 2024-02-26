package application.Controllers;

import application.Strategy.LoginStrategy;
import application.Strategy.SecretaryLoginStrategy;
import application.Strategy.StudentsLoginStrategy;
import application.Strategy.TeachersLoginStrategy;
import application.Database.Secretary.DatabaseSecretaryLogin;
import application.Database.Students.DatabaseStudentsLogin;
import application.Database.Teachers.DatabaseTeachersLogin;
import application.Models.Utils.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Classe utilizzata per controllare la schermata di login
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class LoginController implements Initializable {

    @FXML
    private Pane loginForm;
    @FXML
    private TextField loginUser;

    @FXML
    private PasswordField loginPassw;

    @FXML
    private Button loginbtn;

    @FXML
    private Button exitbtn;

    DatabaseSecretaryLogin databaseSecretary = new DatabaseSecretaryLogin();
    DatabaseStudentsLogin databaseStudents = new DatabaseStudentsLogin();
    DatabaseTeachersLogin databaseTeachers = new DatabaseTeachersLogin();

    /**
     * Metodo utilizzato per il bottone di login
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void loginBTN(ActionEvent event) {
        if (loginUser.getText().isEmpty() || loginPassw.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Compila tutti i campi");
        } else {
            String input = loginUser.getText();
            String password = loginPassw.getText();

            LoginStrategy loginStrategy;

            if (databaseSecretary.secretaryLogin(Integer.valueOf(input), password)) {
                loginStrategy = new SecretaryLoginStrategy();
            } else if (databaseStudents.studentsLogin(Long.parseLong(input), password)) {
                loginStrategy = new StudentsLoginStrategy();
            } else if (databaseTeachers.teachersLogin(Integer.parseInt(input), password)) {
                loginStrategy = new TeachersLoginStrategy();
            } else {
                System.out.println("login errato");
                AlertUtil.showErrorAlert("Login errato");
                return;
            }

           if (loginStrategy.login(this, input, password)) {
                System.out.println("Login riuscito");
            } else {
                System.out.println("Login non riuscito");
            }

        }
    }

    /**
     * Metodo utilizzato per il bottone exit
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void exitBTN(ActionEvent event){
        System.exit(0);
    }


    /**
     * Metodo utilizzato per cambiare schermata in base a quale utente vi sta accedendo
     *
     * @param title titolo del file fxml
     * @param resourcePath percorso della risorsa del file fxml
     */
    public void handleSuccessfulLogin(String title, String resourcePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(resourcePath));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle(title);
            stage.setMinWidth(1920);
            stage.setMinHeight(1080);
            stage.setScene(scene);

            Stage currentStage = (Stage) loginbtn.getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}