import java.io.Serializable;

public class Task implements Serializable {
    private String description;
    private boolean done;

    Task() {
        description = null;
        done = false;
    }

    Task(final String description) {
        this.description = description;
        done = false;
    }

    boolean getDone() {
        return done;
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