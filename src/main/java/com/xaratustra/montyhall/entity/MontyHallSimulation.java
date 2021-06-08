package com.xaratustra.montyhall.entity;

import java.util.concurrent.Callable;

public class MontyHallSimulation implements Callable<MontyHallSimulationResult>, ISimulation<Boolean>{

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

        int revealed = 0;
        for(int x = 0; x < options.length; x++)
        {
            if(selected == x) continue;
            if(options[x] == 1)
            {
                revealed = x;
                break;
            }
        }

        int switchedTo = selected;
        for (int x = 0; x < options.length; x++)
        {
            if(x == selected) continue;
            if(x == revealed) continue;
            switchedTo = x;
            break;
        }
        
        return new MontyHallSimulationResult(selected, revealed, switchedTo, options[switchedTo] == 0);
    }
}
