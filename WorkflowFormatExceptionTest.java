package agentic.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkflowFormatExceptionTest {

    @Test
    public void testMessageConstructor() {
        WorkflowFormatException ex = new WorkflowFormatException("error");

        assertEquals("error", ex.getMessage());
    }
}