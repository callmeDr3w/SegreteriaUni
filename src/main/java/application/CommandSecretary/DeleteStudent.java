package application.CommandSecretary;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.StudentData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


/**
 * Classe utilizzata per eliminare uno studente dal segretario
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DeleteStudent implements Command {

    private DatabaseSecretaryMenu databaseSecretaryMenu;
    private ObservableList<StudentData> studentList;
    private TableView<StudentData> tabellaStudenti;

    /**
     * Costruttore che contiene i parametri per eliminare lo studente
     *
     * @param databaseSecretaryMenu database che richiama la query
     * @param studentList list degli studenti
     * @param tabellaStudenti tabella che mostra gli studenti
     */
    public DeleteStudent(DatabaseSecretaryMenu databaseSecretaryMenu,
                         ObservableList<StudentData> studentList, TableView<StudentData> tabellaStudenti) {
        this.databaseSecretaryMenu = databaseSecretaryMenu;
        this.studentList = studentList;
        this.tabellaStudenti = tabellaStudenti;
    }

    /**
     * Metodo utilizzato per eliminare lo studente
     * Seleziono il record dalla tabella prendendo la chiave matricola ed eseguo la query al database
     * Se andato a buon fine aggiorna la tabella mostrando solo gli studenti ancora presenti
     */
    @Override
    public void execute() {
        StudentData selectedStudent = tabellaStudenti.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            //Recupero matricola selezionata
            Long matricola = selectedStudent.getMatricola();

            //Richiamo della query per eliminare lo studente
            boolean success = databaseSecretaryMenu.deleteRecord(matricola);

            if (success) {
                studentList = databaseSecretaryMenu.getAllRecords();
                tabellaStudenti.setItems(studentList);
                tabellaStudenti.refresh();
                AlertUtil.showSuccessAlert("studente eliminato correttamente");
            } else {
                AlertUtil.showErrorAlert("Errore durante l'eliminazione dello studente.");
            }
        }
    }
}