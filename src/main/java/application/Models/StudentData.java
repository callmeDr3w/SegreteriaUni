package application.Models;

import java.util.Date;

/**
 * Classe utilizzata per i dati dello studente
 *
 * @author andreaaristarco
 * @version 1.0
 */
public class StudentData {

    private Long matricola;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String residenza;
    private String pianoDiStudi;
    private String password;

    /**
     * Costruttore che contiene i dati dello studente
     *
     * @param matricola matricola studente
     * @param nome nome studente
     * @param cognome cognome studente
     * @param dataDiNascita data di nascita
     * @param residenza residenza
     * @param pianoDiStudi piano di studi
     * @param password password
     */
    public StudentData(Long matricola, String nome, String cognome, Date dataDiNascita, String residenza, String pianoDiStudi, String password) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.residenza = residenza;
        this.pianoDiStudi = pianoDiStudi;
        this.password = password;
    }

    /**
     * Metodo utilizzato per ritornare la matricola
     *
     * @return ritorna la matricola
     */
    public Long getMatricola() {
        return matricola;
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
     * Metodo utilizzato per ritornare la data
     *
     * @return ritorna la data
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Metodo utilizzato per ritornare la residenza
     *
     * @return ritorna la residenza
     */
    public String getResidenza() {
        return residenza;
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