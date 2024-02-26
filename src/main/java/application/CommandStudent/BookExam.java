package application.CommandStudent;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Database.Students.DatabaseStudentsMenu;
import application.Models.ExamsData;
import application.Models.LongSession;
import application.Models.ResultJoin;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe utilizzata per prenotarsi ad un esame
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class BookExam implements Command {
    private TableView<ExamsData> tabellaAppelli;
    private DatabaseStudentsMenu databaseStudentsMenu;
    private TableView<ResultJoin> tabellaPre;

    /**
     * Costruttore che contiene i parametri per prenotare un esame
     *
     * @param tabellaAppelli tabella che mostra gli appelli
     * @param databaseStudentsMenu database che richiama la query
     * @param tabellaPre tabella che mostra le prenotazioni
     */
    public BookExam(TableView<ExamsData> tabellaAppelli, DatabaseStudentsMenu databaseStudentsMenu, TableView<ResultJoin> tabellaPre) {
        this.tabellaAppelli = tabellaAppelli;
        this.databaseStudentsMenu = databaseStudentsMenu;
        this.tabellaPre = tabellaPre;
    }

    /**
     * Metodo utilizzato per prenotarsi ad un esame
     * Prenotandosi ad un'esame creo l'id della prenotazione e lo mostro nella tabella per tenere traccia delle prenotazioni
     */
    @Override
    public void execute() {
        ExamsData selectedExam = tabellaAppelli.getSelectionModel().getSelectedItem();

        System.out.println(selectedExam.getNomeEsame());
        //Recupero id esame e matricola (richiamo il singleton per recuperare l'istanza della matricola)
        Integer idEsame = selectedExam.getIdEsame();
        Long matricola = LongSession.getInstance().getId();
        System.out.println(idEsame.toString() + matricola.toString());

        //Recupero della query per prenotare l'esame
        boolean success = DatabaseStudentsMenu.bookExam(((int) DatabaseSecretaryMenu.generateRandomNumber(5)), idEsame, matricola);
        if (success) {
            ObservableList<ResultJoin> joinList = databaseStudentsMenu.getAllBookingJoin();
            tabellaPre.setItems(joinList);
            tabellaPre.refresh();
            AlertUtil.showSuccessAlert("esame prenotato");
        } else {
            AlertUtil.showErrorAlert("esame giá prenotato");
        }
    }
}
