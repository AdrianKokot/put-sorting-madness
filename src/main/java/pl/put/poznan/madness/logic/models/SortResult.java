package pl.put.poznan.madness.logic.models;

import pl.put.poznan.madness.logic.SortingAlgorithm;

public class SortResult<T> {
  public T[] result;
  public double elapsedMilliseconds;
  public SortingAlgorithm algorithm;

  public SortResult(SortingAlgorithm algorithm, double elapsedMilliseconds, T[] result) {
    this.algorithm = algorithm;
    this.elapsedMilliseconds = elapsedMilliseconds;
    this.result = result;
  }
}
