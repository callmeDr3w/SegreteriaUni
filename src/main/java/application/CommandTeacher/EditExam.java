package application.CommandTeacher;

import application.Command;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.ExamsData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Classe utilizzata per modificare i dati di un appello
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class EditExam implements Command {
    private ChoiceBox<String> boxEditNome;
    private ChoiceBox<String> boxEditOrario;
    private TextField tfEditAula;
    private TableView<ExamsData> tabellaAppelli;
    private DatePicker datePickerEditEx;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private Button modificabtnEdit;

    /**
     * Costruttore che contiene i parametri per modificare un appello
     *
     * @param boxEditNome box nome esami
     * @param boxEditOrario box orari esame
     * @param tfEditAula field aula esami
     * @param tabellaAppelli tabella che mostra gli appelli
     * @param datePickerEditEx picker per la data degli appelli
     * @param databaseTeacherMenu database che richiama la query
     * @param modificabtnEdit bottone che modifica l'esame
     */
    public EditExam(ChoiceBox<String> boxEditNome, ChoiceBox<String> boxEditOrario, TextField tfEditAula,
                    TableView<ExamsData> tabellaAppelli, DatePicker datePickerEditEx,
                    DatabaseTeacherMenu databaseTeacherMenu, Button modificabtnEdit) {

        this.boxEditNome = boxEditNome;
        this.boxEditOrario = boxEditOrario;
        this.tfEditAula = tfEditAula;
        this.tabellaAppelli = tabellaAppelli;
        this.datePickerEditEx = datePickerEditEx;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.modificabtnEdit = modificabtnEdit;
    }

    /**
     * Metodo utilizzato per modificare i dati di un appello
     */
    @Override
    public void execute() {
        if(boxEditNome.getItems().isEmpty() || boxEditOrario.getItems().isEmpty() || tfEditAula.getText().isEmpty()){
            AlertUtil.showErrorAlert("compila tutti i campi");
        }else {
            //Recupero il valore del'id esame dalla riga selezionata
            ExamsData selectedExam = tabellaAppelli.getSelectionModel().getSelectedItem();
            Integer idEsame = selectedExam.getIdEsame();
            //Recupero nome, data, orario, aula esame
            String nomeEsame = boxEditNome.getValue();
            LocalDate localDate = datePickerEditEx.getValue();
            Date dataEsame = Date.valueOf(localDate);
            String orarioEsame = boxEditOrario.getValue();
            String aulaEsame = tfEditAula.getText();

            // Richiamo della query per l'update dell'esame
            boolean success = databaseTeacherMenu.updateRecord(idEsame, nomeEsame, dataEsame, orarioEsame, aulaEsame);
            if (success) {

                // Ottieni la lista aggiornata di studenti
                ObservableList<ExamsData> updatedExamList = databaseTeacherMenu.getAllRecords();

                // Aggiorna la TableView con la lista aggiornata
                tabellaAppelli.setItems(updatedExamList);
                tabellaAppelli.refresh();
                Stage currentStage = (Stage) modificabtnEdit.getScene().getWindow();
                currentStage.close();

            } else {
                AlertUtil.showErrorAlert("Errore durante la modifica dell'esame.");
            }
        }
    }
}
