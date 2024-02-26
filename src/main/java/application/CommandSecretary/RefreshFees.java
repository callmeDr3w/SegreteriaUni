package application.CommandSecretary;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.FeesData;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe uutilizzata per il refresh della barra di ricerca delle tasse
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class RefreshFees implements Command {
    private TextField tfFees;
    private ObservableList<FeesData> feesList;
    private DatabaseSecretaryMenu databaseSecretaryMenu;
    private TableView<FeesData> tabellaFees;

    /**
     * Costruttore che contiene i parametri per il refersh
     *
     * @param tfFees field per barra di ricerca delle tasse
     * @param feesList list delle tasse
     * @param databaseSecretaryMenu database che richiama la query
     * @param tabellaFees tabella che mostra le tasse
     */
    public RefreshFees(TextField tfFees, ObservableList<FeesData> feesList,
                       DatabaseSecretaryMenu databaseSecretaryMenu, TableView<FeesData> tabellaFees) {

        this.tfFees = tfFees;
        this.feesList = feesList;
        this.databaseSecretaryMenu = databaseSecretaryMenu;
        this.tabellaFees = tabellaFees;
    }

    /**
     * Metodo utilizzato per il refresh
     */
    @Override
    public void execute() {
        tfFees.clear();
        feesList = databaseSecretaryMenu.getAllFees();
        tabellaFees.setItems(feesList);
    }
}
