import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/** Responsible for printing the status of the program to the console.
 * Implements the singleton pattern.
 *
 * @author
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
     * Used to make the logger a singleton.
     * @return the same instance of the logger every time.
     */
    public static synchronized Logger getInstance() {
        if(_instance == null)
            _instance = new Logger();
        return _instance;
    }

    /**
     * Sets the target for logging.
     * @param outputStream print to console.
     */
    public void setOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void debug(String message) {
        outputStream.printf(LOG_FORMAT, getCurrentTimestamp(), "DEBUG", message);
    }
    public void info(String message) {
        outputStream.printf(LOG_FORMAT, getCurrentTimestamp(), "INFO", message);
    }
    public void warn(String message) {
        outputStream.printf(LOG_FORMAT, getCurrentTimestamp(), "WARN", message);
    }
    public void error(String message) {
        outputStream.printf(LOG_FORMAT, getCurrentTimestamp(), "ERROR", message);
    }

    private String getCurrentTimestamp() {
        return TIMESTAMP_FORMAT.format(new Timestamp(System.currentTimeMillis()));
    }
}
