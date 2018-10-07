package todo;

import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void testCompareTo() {
        Task task1 = new Task(0,"task1","projetc1", Date.valueOf("1212-12-12"), true);
        Task task2 = new Task(0,"task1","projetc1", Date.valueOf("1212-12-20"), true);
        assertTrue(task1.compareTo(task2) < 0);
    }
}