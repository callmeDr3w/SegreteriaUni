package application.CommandTeacher;

import application.Command;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.BookingExamsData;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe utilizzata per il refresh delle prenotazioni dalla barra di ricerca
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class RefreshBooking implements Command {
    private TextField tfSearchPre;
    private ObservableList<BookingExamsData> bookingList;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private TableView<BookingExamsData> tabellaPrenotazioni;

    /**
     * Costruttore che contiene i parametri per il refresh
     *
     * @param tfSearchPre field per la barra di ricerca
     * @param bookingList list delle prenotazioni
     * @param databaseTeacherMenu database che richiama la query
     * @param tabellaPrenotazioni tabella che mostra le prenotazioni
     */
    public RefreshBooking(TextField tfSearchPre, ObservableList<BookingExamsData> bookingList,
                          DatabaseTeacherMenu databaseTeacherMenu, TableView<BookingExamsData> tabellaPrenotazioni) {

        this.tfSearchPre = tfSearchPre;
        this.bookingList = bookingList;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.tabellaPrenotazioni = tabellaPrenotazioni;
    }

    /**
     * Metodo utilizzato per il refresh della barra di ricerca
     */
    @Override
    public void execute() {
        tfSearchPre.clear();
        bookingList = databaseTeacherMenu.getAllBooking();
        tabellaPrenotazioni.setItems(bookingList);
    }
}
