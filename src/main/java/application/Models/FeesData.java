package application.Models;

import java.util.Date;

/**
 * Classe utilizzata per i dati delle tasse
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class FeesData {
    private Integer idTassa;
    private Long matricolaTassa;
    private String causale;
    private Double importo;
    private Date dataScadenza;
    private Boolean pagata;

    /**
     * Costruttore che contiene i dati delle tasse
     *
     * @param idTassa id tassa
     * @param matricolaTassa matricola studente
     * @param causale motivo del pagamento
     * @param importo importo in euro
     * @param dataScadenza data di scadenza
     * @param pagata true o false
     */
    public FeesData(Integer idTassa, Long matricolaTassa, String causale, Double importo, Date dataScadenza, Boolean pagata) {
        this.idTassa = idTassa;
        this.matricolaTassa = matricolaTassa;
        this.causale = causale;
        this.importo = importo;
        this.dataScadenza = dataScadenza;
        this.pagata = pagata;
    }

    /**
     * Metodo utilizzato per ritornare l'id della tassa
     *
     * @return  ritorna l'id
     */
    public Integer getIdTassa() {
        return idTassa;
    }

    /**
     * Metodo utilizzato per ritornare la matricola
     *
     * @return ritorna la matricola
     */
    public Long getMatricolaTassa() {
        return matricolaTassa;
    }

    /**
     * Metodo utilizzato per ritornare la causale
     *
     * @return ritorna la causale
     */
    public String getCausale() {
        return causale;
    }

    /**
     * Metodo utilizzato per ritornare l'importo
     *
     * @return ritorna l'importo
     */
    public Double getImporto() {
        return importo;
    }

    /**
     * Metodo utilizzato per ritornare la data di scadenza
     *
     * @return ritorna la data di scadenza
     */
    public Date getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Metodo utilizzato per ritornare lo stato del pagamento
     *
     * @return ritorna se pagata o meno
     */
    public Boolean getPagata() {
        return pagata;
    }
}
