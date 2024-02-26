package application.Models;

/**
 * Classe utilizzata per i dati dei questionari
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class QuestData {
    private Integer idQuest;
    private String nomeExQuest;
    private Boolean effettuato;

    /**
     * Costruttore che contiene i dati dei questionari
     *
     * @param idQuest id questionario
     * @param nomeExQuest nome dell'esame
     * @param effettuato true o false
     */
    public QuestData(Integer idQuest, String nomeExQuest, Boolean effettuato) {
        this.idQuest = idQuest;
        this.nomeExQuest = nomeExQuest;
        this.effettuato = effettuato;
    }

    /**
     * Metodo utilizzato per ritornare l'id
     *
     * @return ritorna l'id
     */
    public Integer getIdQuest() {
        return idQuest;
    }

    /**
     * Metodo utilizzato per ritornare l'esame
     *
     * @return ritorna l'esame
     */
    public String getNomeExQuest() {
        return nomeExQuest;
    }

    /**
     * Metodo utilizzato per ritornare lo stato del questionario
     *
     * @return ritorna se effettuato o meno
     */
    public Boolean getEffettuato() {
        return effettuato;
    }
}
