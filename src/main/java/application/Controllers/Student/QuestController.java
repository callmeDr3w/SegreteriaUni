package application.Controllers.Student;

import application.CommandStudent.QuestExamEdit;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Database.Students.DatabaseStudentsMenu;
import application.Models.QuestData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classse utilizzata per controllare la schermata per la compilazione dei questionari
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class QuestController implements Initializable {

    @FXML
    private AnchorPane DashboardQuest;

    @FXML
    private ChoiceBox<String> boxQuest1;

    @FXML
    private ChoiceBox<String> boxQuest2;

    @FXML
    private ChoiceBox<String> boxQuest3;

    @FXML
    private Button inseriscibtn;

    //inizializzo le risposte nel box
    private String[] firstQuest = {"buone", "ottime", "scarse"};

    //inizializzo le risposte nel box
    private String[] secondQuest = {"si", "no", "non proprio"};

    //inizializzo le risposte nel box
    private String[] thirdQuest = {"si", "no", "abbastanza"};

    private DatabaseStudentsMenu databaseStudentsMenu = new DatabaseStudentsMenu();
    private DatabaseSecretaryMenu databaseSecretaryMenu = new DatabaseSecretaryMenu();
    private TableView<QuestData> tabellaQuest;
    private ObservableList<QuestData> questList;

    /**
     * Metodo utilizzato per settare la tabella dei questionari
     *
     * @param tabellaQuest tabella che mostra i questionari
     */
    public void setTabellaQuest(TableView<QuestData> tabellaQuest){
        this.tabellaQuest = tabellaQuest;
    }

    /**
     * Metodo utilizzato per il bottone inserisci
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void insertBTN(ActionEvent event){
        QuestExamEdit questExamEdit = new QuestExamEdit(boxQuest1, boxQuest2, boxQuest3,
                tabellaQuest, databaseStudentsMenu, databaseSecretaryMenu,
                questList, inseriscibtn);
        questExamEdit.execute();
    }

    /**
     * Metodo per inizializzare la schermata ogni qual volta la si starta
     *
     * @param url indirizzo del file FXML che definisce la struttura dell'interfaccia utente
     * @param resourceBundle fornisce localizzazione o gestione delle risorse
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxQuest1.getItems().addAll(firstQuest);
        boxQuest2.getItems().addAll(secondQuest);
        boxQuest3.getItems().addAll(thirdQuest);

    }
}
