package application.Models;

/**
 * Classe utilizzata per i dati del segretario
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class SecretaryData {

    private Integer id;
    private String username;
    private String password;

    /**
     * Costruttore che contiene i dati del segretario
     *
     * @param id id segretario
     * @param username username segretario
     * @param password password segretario
     */
    public SecretaryData(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Metodo utilizzato per ritornare l'id
     *
     * @return ritorna l'id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Metodo utilizzato per ritornare l'username
     *
     * @return ritorna l'username
     */
    public String getUsername() { return username; }

    /**
     * Metodo utilizzato per ritornare la password
     *
     * @return ritorna la password
     */
    public String getPassword() {
        return password;
    }
}
