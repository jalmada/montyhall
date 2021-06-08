package com.xaratustra.montyhall.web;

import java.util.ArrayList;
import java.util.List;

import com.xaratustra.montyhall.entity.MontyHallSimulationResult;
import com.xaratustra.montyhall.entity.SimulationViewResults;
import com.xaratustra.montyhall.service.ISimulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/montyhall")
public class MontyHallController {

	private ISimulationService<MontyHallSimulationResult> montyHallSimulationService;

	@Autowired
    public void setMontyHallSimulationService(ISimulationService<MontyHallSimulationResult> montyHallSimulationService) {
        this.montyHallSimulationService = montyHallSimulationService;
    }

	@GetMapping("/simulate/{times}")
	public ResponseEntity<SimulationViewResults> simulate(@PathVariable("times")int times) {
		return simulate(times, -1);
	}

    @GetMapping("/simulate/{times}/{threadCount}")
	public ResponseEntity<SimulationViewResults> simulate(@PathVariable("times")int times, @PathVariable("threadCount")int threadCount) {

		StopWatch watch = new StopWatch();

		watch.start();
		List<MontyHallSimulationResult> simulationResults = montyHallSimulationService.run(times, threadCount);
		watch.stop();

		long positiveResults = simulationResults.stream().filter(x -> x.getResult()).count();
		long negativeResults = simulationResults.stream().filter(x -> !x.getResult()).count();

		String totalSimulationResult = String.format("Final Results: Good: %s Bad: %s", positiveResults, negativeResults);

		SimulationViewResults simulationViewResults = new SimulationViewResults(
			simulationResults.stream().map(x -> x.toString()).toList(),
			totalSimulationResult,
			watch.getTotalTimeNanos()
			);

        return new ResponseEntity<SimulationViewResults>(simulationViewResults, HttpStatus.OK);
	}
}
