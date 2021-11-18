import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Responsible for printing log messages to the logging window. It implements the singleton pattern to provide for
 * common global access to the logging methods.
 *
 * @author Sasanka Gali
 * @version 1.0
 * @since 2021-11-12
 */
public class Logger {
    private static final String LOG_FORMAT = "%-10s%-7s%s\n";
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private static Logger _instance = null;

    private PrintStream outputStream = System.out;

    private Logger() {

    }

    /**
     * Gets the common instance of <code>Logger</code>.
     * <p>
     * NOTE: When this function is first invoked, a new instance is created and the same is returned. In future
     * invocations, the previously created instance is returned.
     *
     * @return instance of <code>Logger</code>
     */
    public static synchronized Logger getInstance() {
        if(_instance == null)
            _instance = new Logger();
        return _instance;
    }

    /**
     * Sets the output stream for logging.
     * @param outputStream output stream which will receive the log
     */
    public void setOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Outputs the message to <code>outputStream</code> as a debugging log along with timestamp
     * @param message message to be logged
     */
    public void debug(String message) {
        outputStream.printf(LOG_FORMAT, getCurrentTimestamp(), "DEBUG", message);
    }

    /**
     * Outputs the message to <code>outputStream</code> as an information log along with timestamp
     * @param message message to be logged
     */
    public void info(String message) {
        outputStream.printf(LOG_FORMAT, getCurrentTimestamp(), "INFO", message);
    }

    /**
     * Outputs the message to <code>outputStream</code> as a warning log along with timestamp
     * @param message message to be logged
     */
    public void warn(String message) {
        outputStream.printf(LOG_FORMAT, getCurrentTimestamp(), "WARN", message);
    }

    /**
     * Outputs the message to <code>outputStream</code> as an error log along with timestamp
     * @param message message to be logged
     */
    public void error(String message) {
        outputStream.printf(LOG_FORMAT, getCurrentTimestamp(), "ERROR", message);
    }

    private String getCurrentTimestamp() {
        return TIMESTAMP_FORMAT.format(new Timestamp(System.currentTimeMillis()));
    }
}
