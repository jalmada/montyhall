package com.xaratustra.montyhall.service;

import java.util.List;

import com.xaratustra.montyhall.entity.ISimulationRequest;

public interface ISimulationService<T, R extends ISimulationRequest> {
    List<T> run(R request);
}
