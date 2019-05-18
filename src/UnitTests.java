import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTests {
    @Test
    public void testAdd() {
        BaseTasks base = new BaseTasks();
        base.addTask("test");
        assertEquals(1, base.getTasks().size());
        assertEquals(1, base.getNotDoneTasks().size());
        assertEquals(0, base.getDoneTasks().size());
        assertEquals("test", base.getTasks().get(0).getDescription());
    }

    @Test
    public void testRemove() {
        BaseTasks base = new BaseTasks();
        base.addTask("test1");
        base.addTask("test2");
        base.addTask("test3");
        assertEquals(3, base.getTasks().size());
        base.removeTask("3");
        assertEquals(2, base.getTasks().size());
        assertEquals("test1", base.getTasks().get(0).getDescription());
        assertEquals("test2", base.getTasks().get(1).getDescription());
        base.removeTask("1");
        assertEquals(1, base.getTasks().size());
        assertEquals("test2", base.getTasks().get(0).getDescription());
    }

    @Test
    public void testGet() {
        BaseTasks base = new BaseTasks();
        assertEquals(0, base.getTasks().size());
        assertEquals(0, base.getNotDoneTasks().size());
        assertEquals(0, base.getDoneTasks().size());
    }

    @Test
    public void testSetAndUnset() {
        BaseTasks base = new BaseTasks();
        base.addTask("test1");
        base.addTask("test2");
        base.addTask("test3");
        assertEquals(3, base.getNotDoneTasks().size());
        base.setDoneTask("3");
        assertEquals(1, base.getDoneTasks().size());
        assertEquals("test3", base.getDoneTasks().get(0).getDescription());
        base.unsetDoneTask("3");
        assertEquals(0, base.getDoneTasks().size());
    }
}