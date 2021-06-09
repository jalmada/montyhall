package com.xaratustra.montyhall.entity;

import java.util.concurrent.Callable;

public class MontyHallSimulation implements Callable<MontyHallSimulationResult>, ISimulation<Boolean>{

    boolean willSwitch = true;
    boolean willReveal = true;

    public MontyHallSimulation(){

    }

    public MontyHallSimulation(boolean willSwitch, boolean willReveal){
        this.willSwitch = willSwitch;
        this.willReveal = willReveal;
    }

    @Override
    public MontyHallSimulationResult call() throws Exception {
        return run();
    }

    @Override
    public MontyHallSimulationResult run() {

        int[] options = new int[3];
        options[0] = ((int)(Math.random() * 10)) % 2;
        options[1] = options[0] == 0 ? 1 : ((int)(Math.random() * 10)) % 2;
        options[2] = options[0] == 1 &&  options[1] == 1 ? 0 : 1;

        int selected = ((int)(Math.random() * 10)) % 3;

        int revealed = -1;
        if(willReveal){
            for(int x = 0; x < options.length; x++)
            {
                if(selected == x) continue;
                if(options[x] == 1)
                {
                    revealed = x;
                    break;
                }
            }
        }   

        int switchedTo = selected;
        if(willSwitch){
            if(willReveal){
                for (int x = 0; x < options.length; x++)
                {
                    if(x == selected) continue;
                    if(x == revealed) continue;
                    switchedTo = x;
                    break;
                }
            } else {
                while (switchedTo == selected) {
                    switchedTo = ((int)(Math.random() * 10)) % 2;
                } 
            }
        }
        
        return new MontyHallSimulationResult(selected, revealed, switchedTo, options[switchedTo] == 0);
    }
}
