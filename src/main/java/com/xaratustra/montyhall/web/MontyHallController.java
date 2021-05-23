package com.xaratustra.montyhall.web;

import com.xaratustra.montyhall.entity.SimulationResults;
import com.xaratustra.montyhall.entity.SimulationViewResults;
import com.xaratustra.montyhall.service.IMontyHallSimulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/montyhall")
public class MontyHallController {

	private IMontyHallSimulationService montyHallSimulationService;

	@Autowired
    public void setMontyHallSimulationService(IMontyHallSimulationService montyHallSimulationService) {
        this.montyHallSimulationService = montyHallSimulationService;
    }

    @GetMapping("/simulate/{times}")
	public ResponseEntity<SimulationViewResults> simulate(@PathVariable("times")int times) {
		SimulationResults simulationResults = montyHallSimulationService.Run(times);

		SimulationViewResults simulationViewResults = new SimulationViewResults(
			simulationResults.getRunResults().stream().map(x -> x.toString()).toList(),
			simulationResults.toString()
			);

        return new ResponseEntity<SimulationViewResults>(simulationViewResults, HttpStatus.OK);
	}
}
