package application.CommandTeacher;

import application.Command;
import application.Controllers.Teacher.EditExamController;
import application.Models.ExamsData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe utilizzata per cambiare la schermata di modifica dell'appello
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class ModifyExam implements Command {
    private TableView<ExamsData> tabellaAppelli;

    /**
     * Costruttore che contiene i parametri per cambiare schermata
     *
     * @param tabellaAppelli tabella che mostra gli appelli
     */
    public ModifyExam(TableView<ExamsData> tabellaAppelli) {
        this.tabellaAppelli = tabellaAppelli;
    }

    /**
     * Metodo utilizzato per cambiare schermata
     */
    @Override
    public void execute() {
        try {
            ExamsData selectedExam = tabellaAppelli.getSelectionModel().getSelectedItem();

            if (selectedExam != null) {
                //Recupero l'id dell'esame
                Integer idEsame = selectedExam.getIdEsame();
                tabellaAppelli.setDisable(true);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditExam.fxml"));
                Parent root = loader.load();
                //Cambio il controller per gestire la nuova schermata
                EditExamController editExamController = loader.getController();

                editExamController.setTabellaAppelli(tabellaAppelli);

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("MODIFICA ESAME");
                stage.setMinWidth(550);
                stage.setMinHeight(550);
                stage.setScene(scene);

                //Imposto la modalità della nuova finestra
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(tabellaAppelli.getScene().getWindow());

                //Gestisco l'evento di chiusura della nuova finestra
                stage.setOnHidden(e -> {
                    // Riabilito gli elementi interattivi nella finestra corrente quando la nuova finestra è chiusa
                    tabellaAppelli.setDisable(false);

                });

                // Mostro la nuova finestra
                stage.show();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
