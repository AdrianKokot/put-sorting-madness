package pl.put.poznan.madness.logic.models;

import java.util.List;

public class SortBenchmarkResult<T> {

  public List<T> result;
  public List<SortPerformance> algorithmsPerformance;

  public SortBenchmarkResult(List<T> result, List<SortPerformance> algorithmsPerformance) {
    this.result = result;
    this.algorithmsPerformance = algorithmsPerformance;
  }
}
