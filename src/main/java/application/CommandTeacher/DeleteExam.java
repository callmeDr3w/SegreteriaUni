package application.CommandTeacher;

import application.Command;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.ExamsData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe utilizzata per eliminare un appello
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DeleteExam implements Command {
    private TableView<ExamsData> tabellaAppelli;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private ObservableList<ExamsData> examsList;

    /**
     * Costruttore che contiene i parametri per eliminare un appello
     *
     * @param tabellaAppelli tabella che mostra gli appelli
     * @param databaseTeacherMenu database che richiama la query
     * @param examsList list degli appelli
     */
    public DeleteExam(TableView<ExamsData> tabellaAppelli, DatabaseTeacherMenu databaseTeacherMenu, ObservableList<ExamsData> examsList) {
        this.tabellaAppelli = tabellaAppelli;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.examsList = examsList;
    }

    /**
     * Metodo utilizzato per eliminare un appello
     */
    @Override
    public void execute() {
        ExamsData selectedExam = tabellaAppelli.getSelectionModel().getSelectedItem();

        if (selectedExam != null){
            //Recupero l'id dell'esame
            Integer idEsame = selectedExam.getIdEsame();
            //Richiamo della query per eliminare l'appello
            boolean success = databaseTeacherMenu.deleteRecord(idEsame);

            if (success){
                examsList = databaseTeacherMenu.getAllRecords();
                tabellaAppelli.setItems(examsList);
                tabellaAppelli.refresh();
                AlertUtil.showSuccessAlert("appello eliminato con successo");

            }else {
                AlertUtil.showErrorAlert("errore durante l'eliminazione dell'esame");
            }
        }
    }
}
