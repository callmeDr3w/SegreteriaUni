package application.CommandSecretary;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.StudentData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.*;


import java.sql.Date;
import java.time.LocalDate;

/**
 * Classe utilizzata per la creazione dello studente dal segretario/a
 *
 * @author Andrea Aristarco
 * @version 1.0
 */
// Comando concreto per l'aggiunta di uno studente
public class AddStudent implements Command {
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String residenza;
    private String pianoDiStudi;
    private String password;
    private ObservableList<StudentData> studentList;
    private TableView<StudentData> tabellaStudenti;
    private TextField tfNome;
    private TextField tfCognome;
    private DatePicker datePicker;
    private TextField tfResidenza;
    private PasswordField tfPassw;

    private ChoiceBox<String> boxStudi;
    private DatabaseSecretaryMenu databaseSecretaryMenu;


    /**
     * Costruttore che contiene i parametri della query da eseguire per l'aggiunta dello studente e i diversi field, liste e altro che si collegano alla schermata
     *
     * @param nome nome dello studente
     * @param cognome cognome dello studente
     * @param dataDiNascita data di nascita dello studente
     * @param residenza residenza dello studente
     * @param pianoDiStudi piano di studi dello studente
     * @param password password dello studente
     * @param studentList lista degli studenti
     * @param tabellaStudenti tabella studenti che mostra la lista degli studenti creati
     * @param tfNome field dove si inserisce il nome
     * @param tfCognome field dove si inserisce il cognome
     * @param datePicker picker dove si sinserisce la data
     * @param tfResidenza field dove si inserisce la residenza
     * @param tfPassw field dove si inserisce la password
     * @param boxStudi box dove inseriamo il oiano di studi
     */
    public AddStudent(String nome, String cognome, LocalDate dataDiNascita, String residenza, String pianoDiStudi, String password,
                      ObservableList<StudentData> studentList, TableView<StudentData> tabellaStudenti,
                      TextField tfNome, TextField tfCognome, DatePicker datePicker, TextField tfResidenza,
                      PasswordField tfPassw, ChoiceBox<String> boxStudi) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.residenza = residenza;
        this.pianoDiStudi = pianoDiStudi;
        this.password = password;
        this.studentList = studentList;
        this.tabellaStudenti = tabellaStudenti;
        this.tfNome = tfNome;
        this.tfCognome = tfCognome;
        this.datePicker = datePicker;
        this.tfResidenza = tfResidenza;
        this.tfPassw = tfPassw;
        this.boxStudi = boxStudi;
        this.databaseSecretaryMenu = new DatabaseSecretaryMenu();
    }

    /**
     * Metodo che inizialmente controlla se tutti i campi sono compilati a dovere e di seguito crea lo studente richiamando la query al database
     * Se va a buon fine, inserisce le tasse richiamando la query e mostra la tabella studenti aggiornata
     */
    @Override
    public void execute() {
        if (tfNome.getText().isEmpty() || tfCognome.getText().isEmpty() || tfResidenza.getText().isEmpty() || tfPassw.getText().isEmpty() ||
        boxStudi.getItems().isEmpty()) {
            AlertUtil.showErrorAlert("Compila tutti i campi");
        } else {
            //Recupero la matricola
            Long matricola = DatabaseSecretaryMenu.generateRandomNumber(10);

            //Richiamo della query per aggiungere lo studente
            boolean success = DatabaseSecretaryMenu.register(matricola, nome, cognome, Date.valueOf(dataDiNascita), residenza, pianoDiStudi, password);

            if (success) {
                studentList = databaseSecretaryMenu.getAllRecords();
                tabellaStudenti.setItems(studentList);
                tabellaStudenti.refresh();

                tfNome.clear();
                tfCognome.clear();
                datePicker.setValue(null);
                tfResidenza.clear();
                tfPassw.clear();
                boxStudi.getSelectionModel().clearSelection();
                AlertUtil.showSuccessAlert("studente inserito correttamente");
            } else {
                AlertUtil.showErrorAlert("Errore durante l'aggiunta dello studente.");
            }
        }
    }
}