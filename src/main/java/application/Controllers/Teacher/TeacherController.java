package application.Controllers.Teacher;


import application.CommandTeacher.*;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.BookingExamsData;
import application.Models.ExamsData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Classe utilizzata per controllare la schermata del docente
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class TeacherController implements Initializable {

    @FXML
    private SplitPane dashboardHome;

    @FXML
    private AnchorPane leftDataSide;

    @FXML
    private ChoiceBox<String> boxEsami;

    @FXML
    private DatePicker datePickerEsami;

    @FXML
    private ChoiceBox<String> boxOrari;

    @FXML
    private TextField tfAula;

    @FXML
    private Button aggiungiEsamebtn;

    @FXML
    private Button pulisciEsamebtn;

    @FXML
    private AnchorPane searchExamSide;

    @FXML
    private TextField tfSearch;

    @FXML
    private Button cercabtn;

    @FXML
    private Button refreshbtn;

    @FXML
    private AnchorPane searchBookSide;

    @FXML
    private TextField tfSearchPre;

    @FXML
    private Button cercaPrebtn;

    @FXML
    private Button refreshPrebtn;

    @FXML
    private AnchorPane rightDataSide;

    @FXML
    private TableView<ExamsData> tabellaAppelli;

    @FXML
    private TableColumn<ExamsData, Integer> colonnaID;

    @FXML
    private TableColumn<ExamsData, String> colonnaEsame;

    @FXML
    private TableColumn<ExamsData, Date> colonnaDataEsame;

    @FXML
    private TableColumn<ExamsData, Integer> colonnaOrarioEsame;

    @FXML
    private TableColumn<ExamsData, String> colonnaAulaEsame;

    @FXML
    private TableView<BookingExamsData> tabellaPrenotazioni;

    @FXML
    private TableColumn<BookingExamsData, Integer> colonnaPrenotazione;

    @FXML
    private TableColumn<BookingExamsData, Integer> colonnaIDPre;

    @FXML
    private TableColumn<BookingExamsData, Long> colonnaMatricola;



    @FXML
    private Button modificaEsamebtn;

    @FXML
    private Button modificaPrebtn;

    @FXML
    private Button aggiungiVotobtn;

    @FXML
    private Button eliminaEsamebtn;

    @FXML
    private Button eliminaPrebtn;

    @FXML
    private Button exitEsamebtn;

    private String[] examName = {"Programmazione I", "Programmazione II", "Programmazione III"};
    private String[] examTime = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",
            "13:30", "14:00", "14:30", "15:00", "15:30", "16:00"};
    
