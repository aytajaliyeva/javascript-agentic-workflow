package agentic.workflow;

import agentic.workflow.llm.SchemaType;
import agentic.workflow.llm.StructuredOutput;

public class WorkflowStep {

    private String name;
    private String prompt;
    private String systemPrompt;
    private StructuredOutput structuredOutput;

    public WorkflowStep(String name, String prompt, String systemPrompt, StructuredOutput structuredOutput) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (systemPrompt == null || systemPrompt.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (structuredOutput == null) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.prompt = prompt;
        this.systemPrompt = systemPrompt;
        this.structuredOutput = structuredOutput;
    }

    public String getName() {
        return name;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getSystemPrompt() {
        return systemPrompt;
    }

    public StructuredOutput getStructuredOutput() {
        return structuredOutput;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

    this.name = name;
    }

    public void setPrompt(String prompt) {
        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException();
        }

    this.prompt = prompt;
    }

    public void setSystemPrompt(String systemPrompt) {
        if (systemPrompt == null || systemPrompt.isBlank()) {
            throw new IllegalArgumentException();
        }

    this.systemPrompt = systemPrompt;
    }

    public boolean expectsStructuredOutput() {
        return structuredOutput != null;
    }

    public String simulateResponse() {
        SchemaType type = structuredOutput.getSchemaTypes()[0];

        switch (type) {
            case INT:
                return "0";
            case STRING:
                return "sample";
            case BOOLEAN:
                return "true";
            case LIST_INT:
                return "[1,2,3]";
            case LIST_STRING:
                return "[\"a\",\"b\",\"c\"]";
            case MAP_STRING_STRING:
                return "{\"key1\":\"value1\",\"key2\":\"value2\"}";
            default:
                return "";
        }
    }

    public void setStructuredOutput(StructuredOutput structuredOutput) {
        this.structuredOutput = structuredOutput;
    }

    @Override
    public String toString() {
        return "WorkflowStep{name='" + name + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof WorkflowStep other)) {
            return false;
        }

        return name.equals(other.name)
                && prompt.equals(other.prompt)
                && systemPrompt.equals(other.systemPrompt);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + prompt.hashCode() + systemPrompt.hashCode();
    }
}