package persistence;

/**
 * Created by Cristian del Cerro.
 */
public class NotFreeConnectionsException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotFreeConnectionsException() {
        super("There isn't connections available");
    }
}
