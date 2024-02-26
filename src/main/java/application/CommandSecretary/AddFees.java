package application.CommandSecretary;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.FeesData;
import application.Models.StudentData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe utilizzata per la creazione delle tasse
 *
 * @author Andrea Aristarco
 * @version 1.0
 */
public class AddFees implements Command {
    private TableView<StudentData> tabellaStudenti;
    private DatabaseSecretaryMenu databaseSecretaryMenu;
    private ObservableList<FeesData> feesList;
    private TableView<FeesData> tabellaFees;

    /**
     * Costruttore che contiene i dati per creare le tasse
     *
     * @param tabellaStudenti tabella studenti
     * @param databaseSecretaryMenu database che richiama la query
     * @param feesList list delle tasse
     * @param tabellaFees tabella che mostra le tasse
     */
    public AddFees(TableView<StudentData> tabellaStudenti, DatabaseSecretaryMenu databaseSecretaryMenu,
                   ObservableList<FeesData> feesList, TableView<FeesData> tabellaFees) {

        this.tabellaStudenti = tabellaStudenti;
        this.databaseSecretaryMenu = databaseSecretaryMenu;
        this.feesList = feesList;
        this.tabellaFees = tabellaFees;
    }

    /**
     * Metodo utilizzato per aggiungere le tasse
     */
    @Override
    public void execute() {
        StudentData selectedStudents = tabellaStudenti.getSelectionModel().getSelectedItem();

        if (selectedStudents != null){
            Integer idTassa = Math.toIntExact(DatabaseSecretaryMenu.generateRandomNumber(6));
            Long matricola = selectedStudents.getMatricola();

            boolean success = databaseSecretaryMenu.insertFees(idTassa, matricola);

            if (success){
                feesList = databaseSecretaryMenu.getAllFees();
                tabellaFees.setItems(feesList);
                tabellaFees.refresh();
                AlertUtil.showSuccessAlert("tasse inserite correttamente");
            }else {
                AlertUtil.showErrorAlert("errore durante l'inserimento delle tasse");
            }
        }
    }
}
