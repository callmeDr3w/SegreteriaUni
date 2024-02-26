package application.CommandSecretary;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.FeesData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe utilizzata per cercare le tasse di uno studente specifico
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class SearchFees implements Command {
    private TextField tfFees;
    private ObservableList<FeesData> feesList;
    private DatabaseSecretaryMenu databaseSecretaryMenu;
    private TableView<FeesData> tabellaFees;

    /**
     * Costruttore che contiene i parametri per ricercare nella barra di ricerca le tasse
     *
     * @param tfFees field barra di ricerca tasse
     * @param feesList list delle tasse
     * @param databaseSecretaryMenu database che richiama la query
     * @param tabellaFees tabella che mostra le tasse
     */
    public SearchFees(TextField tfFees, ObservableList<FeesData> feesList, DatabaseSecretaryMenu databaseSecretaryMenu, TableView<FeesData> tabellaFees) {
        this.tfFees = tfFees;
        this.feesList = feesList;
        this.databaseSecretaryMenu = databaseSecretaryMenu;
        this.tabellaFees = tabellaFees;
    }

    /**
     * Metodo utilizzato per cercare tra le tasse inserendo la matricola dello studente
     */
    @Override
    public void execute() {
        //trim per rimuovere spazi bianchi non necessari
        String searchStudents = tfFees.getText().trim().toLowerCase();

        if (searchStudents.isEmpty()) {
            // Se il campo di ricerca è vuoto, mostra tutte le tasse
            feesList = databaseSecretaryMenu.getAllFees();
            tabellaFees.setItems(feesList);
        } else {
            // Altrimenti, filtra gli studenti in base alla matricola
            ObservableList<FeesData> filteredList = FXCollections.observableArrayList();

            //Verifico se la feesList non è nulla
            if (feesList != null) {
                for (FeesData fees : this.feesList) {
                    if (String.valueOf(fees.getMatricolaTassa()).contains(searchStudents)) {
                        filteredList.add(fees);
                    }
                }
            }

            // Aggiorna la TableView con la lista filtrata
            tabellaFees.setItems(filteredList);
            tabellaFees.refresh();
        }
    }
}

