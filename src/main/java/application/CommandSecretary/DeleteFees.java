package application.CommandSecretary;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.FeesData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe utilizzata per eliminare una tassa
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DeleteFees implements Command {
    private TableView<FeesData> tabellaFees;
    private DatabaseSecretaryMenu databaseSecretaryMenu;
    private ObservableList<FeesData> feesList;

    /**
     * Costruttore che contiene i parametri per eliminare una tassa
     *
     * @param tabellaFees tabella che mostra le tasse
     * @param databaseSecretaryMenu database che richiama la query
     * @param feesList list delle tasse
     */
    public DeleteFees(TableView<FeesData> tabellaFees,
                      DatabaseSecretaryMenu databaseSecretaryMenu, ObservableList<FeesData> feesList) {

        this.tabellaFees = tabellaFees;
        this.databaseSecretaryMenu = databaseSecretaryMenu;
        this.feesList = feesList;
    }

    /**
     * Metodo utilizzato per eliminare la tassa
     */
    @Override
    public void execute() {
        FeesData selectedFees = tabellaFees.getSelectionModel().getSelectedItem();

        if (selectedFees != null){
            Integer idTassa = selectedFees.getIdTassa();

            boolean success = databaseSecretaryMenu.deleteFees(idTassa);

            if (success){
                feesList = databaseSecretaryMenu.getAllFees();
                tabellaFees.setItems(feesList);
                tabellaFees.refresh();
                AlertUtil.showSuccessAlert("tassa eliminata correttamente");
            }else {
                AlertUtil.showErrorAlert("errore durante l'eliminazione della tassa");
            }
        }
    }
}
