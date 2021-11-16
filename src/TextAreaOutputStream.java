import javax.swing.*;
import java.io.OutputStream;

// Source: https://www.codejava.net/java-se/swing/redirect-standard-output-streams-to-jtextarea
public class TextAreaOutputStream extends OutputStream {
    private final JTextArea textArea;

    public TextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
