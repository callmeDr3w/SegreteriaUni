package application.Database.Secretary;

import application.Database.DatabaseConn;
import application.Models.Utils.AlertUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe utilizzata per la operazioni al database quando si effettua il login
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DatabaseSecretaryLogin {

    private Connection connection;

    /**
     * Metodo utilizzato per connettersi al database
     */
    public DatabaseSecretaryLogin() {
        connection = DatabaseConn.connectDB(); // Assicurati che il metodo connectDB() sia disponibile nella classe database.
    }

    /**
     * Metodo utilizzato per l''accesso in fase di Login
     *
     * @param id id del segretario
     * @param password password del segretario
     * @return ritorna la query oppure false se risultasse l'errore
     */
    public boolean secretaryLogin(Integer id, String password){

        //inserisco la query per selezionare i dati dal database
        String selectData = "SELECT id, password FROM secretary WHERE id = ? AND password = ?";
        //Setto i parametri ed eseguo la query
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error" + e.getMessage());
            return false;
        }
    }
}
