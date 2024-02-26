package application.CommandStudent;

import application.Command;
import application.Controllers.Student.QuestController;
import application.Models.QuestData;
import application.Models.Utils.AlertUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe utilizzata per mostrare la schermata della compilazione dei questionari
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class QuestExam implements Command {
    private TableView<QuestData> tabellaQuest;

    /**
     * Costruttore che contiene i parametri per il cambio scena del questionario
     *
     * @param tabellaQuest tabella che mostra i questionari
     */
    public QuestExam(TableView<QuestData> tabellaQuest) {
        this.tabellaQuest = tabellaQuest;
    }

    /**
     * Metodo utilizzato per mostrare la scena della compilazione dei questionari
     */
    @Override
    public void execute() {
        QuestData selectedQuest = tabellaQuest.getSelectionModel().getSelectedItem();

        try {
            //Controllo se il questionario sia giá stato effettuato
            if (selectedQuest != null && !selectedQuest.getEffettuato()) {
                //Recupero l'id del questionario
                Integer idQuest = selectedQuest.getIdQuest();
                tabellaQuest.setDisable(true);
                //Carico la nuona scena
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditQuest.fxml"));
                Parent root = loader.load();

                QuestController questController = loader.getController();

                questController.setTabellaQuest(tabellaQuest);

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("QUESTIONARIO");
                stage.setMinWidth(500);
                stage.setMinHeight(500);
                stage.setScene(scene);

                //Imposto la modalità della nuova finestra
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(tabellaQuest.getScene().getWindow());

                //Gestisco l'evento di chiusura della nuova finestra
                stage.setOnHidden(e -> {
                    // Riabilito gli elementi interattivi nella finestra corrente quando la nuova finestra è chiusa
                    tabellaQuest.setDisable(false);

                });

                // Mostro la nuova finestra
                stage.show();
            }else {
                AlertUtil.showErrorAlert("questionario giá compilato");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
