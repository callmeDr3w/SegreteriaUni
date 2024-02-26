package application.Strategy;

import application.Controllers.LoginController;

/**
 * Interfaccia Strategy, pattern utilizzato per la fase di login
 *
 * @author andreaaristarco
 * @version 1.0
 */
//Pattern Strategy per il login
public interface LoginStrategy {
    /**
     * Metodo utilizzato per la fase di login
     *
     * @param loginController controller della schermata di login
     * @param username username per l'accesso
     * @param password password per l'accesso
     * @return ritorna il login
     */
        boolean login(LoginController loginController, String username, String password);
    }