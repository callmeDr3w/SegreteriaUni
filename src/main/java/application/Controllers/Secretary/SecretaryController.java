package application.Controllers.Secretary;

import application.CommandSecretary.*;
import application.Database.Secretary.DatabaseSecretaryMenu;
import application.Database.Students.DatabaseStudentsMenu;
import application.Models.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


/**
 * Classe utilizzata per controllare la schermata della segreteria dove avvengono diverse operazioni
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class SecretaryController implements Initializable{

    @FXML
    private TabPane tabPaneDashboard;

    @FXML
    private Tab tabHome;

    @FXML
    private SplitPane dashboardHome;

    @FXML
    private AnchorPane leftDataSide;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCognome;

    @FXML
    private TextField tfResidenza;

    @FXML
    private PasswordField tfPassw;

    @FXML
    private ChoiceBox<String> boxStudi;

    @FXML
    private AnchorPane rightDataSide;

    @FXML
    private AnchorPane searchDataSide;

    @FXML
    private TextField tfSearch;

    @FXML
    private Button cercabtn;

    @FXML
    private Button aggiornabtn;

    @FXML
    private TableView<StudentData> tabellaStudenti;

    @FXML
    private TableColumn<StudentData, Long> tabellaMatricola;

    @FXML
    private TableColumn<StudentData, String> tabellaNome;

    @FXML
    private TableColumn<StudentData, String> tabellaCognome;

    @FXML
    private TableColumn<StudentData,Date> tabellaDataDiNascita;

    @FXML
    private TableColumn<StudentData, String> tabellaResidenza;

    @FXML
    private TableColumn<StudentData, String> tabellaPianoDiStudi;

    @FXML
    private TableColumn<StudentData, String> tabellaPassword;


    @FXML
    private Button aggiungibtn;

    @FXML
    private Button puliscibtn;

    @FXML
    private Button eliminabtn;

    @FXML
    private Button escibtn;

    @FXML
    private Button modificabtn;

    @FXML
    private Tab tabVoteFees;

    @FXML
    private AnchorPane dashboardVoteFees;

    @FXML
    private AnchorPane feesPane;

    @FXML
    private TextField tfFees;

    @FXML
    private Button cercaFeesbtn;

    @FXML
    private Button refreshFeesbtn;

    @FXML
    private AnchorPane VotePane;

    @FXML
    private TextField tfVote;

    @FXML
    private Button cercaVotebtn;

    @FXML
    private Button refreshVotebtn;

    @FXML
    private Button aggiungiTassebtn;

    @FXML
    private Button eliminaTassebtn;

    @FXML
    private TableView<FeesData> tabellaFees;

    @FXML
    private TableColumn<FeesData, Integer> colonnaidT;

    @FXML
    private TableColumn<FeesData, Long> colonnaMatrT;

    @FXML
    private TableColumn<FeesData, String> colonnaCauT;

    @FXML
    private TableColumn<FeesData, Double> colonnaImpT;

    @FXML
    private TableColumn<FeesData, Date> colonnaScaT;

    @FXML
    private TableColumn<FeesData, Boolean> colonnaPagT;

    @FXML
    private TableView<VotiData> tabellaVote;


    @FXML
    private TableColumn<VotiData, Integer> colonnaidV;

    @FXML
    private TableColumn<VotiData, Integer> colonnaPreV;

    @FXML
    private TableColumn<VotiData, Integer> colonnaExV;

    @FXML
    private TableColumn<VotiData, String> colonnaNomeV;

    @FXML
    private TableColumn<VotiData, Long> colonnaMatrV;

    @FXML
    private TableColumn<VotiData, Integer> colonnaVoto;

    @FXML
    private TableColumn<VotiData, Boolean> colonnaConfV;


    @FXML
    private Button esci2btn;

    //inizializzo i piani di studio nella box
    private String[] university = {"Informatica", "Conduzione del Mezzo Navale", "Scienze Biologiche"};


    private ObservableList<StudentData> studentList;
    private ObservableList<FeesData> feesList;
    private ObservableList<VotiData> voteList;

    private final DatabaseSecretaryMenu databaseSecretaryMenu = new DatabaseSecretaryMenu();
    private final DatabaseStudentsMenu databaseStudentsMenu = new DatabaseStudentsMenu();


    /**
     * Metodo utilizzato per il bottone aggiungi
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void addBTN(ActionEvent event) {
            // Crea un'istanza di AddStudentCommand passando tutti i parametri necessari
            AddStudent addStudent = new AddStudent(
                    tfNome.getText(),
                    tfCognome.getText(),
                    datePicker.getValue(),
                    tfResidenza.getText(),
                    boxStudi.getValue(),
                    tfPassw.getText(),
                    studentList,
                    tabellaStudenti,
                    tfNome,
                    tfCognome,
                    datePicker,
                    tfResidenza,
                    tfPassw,
                    boxStudi
            );

            // Esegui il comando
            addStudent.execute();

    }

    /**
     * Metodo utilizzato per il bottone aggiungi tasse
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void insertFeesBTN(ActionEvent event){
        AddFees addFees = new AddFees(tabellaStudenti, databaseSecretaryMenu, feesList, tabellaFees);
        addFees.execute();
    }


    /**
     * Metodo utilizzato per il bottone pulisci
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void clearBTN(ActionEvent event){
        ClearStudent clearStudent = new ClearStudent(
                tfNome,
                tfCognome,
                datePicker,
                tfResidenza,
                tfPassw,
                boxStudi
                );
        clearStudent.execute();
}


    /**
     * Metodo utilizzato per il bottone logout
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void logoutBTN(ActionEvent event){
        LogoutSecretary logoutSecretary = new LogoutSecretary(escibtn);
        logoutSecretary.execute();
}


    /**
     * Metodo utilizzato per il bottone logout
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void logout2BTN(ActionEvent event){
        LogoutSecretary logoutSecretary = new LogoutSecretary(escibtn);
        logoutSecretary.execute();
    }


    /**
     * Classe utilizzata per il bottone cerca
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void searchBTN(ActionEvent event){
        SearchStudent searchStudent = new SearchStudent(
                studentList,
                databaseSecretaryMenu,
                tabellaStudenti,
                tfSearch
        );
        searchStudent.execute();
    }


    /**
     * Metodo utilizzato per il bottone aggiorna
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void refreshBTN(ActionEvent event){
        RefreshStudent refreshStudent = new RefreshStudent(tfSearch, studentList,
                databaseSecretaryMenu, tabellaStudenti);
        refreshStudent.execute();
}


    /**
     * Metodo utilizzato per il bottone elimina
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void deleteBTN(ActionEvent event){
            DeleteStudent deleteStudent = new DeleteStudent(
                    databaseSecretaryMenu,
                    studentList,
                    tabellaStudenti
            );

            deleteStudent.execute();
    }

    /**
     * Metodo utilizzato per il bottone elimina tasse
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void deleteFeesBTN(ActionEvent event){
        DeleteFees deleteFees = new DeleteFees(tabellaFees, databaseSecretaryMenu, feesList);
        deleteFees.execute();
    }


    /**
     * Metodo utilizzato per il bottone modifica
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void modifyBTN(ActionEvent event){
        ModifyStudent modifyStudent = new ModifyStudent(tabellaStudenti);

        modifyStudent.execute();
    }

    /**
     * Metodo utilizzato per il bottone cerca su tasse
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void searchFeesBTN(ActionEvent event){
        SearchFees searchFees = new SearchFees(tfFees, feesList, databaseSecretaryMenu, tabellaFees);
        searchFees.execute();
    }

    /**
     * Metodo utilizzato per il bottone cerca su voti
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void searchVoteBTN(ActionEvent event){
        SearchVote searchVote = new SearchVote(tfVote, voteList, databaseStudentsMenu, tabellaVote);
        searchVote.execute();
    }

    /**
     * Metodo utilizzato per il bottone aggiorna su tasse
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void refreshFeesBTN(ActionEvent event){
        RefreshFees refreshFees = new RefreshFees(tfFees, feesList, databaseSecretaryMenu, tabellaFees);
        refreshFees.execute();
    }

    /**
     * Metodo utilizzato per il bottone aggiorna su voti
     *
     * @param event evento che scaturisce l'azione del bottone
     */
    @FXML
    protected void refreshVoteBTN(ActionEvent event){
        RefreshVote refreshVote = new RefreshVote(tfVote, voteList, databaseStudentsMenu, tabellaVote);
        refreshVote.execute();
    }


    /**
     * Metodo utilizzato per mostrare i dati degli studenti sulla tabella
     */
    public void studentsShowData(){
    tabellaMatricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));
    tabellaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    tabellaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
    tabellaDataDiNascita.setCellValueFactory(new PropertyValueFactory<>("dataDiNascita"));
    tabellaResidenza.setCellValueFactory(new PropertyValueFactory<>("residenza"));
    tabellaPianoDiStudi.setCellValueFactory(new PropertyValueFactory<>("pianoDiStudi"));
    tabellaPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
}

    /**
     * Metodo utilizzato per mostrare i dati delle tasse sulla tabella
     */
    public void feesShowData2(){
        colonnaidT.setCellValueFactory(new PropertyValueFactory<>("idTassa"));
        colonnaMatrT.setCellValueFactory(new PropertyValueFactory<>("matricolaTassa"));
        colonnaCauT.setCellValueFactory(new PropertyValueFactory<>("causale"));
        colonnaImpT.setCellValueFactory(new PropertyValueFactory<>("importo"));
        colonnaScaT.setCellValueFactory(new PropertyValueFactory<>("dataScadenza"));
        colonnaPagT.setCellValueFactory(new PropertyValueFactory<>("pagata"));

    }

    /**
     * Metodo utilizzato per mostrare i dati dei voti sulla tabella
     */
    public void voteShowData2(){
        colonnaidV.setCellValueFactory(new PropertyValueFactory<>("idVoto"));
        colonnaPreV.setCellValueFactory(new PropertyValueFactory<>("idPreVoto"));
        colonnaExV.setCellValueFactory(new PropertyValueFactory<>("idEsameVoto"));
        colonnaNomeV.setCellValueFactory(new PropertyValueFactory<>("nomeExVoto"));
        colonnaMatrV.setCellValueFactory(new PropertyValueFactory<>("matricolaVoto"));
        colonnaVoto.setCellValueFactory(new PropertyValueFactory<>("voto"));
        colonnaConfV.setCellValueFactory(new PropertyValueFactory<>("conferma"));
    }

    /**
     * Metodo per inizializzare la schermata ogni qual volta la si starta
     *
     * @param url indirizzo del file FXML che definisce la struttura dell'interfaccia utente
     * @param resourceBundle fornisce localizzazione o gestione delle risorse
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       boxStudi.getItems().addAll(university);

       //record tabella studenti
        studentList = databaseSecretaryMenu.getAllRecords();
        tabellaStudenti.setItems(studentList);
        studentsShowData();

        //record tabella tasse
        feesList = databaseSecretaryMenu.getAllFees();
        tabellaFees.setItems(feesList);
        feesShowData2();

        //record tabella voti
        voteList = databaseStudentsMenu.getAllVote();
        tabellaVote.setItems(voteList);
        voteShowData2();


        // Aggiungo un listener alla tabella per selezionare una riga
        tabellaStudenti.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Abilita il pulsante Elimina quando una riga è selezionata
                eliminabtn.setDisable(false);
            } else {
                // Disabilita il pulsante Elimina quando nessuna riga è selezionata
                eliminabtn.setDisable(true);
            }
        });
    }

}