package application.CommandTeacher;

import application.Command;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Classe utilizzata per ripulire il modulo per l'aggiunta di un esame
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class ClearExam implements Command {
    private ChoiceBox<String> boxEsami;
    private DatePicker datePickerEsami;
    private ChoiceBox<String> boxOrari;
    private TextField tfAula;

    /**
     * Costruttore che contiene i parametri per ripulire il modulo
     *
     * @param boxEsami box elenco esami
     * @param datePickerEsami picker data esame
     * @param boxOrari box orari esame
     * @param tfAula field aula esame
     */
    public ClearExam(ChoiceBox<String> boxEsami, DatePicker datePickerEsami, ChoiceBox<String> boxOrari, TextField tfAula) {
        this.boxEsami = boxEsami;
        this.datePickerEsami = datePickerEsami;
        this.boxOrari = boxOrari;
        this.tfAula = tfAula;
    }

    /**
     * Metodo utilizzato pe rirpulire il modulo
     */
    @Override
    public void execute() {
        boxEsami.getSelectionModel().clearSelection();
        datePickerEsami.setValue(null);
        boxOrari.getSelectionModel().clearSelection();
        tfAula.clear();
    }
}
