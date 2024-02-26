package application.CommandStudent;

import application.Command;
import application.Database.Students.DatabaseStudentsMenu;
import application.Models.FeesData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe utilizzata per il pagamento delle tasse
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class PayFees implements Command {
    private TableView<FeesData> tabellaFees;
    private DatabaseStudentsMenu databaseStudentsMenu;
    private ObservableList<FeesData> feesList;
    private Long matricola;

    /**
     * Costruttore che contiene i parametri per il pagamento
     *
     * @param tabellaFees tabella che mostra le tasse
     * @param databaseStudentsMenu database che richiama la query
     * @param feesList list delle tasse
     * @param matricola matricola dello studente
     */
    public PayFees(TableView<FeesData> tabellaFees, DatabaseStudentsMenu databaseStudentsMenu, ObservableList<FeesData> feesList, Long matricola) {
        this.tabellaFees = tabellaFees;
        this.databaseStudentsMenu = databaseStudentsMenu;
        this.feesList = feesList;
        this.matricola = matricola;
    }

    /**
     * Metodo utilizzato per il pagamento delle tasse
     */
    @Override
    public void execute() {
        FeesData selectedFees = tabellaFees.getSelectionModel().getSelectedItem();

        if (selectedFees != null){
            //Recupero della matricola
            Integer idTassa = selectedFees.getIdTassa();
            //Recupero della query per l'update delle tasse
            boolean success = databaseStudentsMenu.updateFees(idTassa);

            if (success){
                feesList = databaseStudentsMenu.getAllStudentFees(matricola);
                tabellaFees.setItems(feesList);
                tabellaFees.refresh();
                AlertUtil.showSuccessAlert("pagamento tassa andato a buon fine");
            }else {
                AlertUtil.showErrorAlert("Tassa giá pagata");
            }
        }
    }
}