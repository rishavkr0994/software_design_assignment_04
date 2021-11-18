import javax.swing.*;
import java.io.OutputStream;

/**
 * This class defines an output stream for writing out data to a JTextArea
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public class TextAreaOutputStream extends OutputStream {

    private final JTextArea textArea;

    /**
     * Creates a new instance of TextAreaOutputStream
     * @param textArea a JTextArea object to be written to
     */
    public TextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
