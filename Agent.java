package agentic.workflow;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import agentic.workflow.llm.SchemaType;
import agentic.workflow.llm.StructuredOutput;

public class Agent {
    private String name;
    private String description;
    private final List<WorkflowStep> steps;

    public Agent(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.description = "Default description";
        this.steps = new ArrayList<>();
    }

    public Agent(String name, String description, List<WorkflowStep> steps) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (steps == null) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.description = description;
        this.steps = new ArrayList<>(steps);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<WorkflowStep> getSteps() {
        return List.copyOf(steps);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

    this.name = name;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException();
        }

    this.description = description;
    }

    public static Agent loadAgent(String path) throws IOException, WorkflowFormatException {
    List<String> lines = Files.readAllLines(Paths.get(path));
    if (lines.isEmpty()) {
        throw new WorkflowFormatException("Empty file");
    }

    String firstLine = lines.get(0);
    if (!firstLine.startsWith("AGENT:")) {
        throw new WorkflowFormatException("Missing AGENT");
    }

    String name = firstLine.substring(6).trim();
    List<WorkflowStep> steps = new ArrayList<>();
    for (int i = 1; i < lines.size(); i++) {
        if (lines.get(i).equals("STEP")) {
            String stepName = lines.get(i + 1).substring(5).trim();
            String prompt = lines.get(i + 2).substring(7).trim();
            String systemPrompt = lines.get(i + 3).substring(13).trim();
            String outputText = lines.get(i + 4).substring(7).trim();
            SchemaType type = SchemaType.valueOf(outputText);
            StructuredOutput output = new StructuredOutput(new SchemaType[]{type});
            WorkflowStep step = new WorkflowStep(stepName, prompt, systemPrompt, output);
            steps.add(step);
        }
    }

    return new Agent(name, "Loaded agent", steps);
    }
    public void addStep(WorkflowStep step) {
        if (step == null) {
            throw new IllegalArgumentException();
        }

    steps.add(step);
    }

    public int getStepCount() {
        return steps.size();
    }

    public WorkflowStep findStepByName(String stepName) {
        for (WorkflowStep step : steps) {
            if (step.getName().equals(stepName)) {
                return step;
            }
        }

    return null;
    }
    
    public void run() {
        for (WorkflowStep step : steps) {
            System.out.println(step.getName());
            System.out.println(step.simulateResponse());
        }
    }

    @SuppressWarnings("unused")
    private static WorkflowStep parseStep(java.io.BufferedReader reader)
            throws java.io.IOException, WorkflowFormatException {

        return null;
    }

    @Override
    public String toString() {
        return "Agent{name='" + name + "', steps=" + steps.size() + "}";
    }
}