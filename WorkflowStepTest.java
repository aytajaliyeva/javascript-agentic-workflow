package agentic.workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import agentic.workflow.llm.SchemaType;
import agentic.workflow.llm.StructuredOutput;

public class WorkflowStepTest {

    @Test
    public void testExpectsStructuredOutput() {
        StructuredOutput output = new StructuredOutput(new SchemaType[]{SchemaType.INT});
        WorkflowStep step = new WorkflowStep("name", "prompt", "system", output);

        assertTrue(step.expectsStructuredOutput());
    }
}