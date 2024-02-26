package application.Database.Students;

import application.Database.DatabaseConn;
import application.Models.FeesData;
import application.Models.ResultJoin;
import application.Models.Utils.AlertUtil;
import application.Models.VotiData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Classe utilizzata per operazioni al database quando si é nella dashboard
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DatabaseStudentsMenu{
    private static Connection connection;

    /**
     * Metodo utilizzato per connettersi al database
     */
    public DatabaseStudentsMenu(){
        connection = DatabaseConn.connectDB(); // Assicurati che il metodo connectDB() sia disponibile nella classe database.
    }


    /**
     * Metodo utilizzato per prenotare un esame
     *
     * @param idPrenotazione id della prenotazione
     * @param idEsamePre id dell'esame
     * @param matricolaStudente matricola dello studente
     * @return false se nel check trova matricola uguale ed errore, mentre nel caso ottimale ritorna le righe
     */
    public static boolean bookExam(Integer idPrenotazione, Integer idEsamePre, Long matricolaStudente) {
        String checkBooking = "SELECT * FROM bookingExams WHERE idEsamePre = ? AND matricolaStudente = ?";


        try (PreparedStatement checkBookingStatement = connection.prepareStatement(checkBooking)){

            checkBookingStatement.setInt(1, idEsamePre);
            checkBookingStatement.setLong(2, matricolaStudente);

            ResultSet resultSet = checkBookingStatement.executeQuery();
                if (resultSet.next()){
                    return false;
                }else {
                    String insertBookingData = "INSERT INTO bookingExams (idPrenotazione, idEsamePre, matricolaStudente) VALUES (?, ?, ?)";
                    try (PreparedStatement insertBookingStatement = connection.prepareStatement(insertBookingData)){
                        insertBookingStatement.setInt(1, idPrenotazione);
                        insertBookingStatement.setInt(2, idEsamePre);
                        insertBookingStatement.setLong(3, matricolaStudente);

                        int rowsAffected = insertBookingStatement.executeUpdate();
                        if (rowsAffected <= 0){
                            AlertUtil.showErrorAlert("Errore during registration");
                        }
                        return rowsAffected > 0;
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Errore durante la prenotazione dell'esame" + e.getMessage());
            return false;
        }
    }


    /**
     * Metodo utilizzato per aggiungere un voto
     *
     * @param idVoto id del voto
     * @param idPreVoto id della prenotazione
     * @param idEsameVoto id dell'esame
     * @param nomeExVoto nome dell'esame
     * @param matricolaVoto matricola studente
     * @param voto esito del voto
     * @param conferma true o false
     * @return ritorna le righe effettive oppure false in caso di errore
     */
    public static boolean addVote(Integer idVoto, Integer idPreVoto, Integer idEsameVoto, String nomeExVoto, Long matricolaVoto, Integer voto, Boolean conferma) {
        String insertVote = "INSERT INTO voti (idVoto, idPreVoto, idEsameVoto, nomeExVoto, matricolaVoto, voto, conferma) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertVoteStatement = connection.prepareStatement(insertVote)) {
            insertVoteStatement.setInt(1, idVoto);
            insertVoteStatement.setInt(2, idPreVoto);
            insertVoteStatement.setInt(3, idEsameVoto);
            insertVoteStatement.setString(4, nomeExVoto);
            insertVoteStatement.setLong(5, matricolaVoto);
            insertVoteStatement.setInt(6, voto);
            insertVoteStatement.setBoolean(7, conferma);

            int rowsAffected = insertVoteStatement.executeUpdate();
            if (rowsAffected <= 0) {
                AlertUtil.showErrorAlert("Errore durante l'inserimento del voto");
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Errore durante l'inserimento del voto" + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo utilizzato per aggiornare(confermare) un voto
     *
     * @param idVoto id del voto
     * @return false se le righe non sono presenti oppure vi é un un'errore
     */
    public static boolean updateVote(Integer idVoto) {
        String updateVote = "UPDATE voti SET conferma = true WHERE idVoto = ? AND conferma = false";


        try (PreparedStatement updateVoteStatement = connection.prepareStatement(updateVote)) {

            updateVoteStatement.setInt(1, idVoto);

            int rowsAffected = updateVoteStatement.executeUpdate();
            if (rowsAffected <= 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("errore durante la conferma dell'esame" + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo utilizzato per rifiutare un voto
     *
     * @param idVoto id del voto
     * @return false se le righe non sono presenti oppure vi é un un'errore
     */
    public static boolean deleteVote(Integer idVoto){
        String deleteVote = "DELETE FROM voti WHERE idVoto = ? AND conferma = FALSE";

        try(PreparedStatement deleteVoteStatement = connection.prepareStatement(deleteVote)){
            deleteVoteStatement.setInt(1, idVoto);

            int rowsAffected = deleteVoteStatement.executeUpdate();
            if (rowsAffected <= 0){
                return false;
            }
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("errore" + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo utilizzato per aggiornare lo stato delle tasse
     *
     * @param idTassa id della tassa
     * @return false se le righe non sono presenti oppure vi é un un'errore
     */
    public static boolean updateFees(Integer idTassa){
        String updateFees = "UPDATE tasse SET pagata = true WHERE idTassa = ? AND pagata = false";

        try (PreparedStatement updateFeesStatement = connection.prepareStatement(updateFees)){
            updateFeesStatement.setInt(1, idTassa);
            int rowsAffected = updateFeesStatement.executeUpdate();
            if (rowsAffected <= 0) {
                return false;
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
           AlertUtil.showErrorAlert("errore durante il pagamento della tassa" + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo utilizzato per aggiungere un questionario
     *
     * @param idDomanda id domanda
     * @param matricolaDomanda matricola studente
     * @param esameDomanda nome esame
     * @param domanda1 domanda 1
     * @param domanda2 domanda 2
     * @param domanda3 domanda 3
     * @return false se vi é un un'errore
     */
    //l'ho configurato a mano
    public static boolean addQuest(Integer idDomanda, Long matricolaDomanda, String esameDomanda, String domanda1, String domanda2, String domanda3){
        String insertQuest = "INSERT INTO domandeQuest (idDomanda, matricolaDomanda, esameDomanda, domanda1, domanda2, domanda3) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertQuestStatement = connection.prepareStatement(insertQuest)){
            insertQuestStatement.setInt(1, idDomanda);
            insertQuestStatement.setLong(2, matricolaDomanda);
            insertQuestStatement.setString(3, esameDomanda);
            insertQuestStatement.setString(4, domanda1);
            insertQuestStatement.setString(5, domanda2);
            insertQuestStatement.setString(6, domanda3);

            int rowsAffected = insertQuestStatement.executeUpdate();
            if (rowsAffected <= 0) {
                AlertUtil.showErrorAlert("Errore durante l'inserimento della domanda");
            }
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("Errore durante l'inserimento della domanda" + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo utilizzato per aggiornare lo stato del questionario
     *
     * @param idQuest id questionario
     * @param effettuato true o false
     * @return false se le righe non sono presenti oppure vi é un un'errore
     */
    public static boolean updateQuest(Integer idQuest, Boolean effettuato){
        String updateQuest = "UPDATE questionari SET effettuato = true WHERE idQuest = ? AND effettuato = false";

        try (PreparedStatement updateQuestStatement = connection.prepareStatement(updateQuest)) {
            updateQuestStatement.setInt(1, idQuest);
            int rowsAffected = updateQuestStatement.executeUpdate();
            if (rowsAffected <= 0) {
                return false;
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("errore durante l'update del questionario");
            return false;
        }
    }

    /**
     * Metodo utilizzato per visualizzare nella tabella tutti i records dei voti
     *
     * @return ritorna la lista effettiva dei voti
     */
    public ObservableList<VotiData> getAllVote(){
        String selectedVote = "SELECT * FROM voti";
        ObservableList<VotiData> voteList = FXCollections.observableArrayList();

        try (PreparedStatement selectVoteStatement = connection.prepareStatement(selectedVote);
             ResultSet resultSet = selectVoteStatement.executeQuery()){

            while (resultSet.next()){
                Integer idVoto = resultSet.getInt("idVoto");
                Integer idPreVoto = resultSet.getInt("idPreVoto");
                Integer idEsameVoto = resultSet.getInt("idEsameVoto");
                String nomeExVoto = resultSet.getString("nomeExVoto");
                Long matricolaVoto = resultSet.getLong("matricolaVoto");
                Integer voto = resultSet.getInt("voto");
                Boolean conferma = resultSet.getBoolean("conferma");

                VotiData votiData = new VotiData(idVoto, idPreVoto, idEsameVoto, nomeExVoto, matricolaVoto, voto, conferma);
                voteList.add(votiData);
            }
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("ERRORE DURANTE IL RECUPERO DATI");
        }
        return voteList;
    }

    /**
     * Metodo utilizzato per visualizzare nella tabella tutti i records delle prenotazioni
     *
     * @return ritorna la lista effettiva del join tra tabelle
     */
    public ObservableList<ResultJoin> getAllBookingJoin(){
        String selectedJoin = "SELECT b.idPrenotazione, e.* FROM bookingExams b RIGHT JOIN exams e ON b.idEsamePre = e.idEsame";
        ObservableList<ResultJoin> joinList = FXCollections.observableArrayList();

        try (PreparedStatement selectedJoinStatement = connection.prepareStatement(selectedJoin);
        ResultSet resultSet = selectedJoinStatement.executeQuery()){

            while (resultSet.next()){
                Integer idPreJoin = resultSet.getInt("idPrenotazione");
                Integer idExJoin = resultSet.getInt("idEsame");
                String nomeExJoin = resultSet.getString("nomeEsame");
                Date dataExJoin = resultSet.getDate("dataEsame");
                String oraExJoin = resultSet.getString("orarioEsame");
                String aulaExJoin = resultSet.getString("aulaEsame");

                ResultJoin resultJoin = new ResultJoin(idPreJoin, idExJoin, nomeExJoin, dataExJoin, oraExJoin, aulaExJoin);
                joinList.add(resultJoin);
            }
        }catch (SQLException e){
            e.printStackTrace();
            AlertUtil.showErrorAlert("errore durante il recupero dati");
        }
        return joinList;
    }

    /**
     * Metodo utilizzato per visualizzare nella tabella tutti i records delle tasse
     *
     * @param matricola matricola studente
     * @return ritorna la lista effettiva delle tasse
     */
    public ObservableList<FeesData> getAllStudentFees(Long matricola){
        String selectedFees = "SELECT tasse.* FROM tasse JOIN students ON tasse.matricolaTassa = students.matricola WHERE students.matricola = ?";
        ObservableList<FeesData> feesList = FXCollections.observableArrayList();

        try (PreparedStatement selectedFeesStatement = connection.prepareStatement(selectedFees)) {
            // Imposta il valore del parametro nella query
            selectedFeesStatement.setLong(1, matricola);

            try (ResultSet resultSet = selectedFeesStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer idTassa = resultSet.getInt("idTassa");
                    Long matricolaTassa = resultSet.getLong("matricolaTassa");
                    String causale = resultSet.getString("causale");
                    Double importo = resultSet.getDouble("importo");
                    Date dataScadenza = resultSet.getDate("dataScadenza");
                    Boolean pagata = resultSet.getBoolean("pagata");

                    FeesData feesData = new FeesData(idTassa, matricolaTassa, causale, importo, dataScadenza, pagata);
                    feesList.add(feesData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                AlertUtil.showErrorAlert("ERRORE DURANTE IL RECUPERO DATI");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("ERRORE DURANTE IL RECUPERO DATI");
        }
        return feesList;
    }


}
