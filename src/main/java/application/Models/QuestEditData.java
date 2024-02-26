package application.Models;

/**
 * Classe utilizzata per i dati dei questionari
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class QuestEditData {
    private Integer idDomanda;
    private Long matricolaDomanda;
    private String esameDomanda;
    private String domanda1;
    private String domanda2;
    private String domanda3;

    /**
     * Costruttore che contiene i dati dei questionari
     *
     * @param idDomanda id ella domanda
     * @param matricolaDomanda matricola
     * @param esameDomanda nome esame
     * @param domanda1 domanda 1
     * @param domanda2 domanda 2
     * @param domanda3 domanda 3
     */
    public QuestEditData(Integer idDomanda, Long matricolaDomanda, String esameDomanda, String domanda1, String domanda2, String domanda3) {
        this.idDomanda = idDomanda;
        this.matricolaDomanda = matricolaDomanda;
        this.esameDomanda = esameDomanda;
        this.domanda1 = domanda1;
        this.domanda2 = domanda2;
        this.domanda3 = domanda3;
    }

    /**
     * Metodo utilizzato per ritornare l'id
     *
     * @return ritorna l'id
     */
    public Integer getIdDomanda() {
        return idDomanda;
    }

    /**
     * Metodo utilizzato per ritornare la matricola
     *
     * @return ritorna la matricola
     */
    public Long getMatricolaDomanda() {
        return matricolaDomanda;
    }

    /**
     * Metodo utilizzato per ritornare l'esame
     *
     * @return ritorna l'esame
     */
    public String getEsameDomanda() {
        return esameDomanda;
    }

    /**
     * Metodo utilizzato per ritornare la domanda 1
     *
     * @return ritorna domanda 1
     */
    public String getDomanda1() {
        return domanda1;
    }

    /**
     * Metodo utilizzato per ritornare la domanda 2
     *
     * @return ritorna domanda 2
     */
    public String getDomanda2() {
        return domanda2;
    }

    /**
     * Metodo utilizzato per ritornare la domanda 3
     *
     * @return ritorna domanda 3
     */
    public String getDomanda3() {
        return domanda3;
    }
}
