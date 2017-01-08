package rush.io.lab.exception;

/**
 * @author cang
 * @create_time 2017-01-05 23:15
 */
public class RepeatGrabTicketException extends TicketException {
    public RepeatGrabTicketException(String message) {
        super(message);
    }

    public RepeatGrabTicketException(String message, Throwable cause) {
        super(message, cause);
    }
}
