package com.xaratustra.montyhall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.xaratustra.montyhall.entity.MontyHallSimulation;
import com.xaratustra.montyhall.entity.MontyHallSimulationResult;

import org.springframework.stereotype.Service;

@Service
public class MontyHallSimulationService implements ISimulationService<MontyHallSimulationResult> {

    @Override
    public List<MontyHallSimulationResult> run(int times, int threadCount) {
        
        ExecutorService executorService = threadCount > 0 
            ? Executors.newFixedThreadPool(threadCount)
            : Executors.newCachedThreadPool();

        List<MontyHallSimulationResult> simulationResults = new ArrayList<MontyHallSimulationResult>();

        List<Future<MontyHallSimulationResult>> futureResults = new ArrayList<Future<MontyHallSimulationResult>>();

        for(int i = 0; i < times; i++)
        {
            Future<MontyHallSimulationResult> futureResult = executorService.submit(new MontyHallSimulation());
            futureResults.add(futureResult);
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.MINUTES);

            futureResults.stream().forEach(futureResult -> {
                    try{
                        simulationResults.add(futureResult.get());
                    } catch (ExecutionException ee) {}
                    catch (InterruptedException ie) {}
                }
            );
        }
        catch (InterruptedException e){ }

        return simulationResults;
    }
}
