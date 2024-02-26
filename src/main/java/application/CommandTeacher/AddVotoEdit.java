package application.CommandTeacher;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Database.Students.DatabaseStudentsMenu;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.BookingExamsData;
import application.Models.ExamsData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Classe utilizzata per aggiungere il voto ad un esame
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class AddVotoEdit implements Command {
    private ChoiceBox<Integer> boxEditVoto;
    private TableView<BookingExamsData> tabellaPrenotazioni;
    private DatabaseStudentsMenu databaseStudentsMenu;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private Button aggiungibtnVoto;

    private TableView<ExamsData> tabellaAppelli;

    /**
     * Costruttore che contiene i parametri per inserire il voto
     *
     * @param boxEditVoto box con l'elenco dei voti
     * @param tabellaPrenotazioni tabella che mostra le prenotazioni
     * @param databaseStudentsMenu database che richiama la query
     * @param databaseTeacherMenu database che richiama la query
     * @param aggiungibtnVoto bottone per aggiungere il voto
     */
    public AddVotoEdit(ChoiceBox<Integer> boxEditVoto, TableView<BookingExamsData> tabellaPrenotazioni,
                       DatabaseStudentsMenu databaseStudentsMenu, DatabaseTeacherMenu databaseTeacherMenu, Button aggiungibtnVoto) {

        this.boxEditVoto = boxEditVoto;
        this.tabellaPrenotazioni = tabellaPrenotazioni;
        this.databaseStudentsMenu = databaseStudentsMenu;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.aggiungibtnVoto = aggiungibtnVoto;
    }

    /**
     * Metodo utilizzato per settare la tabella appelli
     *
     * @param tabellaAppelli tabella appelli
     */
    public void setTabellaAppelli(TableView<ExamsData> tabellaAppelli){
        this.tabellaAppelli = tabellaAppelli;
    }

    /**
     * Metodo utilizzato per inserire il voto ad un esame
     */
    @Override
    public void execute() {
        if (boxEditVoto.getItems().isEmpty()){
            AlertUtil.showErrorAlert("inserisci voto correttamente");
        }else{
            BookingExamsData selectedBooking = tabellaPrenotazioni.getSelectionModel().getSelectedItem();
            //Recupero l'id della prenotazione, l'id dell'esame, la matricola e il voto
            Integer idPreVo = selectedBooking.getIdPrenotazione();
            Integer idEsameVoto = selectedBooking.getIdEsamePre();
            Long matricolaVoto = selectedBooking.getMatricolaStudente();
            String nomeExVoto = ExamsData.getNomeEsame();
            Integer voto = boxEditVoto.getValue();
            Boolean conferma = false;
            Integer idPrenotazione = selectedBooking.getIdPrenotazione();

            //Richiamo della query per aggiungere il voto
           boolean success = databaseStudentsMenu.addVote(((int) DatabaseSecretaryMenu.generateRandomNumber(3)), idPreVo, idEsameVoto, nomeExVoto, matricolaVoto, voto, conferma);

            if (success) {
                if (voto == 0) {
                    //Se il voto é 0, allora é bocciato
                    System.out.println("bocciato");
                    AlertUtil.showSuccessAlert("voto aggiunto");
                    databaseTeacherMenu.deleteRecordPre(idPrenotazione);
                } else if (voto == 31) {
                    //Se il voto é 31, allora vale 30 e lode
                    System.out.println("30 e lode");
                    AlertUtil.showSuccessAlert("voto aggiunto");
                } else{
                    System.out.println("il tuo voto é " + voto);
                    AlertUtil.showSuccessAlert("voto aggiunto");
                }
                //Richiamo della query per cancellare la prenotazione quando il voto é stato inserito
                boolean deleteSuccess = databaseTeacherMenu.deleteRecordPre(idPreVo);
                if (deleteSuccess){
                    AlertUtil.showSuccessAlert("prenotazione cancellata");
                }else{
                    AlertUtil.showErrorAlert("errore durante la cancellazione della prenotazione");
                }
                Stage currentStage = (Stage) aggiungibtnVoto.getScene().getWindow();
                currentStage.close();

                ObservableList<BookingExamsData> bookingList = databaseTeacherMenu.getAllBooking();
                tabellaPrenotazioni.setItems(bookingList);
                tabellaPrenotazioni.refresh();
            }else {
                AlertUtil.showErrorAlert("Errore durante l'operazione");
            }
        }
    }
}
