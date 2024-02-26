package application.CommandTeacher;

import application.Command;
import application.Controllers.Teacher.EditVotoController;
import application.Models.BookingExamsData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe utilizzata per cambiare schermata utile all'aggiunta del voto
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class AddVoto implements Command {
    private TableView<BookingExamsData> tabellaPrenotazioni;

    /**
     * Costruttore che contiene i parametri per il cambio della schermata
     *
     * @param tabellaPrenotazioni tabella che mostra le prenotazioni
     */
    public AddVoto(TableView<BookingExamsData> tabellaPrenotazioni) {
        this.tabellaPrenotazioni = tabellaPrenotazioni;
    }

    /**
     * Metodo utilizzato per cambiare schermata e passare all'inserimento del voto
     */
    @Override
    public void execute() {
        try {
            BookingExamsData selectedExamVoto = tabellaPrenotazioni.getSelectionModel().getSelectedItem();

            if (selectedExamVoto != null) {
                //Recupero l'id della prenotazione
                Integer idPrenotazione = selectedExamVoto.getIdPrenotazione();
                tabellaPrenotazioni.setDisable(true);
                //Cambio della scena
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditVoto.fxml"));
                Parent root = loader.load();
                //Gestisco la schermata nuova con un altro controller
                EditVotoController editVotoController = loader.getController();

                editVotoController.setTabellaPrenotazioni(tabellaPrenotazioni);

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("INSERISCI VOTO");
                stage.setMinWidth(450);
                stage.setMinHeight(300);
                stage.setScene(scene);

                //Imposto la modalità della nuova finestra
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(tabellaPrenotazioni.getScene().getWindow());

                //Gestisco l'evento di chiusura della nuova finestra
                stage.setOnHidden(e -> {
                    // Riabilito gli elementi interattivi nella finestra corrente quando la nuova finestra è chiusa
                    tabellaPrenotazioni.setDisable(false);

                });

                // Mostro la nuova finestra
                stage.show();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
