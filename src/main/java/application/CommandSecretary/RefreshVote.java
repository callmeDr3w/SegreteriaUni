package application.CommandSecretary;

import application.Command;
import application.Database.Students.DatabaseStudentsMenu;
import application.Models.VotiData;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe uutilizzata per il refresh della barra di ricerca dei voti
 *
 * @author andreaaristarco
 * @version 1.0
 */

public class RefreshVote implements Command{
    private TextField tfVote;
    private ObservableList<VotiData> voteList;
    private DatabaseStudentsMenu databaseStudentsMenu;
    private TableView<VotiData> tabellaVote;

    /**
     * Costruttore che contiene i parametri per il refresh della barra di ricerca
     *
     * @param tfVote field barra di ricerca voti
     * @param voteList list dei voti
     * @param databaseStudentsMenu database che richiama la query
     * @param tabellaVote tabella che mostra i voti
     */
    public RefreshVote(TextField tfVote, ObservableList<VotiData> voteList,
                       DatabaseStudentsMenu databaseStudentsMenu, TableView<VotiData> tabellaVote) {

        this.tfVote = tfVote;
        this.voteList = voteList;
        this.databaseStudentsMenu = databaseStudentsMenu;
        this.tabellaVote = tabellaVote;
    }

    /**
     * Metodo utilizzato per il refresh delle barra dei voti
     */
    @Override
    public void execute() {
        tfVote.clear();
        voteList = databaseStudentsMenu.getAllVote();
        tabellaVote.setItems(voteList);
    }
}
