package rush.io.lab.exception;

/**
 * @author cang
 * @create_time 2017-01-05 23:15
 */
public class TicketException extends Exception {
    public TicketException(String message) {
        super(message);
    }

    public TicketException(String message, Throwable cause) {
        super(message, cause);
    }
}
