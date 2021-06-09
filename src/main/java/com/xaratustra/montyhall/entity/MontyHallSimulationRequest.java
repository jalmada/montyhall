package com.xaratustra.montyhall.entity;

public class MontyHallSimulationRequest implements ISimulationRequest {

    private int times;
    private int threadCount;
    private boolean willSwitch;
    private boolean willReveal;

    public MontyHallSimulationRequest(
        int times,
        int threadCount,
        boolean willSwitch,
        boolean willReveal
    ){
        this.times = times;
        this.threadCount = threadCount;
        this.willSwitch = willSwitch;
        this.willReveal = willReveal;
    }

    @Override
    public int getTimes() {
        return times;
    }

    @Override
    public int getThreadCount() {
        return threadCount;
    }

    public boolean getWillSwitch() {
        return willSwitch;
    }

    public boolean getWillReveal() {
        return willReveal;
    }
}
