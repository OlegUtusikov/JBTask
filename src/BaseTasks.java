import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BaseTasks {
    private List<Task> tasks;

    BaseTasks() {
        tasks = new LinkedList<>();
    }

    BaseTasks(final String nameOfFile) {
        try (Scanner scanner = new Scanner(new File(nameOfFile))) {
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            tasks = toObject(builder.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Can't find a file!");
        } catch (NullPointerException e) {
            System.out.println("The path is null!");
        }
        if (tasks == null) {
            tasks = new LinkedList<>();
        }
    }

    private List<Task> toObject(String json) {
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

    private void printList(List<Task> tasks, String msg) {
        System.out.println(msg);
        int ind = 0;
        for (Task task : tasks) {
            ind++;
            System.out.println(ind + ": " + task.getDescription() + " (" + (task.getDone() ? "Finished" : "Not finished") + ")");
        }
    }

    List<Task> getTasks() {
        return tasks;
    }

    List<Task> getDoneTasks() {
        return tasks.stream().filter(Task::getDone).collect(Collectors.toList());
    }

    List<Task> getNotDoneTasks() {
        return tasks.stream().filter(e -> !e.getDone()).collect(Collectors.toList());
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