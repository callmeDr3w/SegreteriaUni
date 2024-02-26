package application.Models;


/**
 * Classe utilizzata per i dati delle prenotazione
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class BookingExamsData {

    private Integer idPrenotazione;

    private Integer idEsamePre;

    private Long matricolaStudente;


    /**
     * Costruttore che contiene i dati delle prenotazioni
     *
     * @param idPrenotazione id della prenotazione
     * @param idEsamePre id dell'esame
     * @param matricolaStudente matricola studente
     */
    public BookingExamsData(Integer idPrenotazione, Integer idEsamePre, Long matricolaStudente){
        this.idPrenotazione = idPrenotazione;
        this.idEsamePre = idEsamePre;
        this.matricolaStudente = matricolaStudente;
    }

    /**
     * Metodo utilizzato per ritornare l'id
     *
     * @return ritorna l'id
     */
    public Integer getIdPrenotazione() {
        return idPrenotazione;
    }

    /**
     * Metodo utilizzato per ritornare l'id esame
     *
     * @return ritorna l'id dell'esame
     */
    public Integer getIdEsamePre() {
        return idEsamePre;
    }

    /**
     * Metodo utilizzato per ritornare la matricola
     *
     * @return ritorna la matricola
     */
    public Long getMatricolaStudente() {
        return matricolaStudente;
    }


}
