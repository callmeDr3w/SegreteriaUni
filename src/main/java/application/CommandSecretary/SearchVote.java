package application.CommandSecretary;

import application.Command;
import application.Database.Students.DatabaseStudentsMenu;
import application.Models.VotiData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe utilizzata per cercare i voti tramite barra di ricerca
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class SearchVote implements Command {
    private TextField tfVote;
    private ObservableList<VotiData> voteList;
    private DatabaseStudentsMenu databaseStudentsMenu;
    private TableView<VotiData> tabellaVote;

    /**
     * Costruttore che contiene i parametri per la ricerca
     *
     * @param tfVote field barra di ricerca dei voti
     * @param voteList list dei voti
     * @param databaseStudentsMenu database che richiama la query
     * @param tabellaVote tabella che mostra i voti
     */
    public SearchVote(TextField tfVote, ObservableList<VotiData> voteList,
                      DatabaseStudentsMenu databaseStudentsMenu, TableView<VotiData> tabellaVote) {

        this.tfVote = tfVote;
        this.voteList = voteList;
        this.databaseStudentsMenu = databaseStudentsMenu;
        this.tabellaVote = tabellaVote;
    }

    /**
     * Metodo utilizzato per la ricerca dei voti inserendo id del voto, nome esame o matricola nella barra di ricerca
     */
    @Override
    public void execute() {
        //trim per rimuovere spazi bianchi non necessari
        String searchStudents = tfVote.getText().trim().toLowerCase();

        if (searchStudents.isEmpty()) {
            // Se il campo di ricerca è vuoto, mostra tutti gli studenti
            voteList = databaseStudentsMenu.getAllVote();
            tabellaVote.setItems(voteList);
        } else {
            // Altrimenti, filtra gli studenti in base all'id, nome esame o matricola
            ObservableList<VotiData> filteredList = FXCollections.observableArrayList();

            //Verifico se la voteList non è nulla
            if (voteList != null) {
                for (VotiData vote : this.voteList) {
                    if (String.valueOf(vote.getIdVoto()).contains(searchStudents) || vote.getNomeExVoto().toLowerCase().contains(searchStudents) || String.valueOf(vote.getMatricolaVoto()).contains(searchStudents)) {
                        filteredList.add(vote);
                    }
                }
            }

            // Aggiorna la TableView con la lista filtrata
            tabellaVote.setItems(filteredList);
            tabellaVote.refresh();
        }
    }
}
