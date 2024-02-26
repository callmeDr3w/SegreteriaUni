package application.Models;

//Singleton

import application.Models.Utils.AlertUtil;

/**
 * Classe utilizzata per conservare i log effettuati dagli studenti
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class LongSession {

    private Long id;

    private static final LongSession instance = new LongSession();

    /**
     * Metodo utilizzato per controllare le istanze
     */
    private LongSession(){
        if (instance != null){
            AlertUtil.showErrorAlert("Non puoi creare più instanze di questo oggetto");
        }
    }

    /**
     * Metodo utilizzato per ritornare l'istanza
     *
     * @return ritorna l'istanza
     */
    public static LongSession getInstance(){
        return instance;
    }

    /**
     * Metodo utilizzato per ritornare l'id
     *
     * @return ritorna l'id
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo utilizzato per settare l'id
     *
     * @param id setta l'id
     */
    public void setId(Long id) {
        this.id = id;
    }

}
