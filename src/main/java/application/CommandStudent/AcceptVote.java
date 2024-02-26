package application.CommandStudent;

import application.Command;
import application.Database.Students.DatabaseStudentsMenu;
import application.Models.Utils.AlertUtil;
import application.Models.VotiData;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe utilizzata per accettare il voto dell'esame effettuato
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class AcceptVote implements Command {
    private TableView<VotiData> tabellaVoto;
    private ObservableList<VotiData> voteList;
    private DatabaseStudentsMenu databaseStudentsMenu;

    /**
     * Costruttore che contiene i parametri per la conferma del voto
     *
     * @param tabellaVoto tabella che mostra i voti
     * @param voteList list dei voti
     * @param databaseStudentsMenu database che richiama la query
     */
    public AcceptVote(TableView<VotiData> tabellaVoto, ObservableList<VotiData> voteList, DatabaseStudentsMenu databaseStudentsMenu) {
        this.tabellaVoto = tabellaVoto;
        this.voteList = voteList;
        this.databaseStudentsMenu = databaseStudentsMenu;
    }

    /**
     * Metodo utilizzato per accettare il voto dell'esame conseguito
     */
    @Override
    public void execute() {
        VotiData selectedVote = tabellaVoto.getSelectionModel().getSelectedItem();

        if (selectedVote != null){
            //Recupero l'id del voto
            Integer idVoto = selectedVote.getIdVoto();
            //Richiamo della query per l'update del voto
            boolean success = DatabaseStudentsMenu.updateVote(idVoto);

            if (success){
                voteList = databaseStudentsMenu.getAllVote();
                tabellaVoto.setItems(voteList);
                tabellaVoto.refresh();
                AlertUtil.showSuccessAlert("esame confermato");
            }else {
                AlertUtil.showErrorAlert("esame giá confermato");
            }
        }
    }
}