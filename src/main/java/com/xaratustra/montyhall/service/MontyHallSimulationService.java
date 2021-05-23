package com.xaratustra.montyhall.service;

import com.xaratustra.montyhall.entity.RunResult;
import com.xaratustra.montyhall.entity.SimulationResults;

import org.springframework.stereotype.Service;

@Service
public class MontyHallSimulationService implements IMontyHallSimulationService{

    @Override
    public SimulationResults Run(int times) {
        
        SimulationResults simulationResults = new SimulationResults();

        for(int i = 0; i < times; i++)
        {
            simulationResults.addRunResult(RunSingleSimulation());
        }

        return simulationResults;
    }

    private RunResult RunSingleSimulation(){

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

        int switchedTo = 0;
        for (int x = 0; x < options.length; x++)
        {
            if(x == selected) continue;
            if(x == revealed) continue;
            switchedTo = x;
            break;
        }
        
        return new RunResult(selected, revealed, switchedTo, options[switchedTo] == 0);
    }
}
