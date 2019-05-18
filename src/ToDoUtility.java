import java.util.*;

public class ToDoUtility {

    static private void printHelp() {
        System.out.println("Help:");
        System.out.println("All - print all tasks.");
        System.out.println("Done - print only finished tasks.");
        System.out.println("notDone - print only don't finished tasks.");
        System.out.println("setDone [index] - set finished a task with number [index].");
        System.out.println("unsetDone [index] - make not finished a task with number [index].");
        System.out.println("remove [index] - delete a task with number [index].");
        System.out.println("Add [countOfLines] [description] - add a task with [description].");
        System.out.println();
        System.out.println("Pleas print a command:");
        System.out.println();
    }

    public static void main(String[] args) {
        printHelp();
        if (args.length == 0) {
            System.out.println("Please, write a name of file!");
            return;
        }
        BaseTasks base = new BaseTasks(args[0]);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = "";
            if (scanner.hasNext()) {
                command = scanner.next();
            }
            if (command.equals("Exit")) {
                break;
            } else if (command.equals("All")) {
                base.printAllTasks();
            } else if (command.equals("Done")) {
                base.printDoneTasks();
            } else if (command.equals("notDone")) {
                base.printNotDoneTasks();
            } else if (command.equals("setDone")) {
                String num = scanner.next();
                base.setDoneTask(num);
            } else if (command.equals("unsetDone")) {
                String num = scanner.next();
                base.unsetDoneTask(num);
            } else if (command.equals("remove")) {
                String num = scanner.next();
                base.removeTask(num);
            } else if (command.equals("Add")) {
                try {
                    int len = Integer.parseInt(scanner.next());
                    StringBuilder builder = new StringBuilder();
                    scanner.nextLine();
                    for(int i = 0; i < len; i++) {
                        if (scanner.hasNextLine()) {
                            builder.append(scanner.nextLine()).append(i == len - 1 ? "" : System.lineSeparator());
                        }
                    }
                    base.addTask(builder.toString());
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect format of number!");
                }
            } else if (command.equals("help")) {
                printHelp();
            } else {
                System.out.println("Unknown command!");
            }
        }
    }
}
