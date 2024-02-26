package application.Models;

import java.util.Date;

/**
 * Classe utilizzata per la tableview in studentController ove presenterá il join tra due tabelle
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class ResultJoin {
    private Integer idPreJoin;
    private Integer idExJoin;
    private String nomeExJoin;
    private Date dataExJoin;
    private String oraExJoin;
    private String aulaExJoin;

    /**
     * Costruttore che contiene i dati del join
     *
     * @param idPreJoin id prenotazione
     * @param idExJoin id esame
     * @param nomeExJoin nome esame
     * @param dataExJoin data esame
     * @param oraExJoin orario esame
     * @param aulaExJoin aula esame
     */
    public ResultJoin(Integer idPreJoin, Integer idExJoin, String nomeExJoin, Date dataExJoin, String oraExJoin, String aulaExJoin) {
        this.idPreJoin = idPreJoin;
        this.idExJoin = idExJoin;
        this.nomeExJoin = nomeExJoin;
        this.dataExJoin = dataExJoin;
        this.oraExJoin = oraExJoin;
        this.aulaExJoin = aulaExJoin;
    }

    /**
     * Metodo utilizzato per ritornare l'id prenotazione
     *
     * @return ritorna l'id prenotazione
     */
    public Integer getIdPreJoin() {
        return idPreJoin;
    }

    /**
     * Metodo utilizzato per ritornare l'id esame
     *
     * @return ritorna l'id esame
     */
    public Integer getIdExJoin() {
        return idExJoin;
    }

    /**
     * Metodo utilizzato per ritornare il nome esame
     *
     * @return ritorna il nome esame
     */
    public String getNomeExJoin() {
        return nomeExJoin;
    }

    /**
     * Metodo utilizzato per ritornare la data
     *
     * @return ritorna la data
     */
    public Date getDataExJoin() {
        return dataExJoin;
    }

    /**
     * Metodo utilizzato per ritornare l'orario
     *
     * @return ritorna l'orario
     */
    public String getOraExJoin() {
        return oraExJoin;
    }

    /**
     * Metodo utilizzato per ritornare l'aula
     *
     * @return ritorna l'aula
     */
    public String getAulaExJoin() {
        return aulaExJoin;
    }
}
