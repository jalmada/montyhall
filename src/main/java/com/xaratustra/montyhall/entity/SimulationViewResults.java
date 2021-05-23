package com.xaratustra.montyhall.entity;

import java.util.List;

public class SimulationViewResults {
    private List<String> runResults;
    private String simulationResult;

    public SimulationViewResults(List<String> runResults, String simulationResult) {
        this.runResults = runResults;
        this.simulationResult = simulationResult;
    }

    public List<String> getResults(){
        return runResults;
    }

    public String getSimulationResult(){
        return simulationResult;
    }

}
