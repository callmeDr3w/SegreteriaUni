package application;


/**
 * Interfaccia Command, pattern utilizzato per eseguire determinate azioni al click dei bottoni delle nostre interfacce
 *
 * @author andreaaristarco
 * @version 1.0
 */
//Pattern Command per l'uso dei bottoni
public interface Command {
    /**
     * Metodo utilizzato per effettuare l'esecuzione dell'evento
     */
    void execute();
}
