package application.Models;

import java.util.Date;

/**
 * Classe utilizzata per i dati degli appelli
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class ExamsData {

    private Integer idEsame;
    private static String nomeEsame;
    private Date dataEsame;
    private String orarioEsame;
    private String aulaEsame;

    /**
     * Costruttore che contiene i dati degli appelli
     *
     * @param idEsame id dell'esame
     * @param nomeEsame nome esame
     * @param dataEsame data dell'esame
     * @param orarioEsame orario dell'esame
     * @param aulaEsame aula dell'esame
     */
    public ExamsData(Integer idEsame, String nomeEsame, Date dataEsame, String orarioEsame, String aulaEsame){
        this.idEsame = idEsame;
        this.nomeEsame = nomeEsame;
        this.dataEsame = dataEsame;
        this.orarioEsame = orarioEsame;
        this.aulaEsame = aulaEsame;
    }

    /**
     *  Metodo utilizzato per ritornare l'id
     *
     * @return ritorna l'id
     */
    public Integer getIdEsame(){
        return idEsame;
    }

    /**
     *  Metodo utilizzato per ritornare il nome dell'esame
     *
     * @return ritorna il nome dell'esame
     */
    public static String getNomeEsame() {
        return nomeEsame;
    }

    /**
     *  Metodo utilizzato per ritornare la data
     *
     * @return ritorna la data dell'esame
     */
    public Date getDataEsame() {
        return dataEsame;
    }

    /**
     *  Metodo utilizzato per ritornare l'orario
     *
     * @return ritorna l'orario
     */
    public String getOrarioEsame() {
        return orarioEsame;
    }

    /**
     *  Metodo utilizzato per ritornare l'aula
     *
     * @return ritorna l'aula
     */
    public String getAulaEsame() {
        return aulaEsame;
    }

    /**
     *  Metodo utilizzato per ritornare la stringa per intera
     *
     * @return  ritorna l'id e il nome dell'esame
     */
    @Override
    public String toString(){
        return idEsame + nomeEsame;
    }
}