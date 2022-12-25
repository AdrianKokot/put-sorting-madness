package pl.put.poznan.madness.logic.models;

import java.util.List;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;

public class SortBenchmarkResult<T> {

  public List<T> result;
  public List<SortPerformance> algorithmsPerformance;
  public SortDirection direction;

  public SortBenchmarkResult(
      List<T> result, List<SortPerformance> algorithmsPerformance, SortDirection direction) {
    this.result = result;
    this.algorithmsPerformance = algorithmsPerformance;
    this.direction = direction;
  }
}
