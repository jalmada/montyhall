package com.xaratustra.montyhall.service;

import java.util.List;

public interface ISimulationService<T> {
    List<T> run(int times, int threadCount);
}
