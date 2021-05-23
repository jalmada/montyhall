package com.xaratustra.montyhall.service;

import com.xaratustra.montyhall.entity.SimulationResults;

public interface IMontyHallSimulationService {
    SimulationResults Run(int times);
}
