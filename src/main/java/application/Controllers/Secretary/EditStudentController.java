package application.Controllers.Secretary;

import application.CommandSecretary.EditStudent;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Models.StudentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe utilizzata per controllare la schermata di modifica dello studente
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class EditStudentController implements Initializable {

    @FXML
    private AnchorPane editForm;

    @FXML
    private TextField tfNomeEdit;

    @FXML
    private TextField tfCognEdit;

    @FXML
    private DatePicker datePickerEdit;

    @FXML
    private TextField tfResiEdit;

    @FXML
    private ChoiceBox<String> boxStudiEdit;

    @FXML
    private PasswordField tfPasswEdit;

    @FXML
    private Button modificabtnEdit;

    //inizializzo i piani di studio nella box
    private String[] course = {"Informatica", "Conduzione del Mezzo Navale", "Scienze Biologiche"};

    //istanza che richiama il database della segeretria
    private DatabaseSecretaryMenu databaseSecretaryMenu = new DatabaseSecretaryMenu();

    private TableView<StudentData> tabellaStudenti;

    /**
     * Metodo utilizzato per settare la tabella studenti
     *
     * @param tabellaStudenti tabella che mostra gli studenti
     */
    //setto tabella studenti per richiamarla nel controller che gestisce la modifica degli studenti
    public void setTabellaStudenti(TableView<StudentData> tabellaStudenti){
        this.tabellaStudenti = tabellaStudenti;
    }


    /**
     * Metodo utilizzato per il bottone modifica
     *
     * @param event evento che scaturisce l'azione del bottone
     * @throws IOException eccezione in caso di errore
     */
    @FXML
    protected void modifyBTNEdit(ActionEvent event) throws IOException {
            EditStudent editStudent = new EditStudent(tabellaStudenti, databaseSecretaryMenu,tfNomeEdit,
                    tfCognEdit, datePickerEdit, tfResiEdit,
                    boxStudiEdit, tfPasswEdit, modificabtnEdit);
            editStudent.execute();
    }


    /**
     * Metodo per inizializzare la schermata ogni qual volta la si starta
     *
     * @param url indirizzo del file FXML che definisce la struttura dell'interfaccia utente
     * @param resourceBundle fornisce localizzazione o gestione delle risorse
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxStudiEdit.getItems().addAll(course);

    }
}
