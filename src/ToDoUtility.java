import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class ToDoUtility {
    private static class Task implements Serializable {
        String description;
        boolean done;

        Task() {
            description = null;
            done = false;
        }

        Task(final String description) {
            this.description = description;
            done = false;
        }

        String getDescription() {
            return description;
        }

        void setDone() {
            done = true;
        }

        void unsetDone() {
            done = false;
        }
    }

    static private List<Task> toObject(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Task>>() {
        }.getType();
        List<Task> list = new LinkedList<>();
        try {
            list = gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            System.out.println("Can't parse json");
        }
        return list;
    }

    static private void printList(List<Task> tasks, String msg) {
        System.out.println(msg);
        int ind = 0;
        for (Task task : tasks) {
            ind++;
            System.out.println(ind + ": " + task.getDescription() + " (" + (task.done ? "Finished" : "Not finished") + ")");
        }
    }

    private static class BaseTasks {
        List<Task> tasks;

        BaseTasks() {
            tasks = new LinkedList<>();
        }

        BaseTasks(final String nameOfFile) {
            tasks = new LinkedList<>();
            Scanner scanner;
            try {
                scanner = new Scanner(new File(nameOfFile));
                StringBuilder builder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine());
                }
                scanner.close();
                tasks = toObject(builder.toString());
            } catch (FileNotFoundException e) {
                System.out.println("Can't find a file!");
            }
        }

        List<Task> getTasks() {
            return tasks;
        }

        List<Task> getDoneTasks() {
            return tasks.stream().filter(e -> e.done).collect(Collectors.toList());
        }

        List<Task> getNotDoneTasks() {
            return tasks.stream().filter(e -> !e.done).collect(Collectors.toList());
        }

        void printAllTasks() {
            printList(getTasks(), "Tasks: ");
        }

        void printDoneTasks() {
            printList(getDoneTasks(), "Done tasks: ");
        }

        void printNotDoneTasks() {
            printList(getNotDoneTasks(), "Not done tasks: ");
        }

        void removeTask(String ind) {
            if (ind.equals("0")) {
                return;
            }
            try {
                tasks.remove(Integer.parseInt(ind) - 1);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of number!");
            }
        }

        void addTask(String description) {
            tasks.add(new Task(description));
        }

        void setDoneTask(String ind) {
            if (ind.equals("0")) {
                return;
            }
            try {
                tasks.get(Integer.parseInt(ind) - 1).setDone();
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of number!");
            }
        }

        void unsetDoneTask(String ind) {
            if (ind.equals("0")) {
                return;
            }
            try {
                tasks.get(Integer.parseInt(ind) - 1).unsetDone();
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of number!");
            }
        }
    }

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
