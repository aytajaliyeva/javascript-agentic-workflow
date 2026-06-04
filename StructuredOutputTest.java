package agentic.workflow.llm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StructuredOutputTest {

    @Test
    public void testSize() {
        StructuredOutput output = new StructuredOutput(new SchemaType[]{
            SchemaType.INT,
            SchemaType.STRING
        });

        assertEquals(2, output.size());
    }

    @Test
    public void testContainsExistingType() {
        StructuredOutput output = new StructuredOutput(new SchemaType[]{
            SchemaType.INT,
            SchemaType.STRING
        });

        assertTrue(output.contains(SchemaType.INT));
    }
    @Test
    public void testContainsMissingType() {
        StructuredOutput output = new StructuredOutput(new SchemaType[]{
            SchemaType.INT
        });

        assertFalse(output.contains(SchemaType.STRING));
    }
}