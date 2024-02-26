package application.Strategy;

import application.Controllers.LoginController;
import application.Database.Students.DatabaseStudentsLogin;
import application.Models.LongSession;
import application.Models.Utils.AlertUtil;

/**
 * Classe utilizzata per la fase di login dello studente
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class StudentsLoginStrategy implements LoginStrategy{
    private final DatabaseStudentsLogin database = new DatabaseStudentsLogin();

    /**
     * Metodo utilizzato per effettuare il login
     *
     * @param loginController controller della schermata di login
     * @param username username per l'accesso
     * @param password password per l'accesso
     * @return ritorna la schermata dello studente o false se vi é errore
     */
    @Override
    public boolean login(LoginController loginController, String username, String password) {
        Long matricola = Long.parseLong(username);

        if (database.studentsLogin(matricola, password)) {
            AlertUtil.showSuccessAlert("Login riuscito");
            LongSession.getInstance().setId(matricola);
            loginController.handleSuccessfulLogin("MENU STUDENTE", "/application/DashboardStudent.fxml");
            return true;
        }
        return false;
    }
}
