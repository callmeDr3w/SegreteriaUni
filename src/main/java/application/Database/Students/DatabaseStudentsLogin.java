package application.Database.Students;

import application.Database.DatabaseConn;
import application.Models.Utils.AlertUtil;

import java.sql.*;

/**
 * Classe utilizzata per la operazioni al database quando si effettua il login
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DatabaseStudentsLogin {

    private Connection connection;


    /**
     * Metodo utilizzato per connettersi al database
     */
    public DatabaseStudentsLogin() {
        connection = DatabaseConn.connectDB(); // Assicurati che il metodo connectDB() sia disponibile nella classe database.
    }


    /**
     * Metodo utilizzato per l''accesso in fase di Login
     *
     * @param matricola matricola studente
     * @param password password studente
     * @return ritorna la query eseguita o false in fase di errore
     */
    public boolean studentsLogin(Long matricola, String password){

        //inserisco la query per selezionare i dati dal database
        String selectData = "SELECT matricola, password FROM students WHERE matricola = ? AND password = ?";
        //Setto i parametri ed eseguo la query
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
            preparedStatement.setLong(1, matricola);
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