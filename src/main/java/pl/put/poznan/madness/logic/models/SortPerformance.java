package pl.put.poznan.madness.logic.models;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

public class SortPerformance {

  public double elapsedMilliseconds;
  public SortingAlgorithm algorithm;

  public SortPerformance(SortingAlgorithm algorithm, double elapsedMilliseconds) {
    this.algorithm = algorithm;
    this.elapsedMilliseconds = elapsedMilliseconds;
  }
}
