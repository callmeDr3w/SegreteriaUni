package application.Controllers.Teacher;

import application.CommandTeacher.AddVotoEdit;
import application.Database.Students.DatabaseStudentsMenu;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.BookingExamsData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe utilizzata per controllare la schermata di aggiunta del voto
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class EditVotoController implements Initializable {

    @FXML
    private AnchorPane editVotoForm;

    @FXML
    private ChoiceBox<Integer> boxEditVoto;

    @FXML
    private Button aggiungibtnVoto;

    private Integer[] votoEsame = {0, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

    private DatabaseTeacherMenu databaseTeacherMenu = new DatabaseTeacherMenu();

    private DatabaseStudentsMenu databaseStudentsMenu = new DatabaseStudentsMenu();

    private TableView<BookingExamsData> tabellaPrenotazioni;

    /**
     * Metodo utilizzato per settare la tabella delle prenotazioni
     *
     * @param tabellaPrenotazioni tabella che mostra le prenotazioni
     */
    public void setTabellaPrenotazioni(TableView<BookingExamsData> tabellaPrenotazioni){
        this.tabellaPrenotazioni = tabellaPrenotazioni;
    }

    /**
     * Metodo utilizzato per il bottone aggiungi
     *
     * @param event evento che scaturisce l'azione del bottone
     * @throws IOException eccezione in caso di errore
     */
    @FXML
    protected void addBTNVoto(ActionEvent event) throws IOException{
        AddVotoEdit addVotoEdit = new AddVotoEdit(boxEditVoto, tabellaPrenotazioni, databaseStudentsMenu,
                databaseTeacherMenu, aggiungibtnVoto);
        addVotoEdit.execute();
    }

    /**
     * Metodo per inizializzare la schermata ogni qual volta la si starta
     *
     * @param url indirizzo del file FXML che definisce la struttura dell'interfaccia utente
     * @param resourceBundle fornisce localizzazione o gestione delle risorse
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxEditVoto.getItems().addAll(votoEsame);
    }
}
