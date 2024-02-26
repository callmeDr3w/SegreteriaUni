package application.Strategy;

import application.Controllers.LoginController;
import application.Database.Teachers.DatabaseTeachersLogin;
import application.Models.LongSession;
import application.Models.Utils.AlertUtil;

/**
 * Classe utilizzata per la fase di login del'insegnante
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class TeachersLoginStrategy implements LoginStrategy{
    private final DatabaseTeachersLogin database = new DatabaseTeachersLogin();

    /**
     * Metodo utilizzato per effettuare il login
     *
     * @param loginController controller della schermata di login
     * @param username username per l'accesso
     * @param password password per l'accesso
     * @return ritorna la schermata dell'insegnante o false se vi é errore
     */
    @Override
    public boolean login(LoginController loginController, String username, String password) {
        Integer id = Integer.parseInt(username);

        if (database.teachersLogin(id, password)) {
            AlertUtil.showSuccessAlert("Login riuscito");
            LongSession.getInstance().setId(Long.valueOf(id));
            loginController.handleSuccessfulLogin("MENU DOCENTE", "/application/DashboardTeacher.fxml");
            return true;
        }
        return false;
    }
}
