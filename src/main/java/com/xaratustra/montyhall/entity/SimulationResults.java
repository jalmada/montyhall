package com.xaratustra.montyhall.entity;

import java.util.ArrayList;

public class SimulationResults {
    private ArrayList<RunResult> runResults;

    public SimulationResults() {
        runResults = new ArrayList<RunResult>();
    }

    public ArrayList<RunResult> getRunResults(){
        return runResults;
    }

    public void addRunResult(RunResult runResult){
        runResults.add(runResult);
    }

    public long getPositiveResults(){
        return runResults.stream().filter(x -> x.getResult()).count();
    }

    public long getNegativeResults(){
        return runResults.stream().filter(x -> !x.getResult()).count();
    }

    public int getSimulationCount(){
        return runResults.size();
    }

    public String toString(){
        return String.format("Final Results: Good: %s Bad: %s", getPositiveResults(), getNegativeResults());
    }
}
