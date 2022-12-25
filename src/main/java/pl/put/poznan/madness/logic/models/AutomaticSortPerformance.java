package pl.put.poznan.madness.logic.models;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

public class AutomaticSortPerformance extends SortPerformance {
  public SortingAlgorithm automaticallyDeterminedAlgorithm;

  public AutomaticSortPerformance(SortingAlgorithm algorithm, double elapsedMilliseconds) {
    super(SortingAlgorithm.AUTOMATIC, elapsedMilliseconds);
    this.automaticallyDeterminedAlgorithm = algorithm;
  }
}
