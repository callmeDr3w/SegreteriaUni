package application.CommandTeacher;

import application.Command;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.ExamsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe utilizzata per cercare gli appelli tramite barra di ricerca
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class SearchExam implements Command {
    private TextField tfSearch;
    private ObservableList<ExamsData> examsList;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private TableView<ExamsData> tabellaAppelli;

    /**
     * Costruttore che contiene i parametri per la ricerca delle prenotazioni
     *
     * @param tfSearch field per la barra di ricerca
     * @param examsList list degli appelli
     * @param databaseTeacherMenu database che richiama la query
     * @param tabellaAppelli tabella che mostra gli appelli
     */
    public SearchExam(TextField tfSearch, ObservableList<ExamsData> examsList,
                      DatabaseTeacherMenu databaseTeacherMenu, TableView<ExamsData> tabellaAppelli) {

        this.tfSearch = tfSearch;
        this.examsList = examsList;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.tabellaAppelli = tabellaAppelli;
    }

    /**
     * Metodo utilizzato per cercare gli appelli tramite barra di ricerca inserendo alcuni dati
     */
    @Override
    public void execute() {
        //trim per rimuovere spazi bianchi non necessari
        String searchExamData = tfSearch.getText().toLowerCase();

        if (searchExamData.isEmpty()){
            examsList = databaseTeacherMenu.getAllRecords();
            tabellaAppelli.setItems(examsList);
        }else{
            // Altrimenti, filtra gli appelli
            ObservableList<ExamsData> filteredListExams = FXCollections.observableArrayList();

            if (examsList != null){
                //Cerco inserendo l'id dell'esame o il nome dell'esame
                for (ExamsData exam : this.examsList){
                    if (String.valueOf(exam.getIdEsame()).contains(searchExamData) || exam.getNomeEsame().toLowerCase().contains(searchExamData)){
                        filteredListExams.add(exam);
                    }
                }
            }
            tabellaAppelli.setItems(filteredListExams);
        }
    }
}
