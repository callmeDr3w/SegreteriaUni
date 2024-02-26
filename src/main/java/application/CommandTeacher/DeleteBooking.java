package application.CommandTeacher;

import application.Command;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.BookingExamsData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Classe utilizzata per eliminare una prenotazione
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class DeleteBooking implements Command {
    private TableView<BookingExamsData> tabellaPrenotazioni;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private ObservableList<BookingExamsData> bookingList;

    /**
     * Costruttore che contiene i parametri per eliminare la prenotazione
     *
     * @param tabellaPrenotazioni tabella che mostra le prenotazioni
     * @param databaseTeacherMenu database che richiama la query
     * @param bookingList list delle prenotazioni
     */
    public DeleteBooking(TableView<BookingExamsData> tabellaPrenotazioni, DatabaseTeacherMenu databaseTeacherMenu, ObservableList<BookingExamsData> bookingList) {
        this.tabellaPrenotazioni = tabellaPrenotazioni;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.bookingList = bookingList;
    }

    /**
     * Metodo utilizzato per eliminare una prenotazione
     */
    @Override
    public void execute() {
        BookingExamsData selectedBooking = tabellaPrenotazioni.getSelectionModel().getSelectedItem();

        if (selectedBooking != null){
            //Recupero l'id della prenotazione
            Integer idPrenotazione = selectedBooking.getIdPrenotazione();
            //Recupero della query per eliminare una prenotazione
            boolean success = databaseTeacherMenu.deleteRecordPre(idPrenotazione);

            if (success){
                bookingList = databaseTeacherMenu.getAllBooking();
                tabellaPrenotazioni.setItems(bookingList);
                tabellaPrenotazioni.refresh();
                AlertUtil.showSuccessAlert("prenotazione eliminata con successo");
            }else {
                AlertUtil.showErrorAlert("errore durante l'eliminazione della prenotazione");
            }
        }
    }
}
