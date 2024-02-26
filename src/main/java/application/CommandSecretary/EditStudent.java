package application.CommandSecretary;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.StudentData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Classe utilizzata per modificare i dati degli studenti in caso di errore nell'inserimento dei dati
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class EditStudent implements Command {

    private TableView<StudentData> tabellaStudenti;
    private DatabaseSecretaryMenu databaseSecretaryMenu;
    private TextField tfNomeEdit;
    private TextField tfCognEdit;
    private DatePicker datePickerEdit;
    private TextField tfResiEdit;
    private ChoiceBox<String> boxStudiEdit;
    private TextField tfPasswEdit;
    private Button modificabtnEdit;

    /**
     * Costruttore che contiene i parametri per editare lo studente
     *
     * @param tabellaStudenti tabella che mostra gli studenti
     * @param databaseSecretaryMenu database che richiama la query
     * @param tfNomeEdit field nome editato
     * @param tfCognEdit field cognome editato
     * @param datePickerEdit picker data editata
     * @param tfResiEdit field residenza editata
     * @param boxStudiEdit box piano di studi editato
     * @param tfPasswEdit field password editato
     * @param modificabtnEdit bottone per modificare
     */
    public EditStudent(TableView<StudentData> tabellaStudenti,
                       DatabaseSecretaryMenu databaseSecretaryMenu,
                       TextField tfNomeEdit, TextField tfCognEdit,
                       DatePicker datePickerEdit, TextField tfResiEdit,
                       ChoiceBox<String> boxStudiEdit,
                       TextField tfPasswEdit,
                       Button modificabtnEdit) {

        this.tabellaStudenti = tabellaStudenti;
        this.databaseSecretaryMenu = databaseSecretaryMenu;
        this.tfNomeEdit = tfNomeEdit;
        this.tfCognEdit = tfCognEdit;
        this.datePickerEdit = datePickerEdit;
        this.tfResiEdit = tfResiEdit;
        this.boxStudiEdit = boxStudiEdit;
        this.tfPasswEdit = tfPasswEdit;
        this.modificabtnEdit = modificabtnEdit;
    }

    /**
     * Metodo utilizzato per editare i dati degli studenti
     * Inizialmente si coontrolla la giusta compilazione dei campi per poi recuperare i dati necessari per aggiornarli ed eseguire la query
     * Se andato a buon fine mostra la tabella con i dati modificati e chiude la schermata dell'edit
     */
    @Override
    public void execute() {
        if (tfNomeEdit.getText().isEmpty() || tfCognEdit.getText().isEmpty() || tfResiEdit.getText().isEmpty() || tfPasswEdit.getText().isEmpty()) {
            AlertUtil.showErrorAlert("compila tutti i campi");
        } else {
            //Recupero il valore della matricola dalla riga selezionata
            StudentData selectedStudent = tabellaStudenti.getSelectionModel().getSelectedItem();
            Long matricola = selectedStudent.getMatricola();

            String nome = tfNomeEdit.getText();
            String cognome = tfCognEdit.getText();
            LocalDate localDate = datePickerEdit.getValue();
            Date dataDiNascita = Date.valueOf(localDate);
            String residenza = tfResiEdit.getText();
            String pianoDiStudi = boxStudiEdit.getValue();
            String password = tfPasswEdit.getText();

            // Chiamare il metodo updateRecord del database
            boolean success = databaseSecretaryMenu.updateRecord(matricola, nome, cognome, dataDiNascita, residenza, pianoDiStudi, password);
            if (success) {

                // Ottieni la lista aggiornata di studenti
                ObservableList<StudentData> updatedStudentList = databaseSecretaryMenu.getAllRecords();

                // Aggiorna la TableView con la lista aggiornata
                tabellaStudenti.setItems(updatedStudentList);
                tabellaStudenti.refresh();
                Stage currentStage = (Stage) modificabtnEdit.getScene().getWindow();
                currentStage.close();

            } else {
                AlertUtil.showErrorAlert("Errore durante la modifica dello studente.");
            }
        }
    }
}
