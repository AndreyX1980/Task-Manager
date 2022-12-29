import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Task {
    String name;
    private int priority;
    Date startDateTime;
    Date deadline;

    Author author;

    Task() {
    }


    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }


    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void createName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Что нужно сделать?");
        setName(input.nextLine());
        Date start = new Date();
        setStartDateTime(start);

    }
    public void createPriority() {
        boolean flag = true;
        System.out.println("Насколько важно: 1 - Пустяк, 2 - Желательно, 3- Надо сделать, 4- Умри, но сделай");
        while (flag) {
            Scanner input = new Scanner(System.in);
            while (!input.hasNextInt()) {
                System.out.println("Введите число:");
                input = new Scanner(System.in);
            }
            setPriority(input.nextInt());
            if (!(0 < getPriority() && getPriority() < 5)) {
                System.out.println("Введите цифру от 1 до 4");
            } else {
                flag = false;
            }

        }
    }
    public void createDeadline(){
        Calendar deadline = new GregorianCalendar();
        System.out.println("DeadLine задачи:");
        Scanner input = new Scanner(System.in);
        System.out.println("Введите число месяца:");
        int day = input.nextInt();
        System.out.println("Введите месяц (1-12):");
        int month = input.nextInt();
        System.out.println("Введите год (в формате YYYY):");
        int year = input.nextInt();
        deadline.set(year,month-1,day);
        setDeadline(deadline.getTime());
    }
}