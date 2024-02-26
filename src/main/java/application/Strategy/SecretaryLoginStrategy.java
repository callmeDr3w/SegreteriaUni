package application.Strategy;

import application.Controllers.LoginController;
import application.Database.Secretary.DatabaseSecretaryLogin;
import application.Models.Utils.AlertUtil;


/**
 * Classe utilizzata per la fase di login del segretrario
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class SecretaryLoginStrategy implements LoginStrategy{

    private final DatabaseSecretaryLogin database = new DatabaseSecretaryLogin();


    /**
     * Metodo utilizzato per effettuare il login
     *
     * @param loginController controller della schermata di login
     * @param username username per l'accesso
     * @param password password per l'accesso
     * @return ritorma la schermata del segretario o false se vi é errore
     */
  @Override
  public boolean login(LoginController loginController, String username, String password) {
      Integer id = Integer.parseInt(username);

      if (database.secretaryLogin(id, password)) {
          AlertUtil.showSuccessAlert("Login riuscito");
          loginController.handleSuccessfulLogin("MENU SEGRETERIA", "/application/DashboardSecretary.fxml");
          return true;
      }
      return false;
  }
}
