package application.Database.Teachers;

import application.Database.DatabaseConn;
import application.Models.BookingExamsData;
import application.Models.ExamsData;
import application.Models.Utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.*;

/**
 * Classe utilizzata per operazioni al database quando si é nella dashboard
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DatabaseTeacherMenu {
    private static Connection connection;

    /**
     * Metodo utilizzato per connettersi al database
     */
    public DatabaseTeacherMenu() {
        connection = DatabaseConn.connectDB(); // Assicurati che il metodo connectDB() sia disponibile nella classe database.
    }


    /**
     * Metodo utilizzato per la registrazione dell'esame
     *
     * @param idEsame id dell'esame
     * @param nomeEsame nome esame
     * @param dataEsame data dell'esame
     * @param orarioEsame orario dell'esame
     * @param aulaEsame aula dell'esame
     * @return false se le righe non sono presenti oppure vi é un un'errore
     */
    public static boolean register(Integer idEsame, String nomeEsame, Date dataEsame, String orarioEsame, String aulaEsame){
        String checkData = "SELECT idEsame, nomeEsame FROM exams WHERE idEsame = ? AND nomeEsame = ?";
        String insertData = "INSERT INTO exams (idEsame, nomeEsame, dataEsame, orarioEsame, aulaEsame) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement checkDataStatement = connection.prepareStatement(checkData);
                PreparedStatement insertDataStatement = connection.prepareStatement(insertData)){

            checkDataStatement.setInt(1, idEsame);
            checkDataStatement.setString(2, nomeEsame);


            try (ResultSet resultSet = checkDataStatement.executeQuery()){
                if (resultSet.next()){
                    AlertUtil.showErrorAlert("esame già creato");
                }
            }

                insertDataStatement.setInt(1, idEsame);
                insertDataStatement.setString(2, nomeEsame);
                insertDataStatement.setDate(3, dataEsame);
                insertDataStatement.setString(4, orarioEsame);
                insertDataStatement.setString(5, aulaEsame);

                int rowsAffected = insertDataStatement.executeUpdate();
                if (rowsAffected <= 0){
                    AlertUtil.showErrorAlert("Errore during the registration.");
            }
               return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("Errore durante la registrazione" + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo utilizzato per l'eliminazione dell'esame
     *
     * @param idEsame id dell'esame
     * @return le righe presenti oppure false se vi é un un'errore
     */
    public boolean deleteRecord(Integer idEsame){
        String deleteData = "DELETE FROM exams WHERE idEsame = ?";

        try(PreparedStatement deleteDataStatement = connection.prepareStatement(deleteData)){
            deleteDataStatement.setInt(1, idEsame);

            int rowsAffected = deleteDataStatement.executeUpdate();
            if (rowsAffected <= 0){
                AlertUtil.showErrorAlert("errore durante l'eliminazione dell'esame");
            }
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("errore durante l'eliminazione dell' esame" + e.getMessage());
            return false;
        }
    }


    /**
     * Metodo utilizzato per l'eliminazione della prenotazione
     *
     * @param idPrenotazione id della prenotazione
     * @return le righe presenti oppure false se vi é un un'errore
     */
    public boolean deleteRecordPre(Integer idPrenotazione){
        String deleteDataPre = "DELETE FROM bookingExams WHERE idPrenotazione = ?";

        try(PreparedStatement deleteDataPreStatement = connection.prepareStatement(deleteDataPre)){
            deleteDataPreStatement.setInt(1, idPrenotazione);

            int rowsAffected = deleteDataPreStatement.executeUpdate();
            if (rowsAffected <= 0){
                AlertUtil.showErrorAlert("errore durante l'eliminazione dell'esame");
            }
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("errore durante l'eliminazione dell' esame" + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo utilizzato per modificare i dati dell'esame
     *
     * @param idEsame id dell'esame
     * @param nomeEsame nome esame
     * @param dataEsame data dell'esame
     * @param orarioEsame orario dell'esame
     * @param aulaEsame aula dell'esame
     * @return false se le righe non sono presenti oppure vi é un un'errore
     */
    public boolean updateRecord(Integer idEsame, String nomeEsame, Date dataEsame, String orarioEsame, String aulaEsame){
        String updateData = "UPDATE exams SET nomeEsame = ?, dataEsame = ?, orarioEsame = ?, aulaEsame = ? WHERE idEsame = ?";

        try(PreparedStatement updateDataStatement = connection.prepareStatement(updateData)){
            updateDataStatement.setString(1, nomeEsame);
            updateDataStatement.setDate(2, dataEsame);
            updateDataStatement.setString(3, orarioEsame);
            updateDataStatement.setString(4, aulaEsame);
            updateDataStatement.setInt(5, idEsame);

            int rowsAffected = updateDataStatement.executeUpdate();
            if(rowsAffected <= 0){
                AlertUtil.showErrorAlert("errore durante la modifica dell'esame");
                return false;
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("errore durante la modifica dell'esame" + e.getMessage());
            return false;

        }
    }

    /**
     * Metodo utilizzato per visualizzare nella tabella tutti i records degli appelli
     *
     * @return la lista effettiva degli appelli
     */
    public ObservableList<ExamsData> getAllRecords(){
        String selectDataTab = "SELECT * FROM exams";
        ObservableList<ExamsData> examsList = FXCollections.observableArrayList();

        try (PreparedStatement selectDataTabStatement = connection.prepareStatement(selectDataTab);
             ResultSet resultSet = selectDataTabStatement.executeQuery()){

            while(resultSet.next()){
                Integer idEsame = resultSet.getInt("idEsame");
                String nomeEsame = resultSet.getString("nomeEsame");
                Date dataEsame = resultSet.getDate("dataEsame");
                String orarioEsame = resultSet.getString("orarioEsame");
                String aulaEsame = resultSet.getString("aulaEsame");

                ExamsData examsData = new ExamsData(idEsame, nomeEsame, dataEsame, orarioEsame, aulaEsame);
                examsList.add(examsData);
            }

        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("Errore durante il recupero dei dati");
        }

        return examsList;
    }

    /**
     * Metodo utilizzato per visualizzare nella tabella tutti i records delle prenotazioni
     *
     * @return la lista effettiva delle prenotazioni
     */
    public ObservableList<BookingExamsData> getAllBooking(){
        String selectDataTab = "SELECT * FROM bookingExams";
        ObservableList<BookingExamsData> bookingList = FXCollections.observableArrayList();

        try (PreparedStatement selectDataTabStatement = connection.prepareStatement(selectDataTab);
            ResultSet resultSet = selectDataTabStatement.executeQuery()){

            while (resultSet.next()){
                Integer idPrenotazione = resultSet.getInt("idPrenotazione");
                Integer idEsamePre = resultSet.getInt("idEsamePre");
                Long matricolaStudente = resultSet.getLong("matricolaStudente");

                BookingExamsData bookingExamsData = new BookingExamsData(idPrenotazione, idEsamePre, matricolaStudente);
                bookingList.add(bookingExamsData);
            }
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("errore durante il recupero dati");
        }

        return bookingList;
    }
}
