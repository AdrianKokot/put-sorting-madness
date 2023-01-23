package pl.put.poznan.madness.logic.interfaces;

import java.util.List;
import java.util.Optional;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

/**
 * ISortRunner is an interface for a class that runs a benchmark of sorting algorithms on a given
 * list of data.
 *
 * @see pl.put.poznan.madness.logic.models.SortBenchmarkResult
 * @see pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm
 */
public interface ISortRunner {
  /**
   * Runs a benchmark of sorting algorithms on a given list of data.
   *
   * @param algorithms The list of sorting algorithms to benchmark.
   * @param data The list of data to sort.
   * @param direction The direction to sort the data (ascending or descending).
   * @param stopAfterIteration An optional parameter, if provided, the sorting will be stopped after
   *     the given iteration.
   * @return A sort benchmark result object with information about the run
   */
  <T extends Comparable<T>> SortBenchmarkResult<T> runBenchmark(
      List<SortingAlgorithm> algorithms,
      T[] data,
      SortDirection direction,
      Optional<Integer> stopAfterIteration);
}
