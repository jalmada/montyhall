package com.xaratustra.montyhall.entity;

import java.util.List;

public class SimulationViewResults {
    private List<String> runResults;
    private String simulationResult;
    private Long elapsedTime;

    public SimulationViewResults(List<String> runResults, String simulationResult, Long elapsedTime) {
        this.runResults = runResults;
        this.simulationResult = simulationResult;
        this.elapsedTime = elapsedTime;
    }

    public List<String> getResults(){
        return runResults;
    }

    public String getSimulationResult(){
        return simulationResult;
    }

    public Long getElapsedTime(){
        return elapsedTime;
    }

}
