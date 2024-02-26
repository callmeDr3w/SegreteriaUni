package application.Database.Secretary;

import application.Database.DatabaseConn;
import application.Models.*;
import application.Models.Utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Random;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe utilizzata per operazioni al database quando si é nella dashboard
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DatabaseSecretaryMenu{
        private static Connection connection;

    /**
     * Metodo utilizzato per connettersi al database
     */
        public DatabaseSecretaryMenu() {
            connection = DatabaseConn.connectDB(); // Assicurati che il metodo connectDB() sia disponibile nella classe database.
        }


    /**
     * Metodo utilizzato  per la registrazione dello studente
     *
     * @param matricola matricola studente
     * @param nome nome studente
     * @param cognome cognome studente
     * @param dataDiNascita data di nascita studente
     * @param residenza residenza studente
     * @param pianoDiStudi piano di studi studente
     * @param password password studente
     * @return false o righe effettive
     */
    public static boolean register(Long matricola, String nome, String cognome, Date dataDiNascita, String residenza, String pianoDiStudi, String password){
            //Garantisce un check per non far uscire matricole con numeri uguali
            String checkData = "SELECT matricola FROM students WHERE matricola = ?";
            String insertData = "INSERT INTO students (matricola, nome, cognome, dataDiNascita, residenza, pianoDiStudi, password) VALUES(?, ?, ?, ?, ?, ?, ?)";

         try (PreparedStatement checkDataStatement = connection.prepareStatement(checkData);
            PreparedStatement insertDataStatement = connection.prepareStatement(insertData)){

             checkDataStatement.setLong(1, matricola);

            try (ResultSet resultSet = checkDataStatement.executeQuery()) {
                if(resultSet.next()){
                    AlertUtil.showErrorAlert("Matricola già esistente");
                }

            }
                if(password.length() < 8){
                    AlertUtil.showErrorAlert("La password è troppo corta");
                    return false;
                }

                long randomNumber = generateRandomNumber(10);

                insertDataStatement.setLong(1, randomNumber);
                insertDataStatement.setString(2, nome);
                insertDataStatement.setString(3, cognome);
                insertDataStatement.setDate(4, dataDiNascita);
                insertDataStatement.setString(5, residenza);
                insertDataStatement.setString(6, pianoDiStudi);
                insertDataStatement.setString(7, password);

                int rowsAffected = insertDataStatement.executeUpdate();
                if (rowsAffected <= 0) {
                    AlertUtil.showErrorAlert("Errore during the registration.");
                }
                    return rowsAffected > 0;

            } catch (SQLException e){
                e.printStackTrace();
                AlertUtil.showErrorAlert("Errore durante la registrazione" + e.getMessage());
                return false;
            }
    }



    /**
     * Metodo utilizzato per l'eliminazione di studenti
     *
     * @param matricola matricola studente
     * @return false o righe effettive
     */
    //Metodo per l'eliminazione di studenti
    public boolean deleteRecord(Long matricola){
            String deleteData = "DELETE FROM students WHERE matricola = ?";

            try (PreparedStatement deleteDataStatement = connection.prepareStatement(deleteData)){
                deleteDataStatement.setLong(1, matricola);

                int rowsAffected = deleteDataStatement.executeUpdate();
                if(rowsAffected <= 0){
                    AlertUtil.showErrorAlert("errore durante l'eliminazione dello studente");
                }
                return rowsAffected > 0;
            } catch (SQLException e){
                e.printStackTrace();
                AlertUtil.showErrorAlert("errore durante l'eliminazione dello studente" + e.getMessage());
                return false;
            }
    }

    /**
     *Metodo utilizzato per eliminare le tasse
     *
     * @param idTassa id della tassa
     * @return false nel caso in cui si presentasse un'errore
     */
    public boolean deleteFees(Integer idTassa){
        String deleteFees = "DELETE FROM tasse WHERE idTassa = ?";

        try(PreparedStatement deleteFeesStatement = connection.prepareStatement(deleteFees)){
            deleteFeesStatement.setInt(1, idTassa);

            int rowsAffected = deleteFeesStatement.executeUpdate();
            if(rowsAffected <= 0){
                AlertUtil.showErrorAlert("errore durante l'eliminazione della tassa");
            }
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("errore durante l'eliminazione della tassa" + e.getMessage());
            return false;
        }

    }

    /**
     * Metodo utilizzato per modificare lo studente
     *
     * @param matricola matricola studente
     * @param nome nome studente
     * @param cognome cognome studente
     * @param dataDiNascita data di nascita studente
     * @param residenza residenza studente
     * @param pianoDiStudi piano di studi studente
     * @param password password studente
     * @return false se le righe non esistono oppure si presenta un'errore
     */
    public boolean updateRecord(Long matricola, String nome, String cognome, Date dataDiNascita, String residenza, String pianoDiStudi, String password){
            String updateData = "UPDATE students SET nome = ?, cognome = ?, dataDiNascita = ?, residenza = ?, pianoDiStudi = ?, password = ? WHERE matricola = ?";

            try (PreparedStatement updateDataStatement = connection.prepareStatement(updateData)){
                updateDataStatement.setString(1, nome);
                updateDataStatement.setString(2, cognome);
                updateDataStatement.setDate(3, dataDiNascita);
                updateDataStatement.setString(4, residenza);
                updateDataStatement.setString(5, pianoDiStudi);
                updateDataStatement.setString(6, password);
                updateDataStatement.setLong(7, matricola);

                int rowsAffected = updateDataStatement.executeUpdate();
                if(rowsAffected <= 0){
                    AlertUtil.showErrorAlert("errore durante la modifica dello studente");
                    return false;
                }
                return true;
            } catch (SQLException e){
                e.printStackTrace();
                AlertUtil.showErrorAlert("errore durante la modifica dello studente" + e.getMessage());
                return false;

            }
    }

    /**
     * Metodo utilizzato per inserire le tasse
     *
     * @param idTassa id tassa
     * @param matricolaTassa matricola studente di quella tassa
     * @return false se le righe non esistono oppure si presenta un'errore
     */
    public static boolean insertFees(Integer idTassa, Long matricolaTassa) {
        String[] causali = {"Tassa d iscrizione 1-3", "Tassa d iscrizione 2-3", "Tassa d iscrizione 3-3"};
        Double[] importi = {120.00, 60.00, 60.00};
        //Uso date scelte personalmente, nel caso in cui gli anni passino, utilizzo la data locale e sommo i mesi
        String[] dataScadenza = {"2024-01-31", "2024-04-30", "2024-09-30"};

        String checkData = "SELECT idTassa, matricolaTassa FROM tasse WHERE matricolaTassa = ?";
        String insertData = "INSERT INTO tasse (idTassa, matricolaTassa, causale, importo, dataScadenza, pagata) VALUES (?, ?, ?, ?, ?, 0)";


        try (PreparedStatement checkDataStatement = connection.prepareStatement(checkData);
             PreparedStatement insertDataStatement = connection.prepareStatement(insertData)) {

                checkDataStatement.setLong(1, matricolaTassa);

                try (ResultSet resultSet = checkDataStatement.executeQuery()) {
                    if (resultSet.next()) {
                        AlertUtil.showErrorAlert("tassa giá creata");
                        return false;
                    }
                }


            for (int i = 0; i < 3; i++) {
                long randomNumber = generateRandomNumber(6);
                insertDataStatement.setInt(1, (int) randomNumber);
                insertDataStatement.setLong(2, matricolaTassa);
                insertDataStatement.setString(3, causali[i]);
                insertDataStatement.setDouble(4, importi[i]);
                insertDataStatement.setDate(5, java.sql.Date.valueOf(dataScadenza[i]));
                // Pagata è impostata a 0 per impostazione predefinita
                insertDataStatement.addBatch();  // Aggiungi la query al batch
            }

            // Esegui tutte le query del batch
            int[] rowsAffected = insertDataStatement.executeBatch();

            // Verifica se almeno una query ha modificato le righe
            for (int rows : rowsAffected) {
                if (rows <= 0) {
                    AlertUtil.showErrorAlert("Errore tasse");
                    return false;
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("tasse non inserite");
            return false;
        }
    }


    /**
     * Metodo utilizzato per visualizzare nella tabella tutti i records degli studenti
     *
     * @return la lista effettiva degli studenti
     */
    public ObservableList<StudentData> getAllRecords(){
            String selectDataTab = "SELECT * FROM students";
        ObservableList<StudentData> studentList = FXCollections.observableArrayList();

        try (PreparedStatement selectDataTabStatement = connection.prepareStatement(selectDataTab);
        ResultSet resultSet = selectDataTabStatement.executeQuery()){

            while(resultSet.next()){
                Long matricola = resultSet.getLong("matricola");
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                Date dataDiNascita = resultSet.getDate("dataDiNascita");
                String residenza = resultSet.getString("residenza");
                String pianoDiStudi = resultSet.getString("pianoDiStudi");
                String password = resultSet.getString("password");


                StudentData studentData = new StudentData(matricola, nome, cognome, dataDiNascita, residenza, pianoDiStudi, password);
                studentList.add(studentData);
            }

        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("Errore durante il recupero dei dati");
        }
        return studentList;
    }

    /**
     * Metodo utilizzato per visualizzare nella tabella tutti i records dei questionari
     *
     * @return la lista effettiva dei questionari
     */
    public ObservableList<QuestData> getAllQuest(){
            String selectQuest = "SELECT * FROM questionari";

        ObservableList<QuestData> questList = FXCollections.observableArrayList();

        try (PreparedStatement selectQuestStatement = connection.prepareStatement(selectQuest);
             ResultSet resultSet = selectQuestStatement.executeQuery()){

            while(resultSet.next()){
                Integer idQuest = resultSet.getInt("idQuest");
                String nomeExQuest = resultSet.getString("nomeExQuest");
                Boolean effettuato = resultSet.getBoolean("effettuato");

                QuestData questData = new QuestData(idQuest, nomeExQuest, effettuato);
                questList.add(questData);
            }

        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("Errore durante il recupero dei dati");
        }


        return questList;
    }

    /**
     * Metodo utilizzato per visualizzare nella tabella tutti i records delle tasse
     *
     * @return la lista effettiva delle tasse
     */
    public ObservableList<FeesData> getAllFees(){
            String selectFees = "SELECT * FROM tasse";

            ObservableList<FeesData> feesList = FXCollections.observableArrayList();

            try (PreparedStatement selectFeesStatement = connection.prepareStatement(selectFees);
                 ResultSet resultSet = selectFeesStatement.executeQuery()){

                while (resultSet.next()){
                    Integer idTassa = resultSet.getInt("idTassa");
                    Long matricolaTassa = resultSet.getLong("matricolaTassa");
                    String causale = resultSet.getString("causale");
                    Double importo = resultSet.getDouble("importo");
                    Date dataScadenza = resultSet.getDate("dataScadenza");
                    Boolean pagata = resultSet.getBoolean("pagata");

                    FeesData feesData = new FeesData(idTassa, matricolaTassa, causale, importo, dataScadenza, pagata);
                    feesList.add(feesData);
                }
            }catch (SQLException e) {
                e.printStackTrace();
                AlertUtil.showErrorAlert("ERRORE DURANTE IL RECUPERO DATI");
            }
        return feesList;
    }


    /**
     * Metodo utilizzato per generare un numero random
     *
     * @param length lunghezza del valore da assumere
     * @return numero casuale di una certa lunghezza con una parte fissa
     */
    public static long generateRandomNumber(int length){
            Random random = new Random();
        long upperBound = (long) Math.pow(10, length) - 1;
        return (long) (Math.random() * upperBound) + (long) Math.pow(10, length - 1);

    }
}
