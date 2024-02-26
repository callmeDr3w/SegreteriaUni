package application.CommandSecretary;

import application.Command;
import application.Controllers.Secretary.EditStudentController;
import application.Models.StudentData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe utilizzata per aprire la schermata di edit degli studenti
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class ModifyStudent implements Command {

    private TableView<StudentData> tabellaStudenti;


    /**
     * Costruttore che contiene il parametro per il cambio schermata
     *
     * @param tabellaStudenti tabella che mostra gli studenti
     */
    public ModifyStudent(TableView<StudentData> tabellaStudenti){
        this.tabellaStudenti = tabellaStudenti;
    }


    /**
     * Metodo utilizzato per cambiare schermata e inizare l'edit dei dati
     * Seleziono il record da modificare e con try e catch mi assicuro che non ci sinao errori nel cambio schermata concedendo di gestire il resto da l'altro controllor
     */
    @Override
    public void execute() {
        StudentData selectedStudent = tabellaStudenti.getSelectionModel().getSelectedItem();

        if(selectedStudent != null) {
            try {
                //Recupero della matricola
                Long matricola = selectedStudent.getMatricola();

                tabellaStudenti.setDisable(true);
                //Cambio della scena
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditStudents.fxml"));
                Parent root = loader.load();

                // Ottieni il controller della finestra di modifica
                EditStudentController editStudentController = loader.getController();

                // Passa la TableView al controller della finestra di modifica
                editStudentController.setTabellaStudenti(tabellaStudenti);


                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("MODIFICA STUDENTE");
                stage.setMinWidth(600);
                stage.setMinHeight(650);
                stage.setScene(scene);

                //Imposto la modalità della nuova finestra
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(tabellaStudenti.getScene().getWindow());

                //Gestisco l'evento di chiusura della nuova finestra
                stage.setOnHidden(e -> {
                    // Riabilito gli elementi interattivi nella finestra corrente quando la nuova finestra è chiusa
                    tabellaStudenti.setDisable(false);

                });

                // Mostro la nuova finestra
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}