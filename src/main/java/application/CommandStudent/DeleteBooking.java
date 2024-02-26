package application.CommandStudent;

import application.Command;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.ResultJoin;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe utilizzata per cancellare una prenotazione di un esame
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DeleteBooking implements Command {
    private TableView<ResultJoin> tabellaPre;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private ObservableList<ResultJoin> joinList;

    /**
     * Costruttore che contiene i parametri per cancellare una prenotazione
     *
     * @param tabellaPre tabella che mostra le prenotazioni
     * @param databaseTeacherMenu database che richiama la query
     * @param joinList list del join tra tabella voti tra prenotazione ed esame
     */
    public DeleteBooking(TableView<ResultJoin> tabellaPre, DatabaseTeacherMenu databaseTeacherMenu, ObservableList<ResultJoin> joinList) {
        this.tabellaPre = tabellaPre;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.joinList = joinList;
    }

    /**
     * Classe utilizzata per eliminare la prenotazione di un esame
     */
    @Override
    public void execute() {
        ResultJoin selectedJoin = tabellaPre.getSelectionModel().getSelectedItem();

        if (selectedJoin != null){
            //Recupero l'id della prenotazione
            Integer idPreJoin = selectedJoin.getIdPreJoin();
            //Recupero della query per eliminare la prenotazione
            boolean success = databaseTeacherMenu.deleteRecordPre(idPreJoin);

            if (success){
                tabellaPre.setItems(joinList);
                tabellaPre.refresh();
                AlertUtil.showSuccessAlert("prenotazione eliminata");
            }else {
                AlertUtil.showErrorAlert("errore durante l'eliminazione della prenotazione");
            }
        }
    }
}
