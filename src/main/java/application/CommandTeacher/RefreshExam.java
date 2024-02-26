package application.CommandTeacher;

import application.Command;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.ExamsData;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe utilizzata per il refresh degli appelli dalla barra di ricerca
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class RefreshExam implements Command {
    private TextField tfSearch;
    private ObservableList<ExamsData> examsList;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private TableView<ExamsData> tabellaAppelli;

    /**
     * Costruttore che contiene i parametri per il refresh
     *
     * @param tfSearch field per la barra di ricerca
     * @param examsList list degli appelli
     * @param databaseTeacherMenu database che richiama la query
     * @param tabellaAppelli tabella che mostra gli appelli
     */
    public RefreshExam(TextField tfSearch, ObservableList<ExamsData> examsList,
                       DatabaseTeacherMenu databaseTeacherMenu, TableView<ExamsData> tabellaAppelli) {
        this.tfSearch = tfSearch;
        this.examsList = examsList;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.tabellaAppelli = tabellaAppelli;
    }

    /**
     * Metodo utilizzato per il refresh degli appelli
     */
    @Override
    public void execute() {
        tfSearch.clear();
        examsList = databaseTeacherMenu.getAllRecords();
        tabellaAppelli.setItems(examsList);
    }
}
