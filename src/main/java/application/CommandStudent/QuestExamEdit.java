package application.CommandStudent;

import application.Command;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Database.Students.DatabaseStudentsMenu;
import application.Models.LongSession;
import application.Models.QuestData;
import application.Models.Utils.AlertUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Classe utilizzata per compilare il questionario
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class QuestExamEdit implements Command {
    private ChoiceBox<String> boxQuest1;
    private ChoiceBox<String> boxQuest2;
    private ChoiceBox<String> boxQuest3;
    private TableView<QuestData> tabellaQuest;
    private DatabaseStudentsMenu databaseStudentsMenu;
    private DatabaseSecretaryMenu databaseSecretaryMenu;
    private ObservableList<QuestData> questList;
    private Button inseriscibtn;

    /**
     * Costruttore che contiene i parametri per compilare il questionario
     *
     * @param boxQuest1 box risposte domanda 1
     * @param boxQuest2 box risposte domanda 2
     * @param boxQuest3 box risposte domanda 3
     * @param tabellaQuest tabella che mostra i questionari
     * @param databaseStudentsMenu database che richiama la query
     * @param databaseSecretaryMenu database che richiama la query
     * @param questList list dei questionari
     * @param inseriscibtn bottone per inserire le risposte
     */
    public QuestExamEdit(ChoiceBox<String> boxQuest1, ChoiceBox<String> boxQuest2, ChoiceBox<String> boxQuest3,
                         TableView<QuestData> tabellaQuest, DatabaseStudentsMenu databaseStudentsMenu, DatabaseSecretaryMenu databaseSecretaryMenu,
                         ObservableList<QuestData> questList, Button inseriscibtn) {

        this.boxQuest1 = boxQuest1;
        this.boxQuest2 = boxQuest2;
        this.boxQuest3 = boxQuest3;
        this.tabellaQuest = tabellaQuest;
        this.databaseStudentsMenu = databaseStudentsMenu;
        this.databaseSecretaryMenu = databaseSecretaryMenu;
        this.questList = questList;
        this.inseriscibtn = inseriscibtn;
    }

    /**
     * Metodo utilizzato per compilare il questionario inserendo le risposte alle varie domande
     */
    @Override
    public void execute() {
        if (boxQuest1.getItems().isEmpty() || boxQuest2.getItems().isEmpty() || boxQuest3.getItems().isEmpty()){
            AlertUtil.showErrorAlert("compila tutti i campi");
        }else {
            QuestData selectedQuest = tabellaQuest.getSelectionModel().getSelectedItem();
            //Recupero l'id del questionario, la matricola di chi l'ha effettuato, il nome dell'esame scelto e le risposte controllando lo stato se effettuato o meno
            Integer idQuest = selectedQuest.getIdQuest();
            Long matricolaDomanda = LongSession.getInstance().getId();
            String esameDomanda = selectedQuest.getNomeExQuest();
            String domanda1 = boxQuest1.getValue();
            String domanda2 = boxQuest2.getValue();
            String domanda3 = boxQuest3.getValue();
            Boolean effettuato = selectedQuest.getEffettuato();

            //Recupero della query per aggiungere i dati del questionario
            boolean success = databaseStudentsMenu.addQuest(idQuest, matricolaDomanda, esameDomanda, domanda1, domanda2, domanda3);

            if (success){
                //Richiamo della query per aggiornare lo stato del questionario
                databaseStudentsMenu.updateQuest(idQuest, effettuato);
                questList = databaseSecretaryMenu.getAllQuest();
                tabellaQuest.setItems(questList);
                tabellaQuest.refresh();
                AlertUtil.showSuccessAlert("questionario completato");
                Stage currentStage = (Stage) inseriscibtn.getScene().getWindow();
                currentStage.close();
            }else {
                AlertUtil.showErrorAlert("questionario non completato");
            }
        }
    }
}
