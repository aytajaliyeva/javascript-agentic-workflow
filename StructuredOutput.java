package agentic.workflow.llm;

public class StructuredOutput {
    private final SchemaType[] schemaTypes;

    public StructuredOutput(SchemaType[] schemaTypes) {
        if (schemaTypes == null || schemaTypes.length == 0) {
            throw new IllegalArgumentException();
        }

        this.schemaTypes = schemaTypes.clone();
    }

    public SchemaType[] getSchemaTypes() {
        return schemaTypes.clone();
    }

    public int size() {
        return schemaTypes.length;
    }

    public boolean contains(SchemaType type) {
        for (SchemaType t : schemaTypes) {
            if (t == type) {
                return true;
            }
        }

        return false;
    }
    @Override
    public String toString() {
        return "StructuredOutput" + java.util.Arrays.toString(schemaTypes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StructuredOutput other)) {
            return false;
        }

        return java.util.Arrays.equals(schemaTypes, other.schemaTypes);
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.hashCode(schemaTypes);
    }
}