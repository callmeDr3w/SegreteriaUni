package application.Models;

/**
 * Classe utilizzata per i dati dell'insegnante
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class TeacherData {

    private Integer id;
    private String nome;
    private String cognome;
    private String pianoDiStudi;
    private String password;

    /**
     * Costruttore che contiene i dati dell'insegnante
     *
     * @param id id insegnante
     * @param nome nome insegnante
     * @param cognome cognome insegnante
     * @param pianoDiStudi piano di studi
     * @param password password
     */
    public TeacherData(Integer id, String nome, String cognome, String pianoDiStudi, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.pianoDiStudi = pianoDiStudi;
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
     * Metodo utilizzato per ritornare il nome
     *
     * @return ritorna il nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo utilizzato per ritornare il cognome
     *
     * @return ritorna il cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo utilizzato per ritornare il piano di studi
     *
     * @return ritorna il piano di studi
     */
    public String getPianoDiStudi() {
        return pianoDiStudi;
    }

    /**
     * Metodo utilizzato per ritornare la password
     *
     * @return ritorna la password
     */
    public String getPassword() {
        return password;
    }
}
