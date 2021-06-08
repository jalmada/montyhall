package com.xaratustra.montyhall.entity;

public interface ISimulationResult<T> {
    public T getResult();
    public String toString();
    public String toString(boolean verbose);
}
