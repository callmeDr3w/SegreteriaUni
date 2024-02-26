package application.CommandSecretary;

import application.Command;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Classe utilizzata per pulire i campi della compilazione dello studente
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class ClearStudent implements Command {

    private TextField tfNome;
    private TextField tfCognome;
    private DatePicker datePicker;
    private TextField tfResidenza;
    private TextField tfPassw;
    private ChoiceBox<String> boxStudi;

    /**
     * Costruttore che contiene i parametri dei field da ripulire
     *
     * @param tfNome field nome studente
     * @param tfCognome field cognome studente
     * @param datePicker picker data studente
     * @param tfResidenza field residenza studente
     * @param tfPassw field password studente
     * @param boxStudi box piano di studi studente
     */
    public ClearStudent(TextField tfNome,
                        TextField tfCognome,
                        DatePicker datePicker,
                        TextField tfResidenza,
                        TextField tfPassw,
                        ChoiceBox<String> boxStudi) {

        this.tfNome = tfNome;
        this.tfCognome = tfCognome;
        this.datePicker = datePicker;
        this.tfResidenza = tfResidenza;
        this.tfPassw = tfPassw;
        this.boxStudi = boxStudi;
    }

    /**
     * Metodo utilizzato per ripulire i campi dei field
     */
    @Override
    public void execute() {
        tfNome.clear();
        tfCognome.clear();
        datePicker.setValue(null);
        tfResidenza.clear();
        tfPassw.clear();
        boxStudi.getSelectionModel().clearSelection();
    }
}
