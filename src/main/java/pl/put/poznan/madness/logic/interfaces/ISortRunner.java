package pl.put.poznan.madness.logic.interfaces;

import java.util.List;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

public interface ISortRunner {
  <T extends Comparable<T>> SortBenchmarkResult<T> runBenchmark(
      List<SortingAlgorithm> algorithms, T[] data, SortDirection direction);

  <T extends Comparable<T>> SortBenchmarkResult<T> runBenchmark(
      List<SortingAlgorithm> algorithms, T[] data, SortDirection direction, int stopAfterIteration);
}
