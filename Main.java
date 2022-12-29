import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Notebook notebook = new Notebook();
        notebook.createName();
        notebook.fillSheet();
        notebook.outputToCsvFile(notebook.getName(), notebook.getSheet());
        notebook.showPriority("Тест");
    }
}