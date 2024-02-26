package application.Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe utilizzata per connettersi al database
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DatabaseConn {

    /**
     * Metodo utilizzato per stabilire la connessione con il database
     *
     * @return ritorna la connessione o null in caso di errore
     */
    public static Connection connectDB() {

        try {
            // Carica il driver JDBC per MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crea una connessione al database "segreteria" su localhost con utente "root" e password vuota
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/segreteria", "root", "");

            // Restituisce la connessione
            return connect;

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Situazione di errore, restituisce null
        return null;
    }

}