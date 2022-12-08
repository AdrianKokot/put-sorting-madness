package pl.put.poznan.madness.logic.models;

import pl.put.poznan.madness.logic.SortingAlgorithm;

public class SortResult<T> {
  public T[] result;
  public long elapsedMilliseconds;
  public SortingAlgorithm usedAlgorithm;

  public SortResult(SortingAlgorithm alg, long time, T[] result) {
    this.usedAlgorithm = alg;
    this.elapsedMilliseconds = time;
    this.result = result;
  }
}
