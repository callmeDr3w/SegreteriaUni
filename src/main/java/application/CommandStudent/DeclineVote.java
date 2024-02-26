package application.CommandStudent;

import application.Command;
import application.Database.Students.DatabaseStudentsMenu;
import application.Models.Utils.AlertUtil;
import application.Models.VotiData;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe utilizzata per rifiutare il voto di un esame conseguito
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DeclineVote implements Command {
    private TableView<VotiData> tabellaVoto;
    private ObservableList<VotiData> voteList;
    private DatabaseStudentsMenu databaseStudentsMenu;

    /**
     * Costruttore che contiene i parametri per rifiutare il voto
     *
     * @param tabellaVoto tabella che mostra i voti
     * @param voteList list dei voti
     * @param databaseStudentsMenu database che richiama la query
     */
    public DeclineVote(TableView<VotiData> tabellaVoto, ObservableList<VotiData> voteList, DatabaseStudentsMenu databaseStudentsMenu) {
        this.tabellaVoto = tabellaVoto;
        this.voteList = voteList;
        this.databaseStudentsMenu = databaseStudentsMenu;
    }

    /**
     * Metodo utilizzato per rifiutare un'esame conseguito
     */
    @Override
    public void execute() {
        VotiData selectedVote = tabellaVoto.getSelectionModel().getSelectedItem();

        if (selectedVote != null);
        //Recupero l'id voto
        Integer idVoto = selectedVote.getIdVoto();
        //Recupero della query per rifiutare un voto
        boolean success = DatabaseStudentsMenu.deleteVote(idVoto);

        if (success){
            voteList = databaseStudentsMenu.getAllVote();
            tabellaVoto.setItems(voteList);
            tabellaVoto.refresh();
        }else {
            AlertUtil.showErrorAlert("impossibile rifiutare, esame giá confermato");
        }
    }
}
