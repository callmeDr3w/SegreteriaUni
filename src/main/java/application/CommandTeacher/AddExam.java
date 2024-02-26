package application.CommandTeacher;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.ExamsData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Classe utilizzata per aggiungere gli appelli
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class AddExam implements Command {
    private ChoiceBox<String> boxEsami;
    private ChoiceBox<String> boxOrari;
    private TextField tfAula;
    private DatePicker datePickerEsami;
    private ObservableList<ExamsData> examsList;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private TableView<ExamsData> tabellaAppelli;

    /**
     * Costruttore che contiene i parametri per aggiungere un appello
     *
     * @param boxEsami box degli esami
     * @param boxOrari box degli orari
     * @param tfAula field numero dell'aula
     * @param datePickerEsami picker data dell'esame
     * @param examsList list degli esami
     * @param databaseTeacherMenu database che richiama la query
     * @param tabellaAppelli tabella che mostra gli appelli
     */
    public AddExam(ChoiceBox<String> boxEsami, ChoiceBox<String> boxOrari, TextField tfAula,
                   DatePicker datePickerEsami, ObservableList<ExamsData> examsList,
                   DatabaseTeacherMenu databaseTeacherMenu, TableView<ExamsData> tabellaAppelli) {

        this.boxEsami = boxEsami;
        this.boxOrari = boxOrari;
        this.tfAula = tfAula;
        this.datePickerEsami = datePickerEsami;
        this.examsList = examsList;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.tabellaAppelli = tabellaAppelli;
    }

    /**
     * Metodo utilizzato per aggiungere un appello
     */
    @Override
    public void execute() {
        if(boxEsami.getItems().isEmpty() || boxOrari.getItems().isEmpty() || tfAula.getText().isEmpty()){
            AlertUtil.showErrorAlert("compila tutti i campi");
        }else{
            //Recupero nome, data, orario e aula dell'esame
            String nomeEsame = boxEsami.getValue();
            LocalDate localDate = datePickerEsami.getValue();
            Date dataEsame = Date.valueOf(localDate);
            String orarioEsame = boxOrari.getValue();
            String aulaEsame = tfAula.getText();

            //Richiamo della query per l'aggiunta dell'appello
            boolean success = DatabaseTeacherMenu.register(((int) DatabaseSecretaryMenu.generateRandomNumber(5)), nomeEsame, dataEsame, orarioEsame, aulaEsame);

            if (success) {
                // Aggiorna la TableView con la lista aggiornata degli appelli dal database
                examsList = databaseTeacherMenu.getAllRecords();
                tabellaAppelli.setItems(examsList);
                tabellaAppelli.refresh();
                AlertUtil.showSuccessAlert("appello inserito correttamente");

                // Pulisci i campi del modulo dopo l'aggiunta
                boxEsami.getSelectionModel().clearSelection();
                datePickerEsami.setValue(null);
                boxOrari.getSelectionModel().clearSelection();
                tfAula.clear();

            } else {
                AlertUtil.showErrorAlert("Errore durante l'aggiunta dell'appello.");
            }
        }
    }
}
