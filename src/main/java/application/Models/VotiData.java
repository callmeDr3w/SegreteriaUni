package application.Models;

/**
 * Classe utilizzata per i dati dei voti
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class VotiData {
    private Integer idVoto;

    private Integer idPreVoto;

    private Integer idEsameVoto;

    private String nomeExVoto;

    private Long matricolaVoto;

    private Integer voto;

    private Boolean conferma;

    /**
     * Costruttore che contiene i dati dei voti
     *
     * @param idVoto id del voto
     * @param idPreVoto id prenotazione
     * @param idEsameVoto id esame
     * @param nomeExVoto nome esame
     * @param matricolaVoto matricola studente
     * @param voto voto dello studente
     * @param conferma true o false
     */
    public VotiData(Integer idVoto, Integer idPreVoto, Integer idEsameVoto, String nomeExVoto, Long matricolaVoto, Integer voto, Boolean conferma) {
        this.idVoto = idVoto;
        this.idPreVoto = idPreVoto;
        this.idEsameVoto = idEsameVoto;
        this.nomeExVoto = nomeExVoto;
        this.matricolaVoto = matricolaVoto;
        this.voto = voto;
        this.conferma = conferma;
    }

    /**
     * Metodo utilizzato per ritornare l'id voto
     *
     * @return ritorna l'id
     */
    public Integer getIdVoto() {
        return idVoto;
    }

    /**
     * Metodo utilizzato per ritornare l'id prenotazione
     *
     * @return ritorna l'id prenotazione
     */
    public Integer getIdPreVoto(){
        return idPreVoto;
    }

    /**
     * Metodo utilizzato per ritornare l'id esame
     *
     * @return ritorna l'id esame
     */
    public Integer getIdEsameVoto() {
        return idEsameVoto;
    }

    /**
     * Metodo utilizzato per ritornare nome esame
     *
     * @return ritorna il nome
     */
    public String getNomeExVoto(){
        return nomeExVoto;
    }

    /**
     * Metodo utilizzato per ritornare la matricola
     *
     * @return ritorna la matricola
     */
    public Long getMatricolaVoto() {
        return matricolaVoto;
    }

    /**
     * Metodo utilizzato per ritornare il voto
     *
     * @return ritorna il voto
     */
    public Integer getVoto() {
        return voto;
    }

    /**
     * Metodo utilizzato per ritornare la conferma
     *
     * @return ritorna la conferma
     */
    public Boolean getConferma(){
        return conferma;
    }

}
