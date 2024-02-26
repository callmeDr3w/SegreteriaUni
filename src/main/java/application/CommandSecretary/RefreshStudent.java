package application.CommandSecretary;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.StudentData;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe uutilizzata per il refresh della barra di ricerca degli studenti
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class RefreshStudent implements Command {

    private TextField tfSearch;
    private ObservableList<StudentData> studentList;
    private DatabaseSecretaryMenu databaseSecretaryMenu;
    private TableView<StudentData> tabellaStudenti;

    /**
     * Costruttore che contiene i parametri per il refresh
     *
     * @param tfSearch field barra di ricerca degli studenti
     * @param studentList list degli studenti
     * @param databaseSecretaryMenu database che richiama la query
     * @param tabellaStudenti tabella che mostra gli studenti
     */
    public RefreshStudent(TextField tfSearch,
                          ObservableList<StudentData> studentList,
                          DatabaseSecretaryMenu databaseSecretaryMenu,
                          TableView<StudentData> tabellaStudenti) {

        this.tfSearch = tfSearch;
        this.studentList = studentList;
        this.databaseSecretaryMenu = databaseSecretaryMenu;
        this.tabellaStudenti = tabellaStudenti;
    }

    /**
     * Metodo utilizzato per il refresh
     */
    @Override
    public void execute() {
        tfSearch.clear();
        studentList = databaseSecretaryMenu.getAllRecords();
        tabellaStudenti.setItems(studentList);
    }
}