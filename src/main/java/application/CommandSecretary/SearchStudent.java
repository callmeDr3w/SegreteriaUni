package application.CommandSecretary;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.StudentData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe utilizzata per cercare gli studenti con la barra di ricerca
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class SearchStudent implements Command {

    private ObservableList<StudentData> studentList;
    private DatabaseSecretaryMenu databaseSecretaryMenu;
    private TableView<StudentData> tabellaStudenti;
    private TextField tfSearch;

    /**
     * Costruttore che contiene i parametri per la ricerca
     *
     * @param studentList list degli studenti
     * @param databaseSecretaryMenu database che richiama la query
     * @param tabellaStudenti tabella che mostra gli studenti
     * @param tfSearch field per la barra di ricerca
     */
    public SearchStudent(ObservableList<StudentData> studentList,
                         DatabaseSecretaryMenu databaseSecretaryMenu,
                         TableView<StudentData> tabellaStudenti,
                         TextField tfSearch) {

        this.studentList = studentList;
        this.databaseSecretaryMenu = databaseSecretaryMenu;
        this.tabellaStudenti = tabellaStudenti;
        this.tfSearch = tfSearch;
    }

    /**
     * Metodo utilizzato per fare la ricerca degli studenti inserendo matricola, nome o cognome
     */
    @Override
    public void execute() {
        //trim per rimuovere spazi bianchi non necessari
        String searchStudents = tfSearch.getText().trim().toLowerCase();

        if (searchStudents.isEmpty()) {
            // Se il campo di ricerca è vuoto, mostra tutti gli studenti
            studentList = databaseSecretaryMenu.getAllRecords();
            tabellaStudenti.setItems(studentList);
        } else {
            // Altrimenti, filtra gli studenti in base al nome, cognome o matricola
            ObservableList<StudentData> filteredList = FXCollections.observableArrayList();

            //Verifico se la studentList non è nulla
            if (studentList != null) {
                for (StudentData student : this.studentList) {
                    if (student.getNome().toLowerCase().contains(searchStudents) || student.getCognome().toLowerCase().contains(searchStudents) || String.valueOf(student.getMatricola()).contains(searchStudents)) {
                        filteredList.add(student);
                    }
                }
            }

            // Aggiorna la TableView con la lista filtrata
            tabellaStudenti.setItems(filteredList);
            tabellaStudenti.refresh();
        }
    }
}