private ObservableList<ExamsData> examsList;
private ObservableList<BookingExamsData> bookingList;
private final DatabaseTeacherMenu databaseTeacherMenu = new DatabaseTeacherMenu();

    /**
     * Metodo utilizzato per il bottone aggiungi
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void addBTN(ActionEvent event){
        AddExam addExam = new AddExam(boxEsami, boxOrari, tfAula,
                datePickerEsami, examsList, databaseTeacherMenu, tabellaAppelli);
        addExam.execute();
    }

    /**
     * Metodo utilizzato per il bottone pulisci
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void clearBTN(ActionEvent event){
        ClearExam clearExam = new ClearExam(boxEsami,datePickerEsami, boxOrari, tfAula);
        clearExam.execute();
    }

    /**
     * Metodo utilizzato per il bottone logout
     *
     * @param event evento che scaturisce l'azione del bottone
     * @throws IOException eccezione in caso di errore
     */
    @FXML
    protected void logoutBTN(ActionEvent event) throws IOException {
        LogoutTeacher logoutTeacher = new LogoutTeacher(exitEsamebtn);
        logoutTeacher.execute();
    }

    /**
     * Metodo utilizzato per il bottone cerca appelli
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void searchBTN(ActionEvent event){
        SearchExam searchExam = new SearchExam(tfSearch, examsList,
                databaseTeacherMenu, tabellaAppelli);
        searchExam.execute();
    }

    /**
     * Metodo utilizzato per il bottone cerca prenotazione
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void searchPreBTN(ActionEvent event){
        SearchBooking searchBooking = new SearchBooking(tfSearchPre, bookingList,
                databaseTeacherMenu, tabellaPrenotazioni);
        searchBooking.execute();
    }

    /**
     * Metodo utilizzato per il bottone aggiorna esami
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void refreshBTN(ActionEvent event){
        RefreshExam refreshExam = new RefreshExam(tfSearch, examsList,
                databaseTeacherMenu, tabellaAppelli);
        refreshExam.execute();
    }

    /**
     * Metodo utilizzato per il bottone aggiorna prenotazioni
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void refreshPreBTN(ActionEvent event){
        RefreshBooking refreshBooking = new RefreshBooking(tfSearchPre, bookingList,
                databaseTeacherMenu, tabellaPrenotazioni);
        refreshBooking.execute();
    }

    /**
     * Metodo utilizzato per il bottone elimina appelli
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void deleteBTN(ActionEvent event){
        DeleteExam deleteExam = new DeleteExam(tabellaAppelli, databaseTeacherMenu, examsList);
        deleteExam.execute();
    }

    /**
     * Metodo utilizzato per il bottone elimina prenotazioni
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void deletePreBTN(ActionEvent event){
        DeleteBooking deleteBooking = new DeleteBooking(tabellaPrenotazioni, databaseTeacherMenu, bookingList);
        deleteBooking.execute();
    }

    /**
     * Metodo utilizzato per il bottone modifica appello
     *
     * @param event evento che scaturisce l'azione del bottone
     * @throws IOException eccezione in caso di errore
     */
    @FXML
    protected void modifyBTN(ActionEvent event) throws IOException{
        ModifyExam modifyExam = new ModifyExam(tabellaAppelli);
        modifyExam.execute();
    }

    /**
     * Metodo utilizzato per il bottone aggiungi voto
     *
     * @param event evento che scaturisce l'azione del bottone
     * @throws IOException eccezione in caso di errore
     */
    @FXML
    protected void addVotoBTN(ActionEvent event) throws IOException{
        AddVoto addVoto = new AddVoto(tabellaPrenotazioni);
        addVoto.execute();
    }

    /**
     * Metodo utilizzato per mostrare i dati degli appelli sulla tabella
     */
    public void examsShowData(){
        colonnaID.setCellValueFactory(new PropertyValueFactory<>("idEsame"));
        colonnaEsame.setCellValueFactory(new PropertyValueFactory<>("nomeEsame"));
        colonnaDataEsame.setCellValueFactory(new PropertyValueFactory<>("dataEsame"));
        colonnaOrarioEsame.setCellValueFactory(new PropertyValueFactory<>("orarioEsame"));
        colonnaAulaEsame.setCellValueFactory(new PropertyValueFactory<>("aulaEsame"));
    }

    /**
     * Metodo utilizzato per mostrare i dati delle prenotazioni sulla tabella
     */
    public void bookingShowData(){
        colonnaPrenotazione.setCellValueFactory(new PropertyValueFactory<>("idPrenotazione"));
        colonnaIDPre.setCellValueFactory(new PropertyValueFactory<>("idEsamePre"));
        colonnaMatricola.setCellValueFactory(new PropertyValueFactory<>("matricolaStudente"));
    }


    /**
     * Metodo per inizializzare la schermata ogni qual volta la si starta
     *
     * @param url indirizzo del file FXML che definisce la struttura dell'interfaccia utente
     * @param resourceBundle fornisce localizzazione o gestione delle risorse
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxEsami.getItems().addAll(examName);
        boxOrari.getItems().addAll(examTime);

        //Inizializzo la tabella ad ogni start del programma rendendo visibili gli esami registrati e le prenotazioni
        examsList = databaseTeacherMenu.getAllRecords();
        tabellaAppelli.setItems(examsList);
        examsShowData();


        bookingList = databaseTeacherMenu.getAllBooking();
        tabellaPrenotazioni.setItems(bookingList);
        bookingShowData();

        // Aggiungo un listener alla tabella per selezionare una riga
        tabellaAppelli.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if (newSelection != null){
                eliminaEsamebtn.setDisable(false);
            }else{
                eliminaEsamebtn.setDisable(true);
            }
        });

        tabellaPrenotazioni.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if (newSelection != null) {
                // Abilita il pulsante aggiungi voto ed elimina quando una riga è selezionata
                aggiungiVotobtn.setDisable(false);
                eliminaPrebtn.setDisable(false);
            } else {
                // Disabilita il pulsante aggiungi voto ed elimina quando nessuna riga è selezionata
                aggiungiVotobtn.setDisable(true);
                eliminaPrebtn.setDisable(true);
            }
        });
    }
}

