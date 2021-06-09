package com.xaratustra.montyhall.web;

import java.util.List;

import com.xaratustra.montyhall.entity.MontyHallSimulationRequest;
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

	private ISimulationService<MontyHallSimulationResult, MontyHallSimulationRequest> montyHallSimulationService;

	@Autowired
    public void setMontyHallSimulationService(ISimulationService<MontyHallSimulationResult, MontyHallSimulationRequest> montyHallSimulationService) {
        this.montyHallSimulationService = montyHallSimulationService;
    }

	@GetMapping("/simulate/times/{times}")
	public ResponseEntity<SimulationViewResults> simulate(@PathVariable("times")int times) {
		return simulate(times, -1, true, true);
	}

	@GetMapping("/simulate/times/{times}/threadCount/{threadCount}")
	public ResponseEntity<SimulationViewResults> simulate(
		@PathVariable("times")int times,
		@PathVariable("threadCount")int threadCount) {
		return simulate(times, threadCount, true, true);
	}

	@GetMapping("/simulate/times/{times}/threadCount/{threadCount}/willSwitch/{willSwitch}")
	public ResponseEntity<SimulationViewResults> simulate(
		@PathVariable("times")int times,
		@PathVariable("threadCount")int threadCount,
		@PathVariable("willSwitch") boolean willSwitch) {
		return simulate(times, threadCount, willSwitch, true);
	}

    @GetMapping("/simulate/times/{times}/threadCount/{threadCount}/willSwitch/{willSwitch}/willReveal/{willReveal}")
	public ResponseEntity<SimulationViewResults> simulate(
		@PathVariable("times")int times, 
		@PathVariable("threadCount")int threadCount,
		@PathVariable("willSwitch") boolean willSwitch,
		@PathVariable("willReveal") boolean willReveal
		) {

		MontyHallSimulationRequest request = new MontyHallSimulationRequest(times, threadCount, willSwitch, willReveal);

		StopWatch watch = new StopWatch();

		watch.start();
		List<MontyHallSimulationResult> simulationResults = montyHallSimulationService.run(request);
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
