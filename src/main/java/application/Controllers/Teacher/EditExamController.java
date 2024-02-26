package application.Controllers.Teacher;

import application.CommandTeacher.EditExam;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.ExamsData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe utilizzata per controllare la schermata di modifica degli appelli
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class EditExamController implements Initializable {

    @FXML
    private AnchorPane editExamForm;

    @FXML
    private ChoiceBox<String> boxEditNome;

    @FXML
    private DatePicker datePickerEditEx;

    @FXML
    private ChoiceBox<String> boxEditOrario;

    @FXML
    private TextField tfEditAula;

    @FXML
    private Button modificabtnEdit;

    private String[] examName = {"Programmazione I", "Programmazione II", "Programmazione III"};
    private String[] examTime = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",
            "13:30", "14:00", "14:30", "15:00", "15:30", "16:00"};

    private DatabaseTeacherMenu databaseTeacherMenu = new DatabaseTeacherMenu();
    private TableView<ExamsData> tabellaAppelli;

    /**
     * Metodo utilizzato per settare la tabella degli appelli
     *
     * @param tabellaAppelli tabella che mostra gli appelli
     */
    public void setTabellaAppelli(TableView<ExamsData> tabellaAppelli){
        this.tabellaAppelli = tabellaAppelli;
    }

    /**
     * Metodo utilizzato per il bottone modifica
     *
     * @param event evento che scaturisce l'azione del bottone
     * @throws IOException eccezione in caso di errore
     */
    @FXML
    protected void modifyBTNEdit(ActionEvent event) throws IOException {
        EditExam editExam = new EditExam(boxEditNome, boxEditOrario, tfEditAula,
                tabellaAppelli, datePickerEditEx, databaseTeacherMenu, modificabtnEdit);
        editExam.execute();
    }

    /**
     * Metodo per inizializzare la schermata ogni qual volta la si starta
     *
     * @param url indirizzo del file FXML che definisce la struttura dell'interfaccia utente
     * @param resourceBundle fornisce localizzazione o gestione delle risorse
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxEditNome.getItems().addAll(examName);
        boxEditOrario.getItems().addAll(examTime);


    }
}
