package application.CommandTeacher;

import application.Command;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.BookingExamsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Classe utilizzata per cercare le prenotazioni tramite barra di ricerca
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class SearchBooking implements Command {
    private TextField tfSearchPre;
    private ObservableList<BookingExamsData> bookingList;
    private DatabaseTeacherMenu databaseTeacherMenu;
    private TableView<BookingExamsData> tabellaPrenotazioni;

    /**
     * Costruttore che contiene i parametri per la ricerca delle prenotazioni
     *
     * @param tfSearchPre field per la barra di ricerca
     * @param bookingList list delle prenotazioni
     * @param databaseTeacherMenu database che richiama la query
     * @param tabellaPrenotazioni tabella che mostra le prenotazioni
     */
    public SearchBooking(TextField tfSearchPre, ObservableList<BookingExamsData> bookingList,
                         DatabaseTeacherMenu databaseTeacherMenu, TableView<BookingExamsData> tabellaPrenotazioni) {

        this.tfSearchPre = tfSearchPre;
        this.bookingList = bookingList;
        this.databaseTeacherMenu = databaseTeacherMenu;
        this.tabellaPrenotazioni = tabellaPrenotazioni;
    }

    /**
     * Metodo utilizzato per cercare le prenotazioni tramite barra di ricerca inserendo alcuni dati
     */
    @Override
    public void execute() {
        //trim per rimuovere spazi bianchi non necessari
        String searchBookData = tfSearchPre.getText().trim().toLowerCase();

        if (searchBookData.isEmpty()){
            bookingList = databaseTeacherMenu.getAllBooking();
            tabellaPrenotazioni.setItems(bookingList);
        }else{
            // Altrimenti, filtra gli appelli
            ObservableList<BookingExamsData> filteredListBook = FXCollections.observableArrayList();

            if (bookingList != null){
                //Cerco inserendo l'id della prenotazione o la matricola
                for (BookingExamsData booking : this.bookingList){
                    if (String.valueOf(booking.getIdPrenotazione()).contains(searchBookData) || String.valueOf(booking.getMatricolaStudente()).contains(searchBookData)){
                        filteredListBook.add(booking);
                    }
                }
            }
            tabellaPrenotazioni.setItems(filteredListBook);
        }
    }
}
