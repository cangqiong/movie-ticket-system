package rush.io.lab.exception;

/**
 * @author cang
 * @create_time 2017-01-05 23:16
 */
public class TicketSoldOutException extends TicketException {
    public TicketSoldOutException(String message) {
        super(message);
    }

    public TicketSoldOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
