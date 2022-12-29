import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class Notebook {
    ArrayList<Task> sheet = new ArrayList<>();
    String name;

    public ArrayList<Task> getSheet() {
        return sheet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSheet(ArrayList<Task> sheet) {
        this.sheet = sheet;
    }

    public void createName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Название плана действий:");
        setName(input.nextLine());
    }
    public Author createAuthor() {
        Scanner input = new Scanner(System.in);
        Author author = new Author();
        System.out.println("Введите фамилию: ");
        author.setSurname(input.nextLine());
        System.out.println("Введите имя: ");
        author.setName(input.nextLine());
        System.out.println("Профессия: ");
        author.setPosition(input.nextLine());
        return author;
    }
    public void fillSheet() {
        boolean flag = true;
        ArrayList<Task> sheet = new ArrayList<>();
        Author author = createAuthor();
        while (flag) {
            System.out.println("Создать новую задачу? 1 - О.К., 2 - Отмена");
            Scanner input = new Scanner(System.in);
            int a = input.nextInt();
            if (a == 1) {
                Task task = new Task();
                task.setAuthor(author);
                task.createName();
                task.createPriority();
                task.createDeadline();
                sheet.add(task);
            } else if (a == 2) {
                System.out.println("Ваш план действий сохранен");
                flag = false;
            } else {
                System.out.println("Введите 1 - О.К.  или 2 - Отмена");
                flag = true;
            }
        }
        setSheet(sheet);
    }


    public void outputToTxtFile(String filename, ArrayList<Task> n) throws IOException {
        filename = filename + ".txt";
        FileWriter fileWriter = new FileWriter(filename);
        for (Task x : n) {
            fileWriter.write("Задача: " + x.getName() + ". Приоритет: " + x.getPriority() + ". Дата создания: " + x.getStartDateTime() + ".\n");

        }
        fileWriter.flush();
    }

    public void outputToCsvFile(String filename, ArrayList<Task> n) throws IOException {
        filename = filename + ".csv";
        FileWriter fileWriter = new FileWriter(filename);
        for (Task x : n) {
            fileWriter.write(x.getName() + "," + x.getPriority() + "," + x.getStartDateTime() + ","+
                    x.getAuthor().getName() + "," +x.getAuthor().getSurname() + "," + x.getAuthor().getPosition()+
                    ","+ x.getDeadline()+"\n");

        }
        fileWriter.flush();
    }

    public ArrayList<Task> inputFile(String filename) throws IOException, ParseException {
        String[] parsing = new String[3];
        ArrayList<Task> output = new ArrayList<>();
        File file = new File(filename +".csv");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            parsing = scanner.nextLine().split(",");
            Task task = new Task();
            task.setName(parsing[0]);
            task.setPriority(Integer.parseInt(parsing[1]));
            SimpleDateFormat sd = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH);
            task.setStartDateTime(sd.parse (parsing[2]));
            task.author = new Author();
            task.author.setName(parsing[3]);
            task.author.setSurname(parsing[4]);
            task.author.setPosition(parsing[5]);
            task.setDeadline(sd.parse (parsing[6]));
            output.add(task);

        }
        return output;
    }
    public void showPriority (String filename) throws IOException, ParseException {
        ArrayList <Task> base = inputFile(filename);
        ArrayList <Task> result = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Насколько важно: 1 - Пустяк, 2 - Желательно, 3- Надо сделать, 4- Умри, но сделай");
        int priority = input.nextInt();
        System.out.println(priority);
        for (Task x:base
        ) {
            if (priority == x.getPriority()){
                result.add (x);
            }
            outputToCsvFile (filename+"-"+priority, result);


        }
    }
}