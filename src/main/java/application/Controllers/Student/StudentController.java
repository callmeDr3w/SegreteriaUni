package application.Controllers.Student;


import application.CommandStudent.*;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Database.Students.DatabaseStudentsMenu;
import application.Database.Teachers.DatabaseTeacherMenu;
import application.Models.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Classe utilizzata per controllare la schermata dello studente
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class StudentController implements Initializable {


    @FXML
    private TabPane tabPaneDashboard;

    @FXML
    private Tab tabHome;

    @FXML
    private AnchorPane dashboardHome;

    @FXML
    private TableView<ExamsData> tabellaAppelli;

    @FXML
    private TableColumn<ExamsData, Integer> colonnaIdEx;

    @FXML
    private TableColumn<ExamsData, String> colonnaNomeEx;

    @FXML
    private TableColumn<ExamsData, Date> colonnaDataEx;

    @FXML
    private TableColumn<ExamsData, String> colonnaOraEx;

    @FXML
    private TableColumn<ExamsData, String> colonnaAulaEx;

    @FXML
    private Button prenotaExbtn;


    @FXML
    private TableView<VotiData> tabellaVoto;

    @FXML
    private TableColumn<VotiData, Integer> colonnaIdV;

    @FXML
    private TableColumn<VotiData, Integer> colonnaIdPreV;

    @FXML
    private TableColumn<VotiData, Integer> colonnaIdExV;

    @FXML
    private TableColumn<VotiData, String> colonnaesameV;

    @FXML
    private TableColumn<VotiData, Long> colonnaMatrV;

    @FXML
    private TableColumn<VotiData, Integer> colonnaVoto;
    
    @FXML
    private TableColumn<VotiData, Boolean> colonnaConferma;

    @FXML
    private Button acceptExbtn;

    @FXML
    private Button declineExbtn;

    @FXML
    private Button exitbtn;

    @FXML
    private TableView<ResultJoin> tabellaPre;

    @FXML
    private TableColumn<ResultJoin, Integer> colonnaPreJ;

    @FXML
    private TableColumn<ResultJoin, Integer> colonnaExJ;

    @FXML
    private TableColumn<ResultJoin, String> colonnaNomeJ;

    @FXML
    private TableColumn<ResultJoin, Date> colonnaDataJ;

    @FXML
    private TableColumn<ResultJoin, String> colonnaOraJ;

    @FXML
    private TableColumn<ResultJoin, String> colonnaAulaJ;

    @FXML
    private Tab tabFeesSurvey;

    @FXML
    private AnchorPane dashboardFeesSurvey;

    @FXML
    private TableView<FeesData> tabellaFees;

    @FXML
    private TableColumn<FeesData, Integer> colonnaIdF;

    @FXML
    private TableColumn<FeesData, Long> colonnaMatrF;

    @FXML
    private TableColumn<FeesData, String> colonnaCauF;

    @FXML
    private TableColumn<FeesData, Double> colonnaImpF;

    @FXML
    private TableColumn<FeesData, Date> colonnaDataF;

    @FXML
    private TableColumn<FeesData, Boolean> colonnaPagataF;

    @FXML
    private TableView<QuestData> tabellaQuest;

    @FXML
    private TableColumn<QuestData, Integer> colonnaIdQuest;

    @FXML
    private TableColumn<QuestData, String> colonnaNomeQuest;

    @FXML
    private TableColumn<QuestData, Integer> colonnaEffettuato;

    @FXML
    private Button annullabtn;



    private ObservableList<ExamsData> examsList;
    private ObservableList<VotiData> voteList;
    private ObservableList<ResultJoin> joinList;
    private ObservableList<FeesData> feesList;
    private ObservableList<QuestData> questList;
    Long matricola = LongSession.getInstance().getId();


    //Utilizzato per eseguire l'update al database e visualizzare la tabella per il voto
    private final DatabaseStudentsMenu databaseStudentsMenu = new DatabaseStudentsMenu();

    //Utilizzato per stampare i record della tabella esami
    private final DatabaseTeacherMenu databaseTeacherMenu = new DatabaseTeacherMenu();

    private final DatabaseSecretaryMenu databaseSecretaryMenu = new DatabaseSecretaryMenu();

    /**
     * Metodo utilizzato per il bottone prenota
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void bookBTN(ActionEvent event) {
        BookExam bookExam = new BookExam(tabellaAppelli, databaseStudentsMenu, tabellaPre);
        bookExam.execute();
    }

    /**
     * Metodo utilizzato per il bottone di log out
     *
     * @param event evento che scaturisce l'azione del bottone
     * @throws IOException eccezione in caso di errore
     */
    @FXML
    protected void logoutBTN(ActionEvent event) throws IOException{
        LogoutStudent logoutStudent = new LogoutStudent(exitbtn);
        logoutStudent.execute();
    }

    /**
     * Metodo utilizzato per il bottone accetta
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void acceptBTN(ActionEvent event){
        AcceptVote acceptVote = new AcceptVote(tabellaVoto, voteList, databaseStudentsMenu);
        acceptVote.execute();
    }

    /**
     * Metodo utilizzato per il bottone rifiuta
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void declineBTN(ActionEvent event){
        DeclineVote declineVote = new DeclineVote(tabellaVoto, voteList, databaseStudentsMenu);
        declineVote.execute();
    }

    /**
     * Metodo utilizzato per il bottone elimina
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void deleteBTN(ActionEvent event){
        DeleteBooking deleteBooking = new DeleteBooking(tabellaPre, databaseTeacherMenu, joinList);
        deleteBooking.execute();
    }

    /**
     * Metodo utilizzato per il bottone effettua Pagamento
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void payBTN(ActionEvent event){
        PayFees payFees = new PayFees(tabellaFees, databaseStudentsMenu, feesList, matricola);
        payFees.execute();
    }

    /**
     * Metodo utilizzato per il bottone esegui questionario
     *
     * @param event evento che scaturisce l'azione del bottone
     * @throws IOException eccezione in caso di errore
     */
    @FXML
    protected void questBTN(ActionEvent event) throws IOException {
        QuestExam questExam = new QuestExam(tabellaQuest);
        questExam.execute();
    }

    /**
     * Metodo utilizzato per mostrare i dati degli appelli sulla tabella
     */
    public void examsShowData() {
        colonnaIdEx.setCellValueFactory(new PropertyValueFactory<>("idEsame"));
        colonnaNomeEx.setCellValueFactory(new PropertyValueFactory<>("nomeEsame"));
        colonnaDataEx.setCellValueFactory(new PropertyValueFactory<>("dataEsame"));
        colonnaOraEx.setCellValueFactory(new PropertyValueFactory<>("orarioEsame"));
        colonnaAulaEx.setCellValueFactory(new PropertyValueFactory<>("aulaEsame"));
    }

    /**
     * Metodo utilizzato per mostrare i dati dei voti sulla tabella
     */
    public void voteShowData(){
        colonnaIdV.setCellValueFactory(new PropertyValueFactory<>("idVoto"));
        colonnaIdPreV.setCellValueFactory(new PropertyValueFactory<>("idPreVoto"));
        colonnaIdExV.setCellValueFactory(new PropertyValueFactory<>("idEsameVoto"));
        colonnaesameV.setCellValueFactory(new PropertyValueFactory<>("nomeExVoto"));
        colonnaMatrV.setCellValueFactory(new PropertyValueFactory<>("matricolaVoto"));
        colonnaVoto.setCellValueFactory(new PropertyValueFactory<>("voto"));
        colonnaConferma.setCellValueFactory(new PropertyValueFactory<>("conferma"));

    }

    /**
     * Metodo utilizzato per mostrare i dati della prenotazione sulla tabella
     */
    public void joinShowData(){
        colonnaPreJ.setCellValueFactory(new PropertyValueFactory<>("idPreJoin"));
        colonnaExJ.setCellValueFactory(new PropertyValueFactory<>("idExJoin"));
        colonnaNomeJ.setCellValueFactory(new PropertyValueFactory<>("nomeExJoin"));
        colonnaDataJ.setCellValueFactory(new PropertyValueFactory<>("dataExJoin"));
        colonnaOraJ.setCellValueFactory(new PropertyValueFactory<>("oraExJoin"));
        colonnaAulaJ.setCellValueFactory(new PropertyValueFactory<>("aulaExJoin"));

    }

    /**
     * Metodo utilizzato per mostrare i dati delle tasse sulla tabella
     */
    public void feesShowData(){
        colonnaIdF.setCellValueFactory(new PropertyValueFactory<>("idTassa"));
        colonnaMatrF.setCellValueFactory(new PropertyValueFactory<>("matricolaTassa"));
        colonnaCauF.setCellValueFactory(new PropertyValueFactory<>("causale"));
        colonnaImpF.setCellValueFactory(new PropertyValueFactory<>("importo"));
        colonnaDataF.setCellValueFactory(new PropertyValueFactory<>("dataScadenza"));
        colonnaPagataF.setCellValueFactory(new PropertyValueFactory<>("pagata"));

    }

    /**
     * Metodo utilizzato per mostrare i dati dei questionari sulla tabella
     */
    public void questShowData(){
        colonnaIdQuest.setCellValueFactory(new PropertyValueFactory<>("idQuest"));
        colonnaNomeQuest.setCellValueFactory(new PropertyValueFactory<>("nomeExQuest"));
        colonnaEffettuato.setCellValueFactory(new PropertyValueFactory<>("effettuato"));
    }

    /**
     * Metodo per inizializzare la schermata ogni qual volta la si starta
     *
     * @param url indirizzo del file FXML che definisce la struttura dell'interfaccia utente
     * @param resourceBundle fornisce localizzazione o gestione delle risorse
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //record tabella esami
        examsList = databaseTeacherMenu.getAllRecords();
        tabellaAppelli.setItems(examsList);
        examsShowData();

        //record tabella voti
        voteList = databaseStudentsMenu.getAllVote();
        tabellaVoto.setItems(voteList);
        voteShowData();

        //Utilizzo removeIf cosi quando non avviene la prenotazione, nasconde i record che non servono
        joinList = databaseStudentsMenu.getAllBookingJoin();
        joinList.removeIf(resultJoin -> resultJoin.getIdPreJoin() == 0);
        tabellaPre.setItems(joinList);
        joinShowData();

        //record tabella tasse
        feesList = databaseStudentsMenu.getAllStudentFees(matricola);
        tabellaFees.setItems(feesList);
        feesShowData();

        //record tabella questionario
        questList = databaseSecretaryMenu.getAllQuest();
        tabellaQuest.setItems(questList);
        questShowData();


        // Aggiungo un listener alla tabella per selezionare una riga
        tabellaAppelli.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // Abilita il pulsante Elimina quando una riga è selezionata
            // Disabilita il pulsante Elimina quando nessuna riga è selezionata
            prenotaExbtn.setDisable(newSelection == null);
        });
    }
}

