public class Logger {

    private static Logger _instance = null;

    public static synchronized Logger getInstance(){
        if(_instance == null)
            _instance = new Logger();
        return _instance;
    }
    private Logger() {

    }
    public void debug (String message) {
        System.out.printf("%s\t%s\t%s\n", new Timestamp(System.currentTimeMillis()), "INFO", message);
    }
    public void info (String message) {
        System.out.printf("%s\t%s\t%s\n", new Timestamp(System.currentTimeMillis()), "INFO", message);
    }
    public void warn (String message) {
        System.out.printf("%s\t%s\t%s\n", new Timestamp(System.currentTimeMillis()), "INFO", message);
    }
    public void error (String message) {
        System.out.printf("%s\t%s\t%s\n", new Timestamp(System.currentTimeMillis()), "INFO", message);
    }
}
