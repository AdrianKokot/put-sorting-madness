package pl.put.poznan.madness.logic;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.put.poznan.madness.logic.interfaces.ISortRunner;
import pl.put.poznan.madness.logic.models.AutomaticSortPerformance;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.models.SortPerformance;
import pl.put.poznan.madness.logic.sorting.Sorter;
import pl.put.poznan.madness.logic.sorting.strategies.BubbleSort;
import pl.put.poznan.madness.logic.sorting.strategies.InsertionSort;
import pl.put.poznan.madness.logic.sorting.strategies.JavaSort;
import pl.put.poznan.madness.logic.sorting.strategies.MergeSort;
import pl.put.poznan.madness.logic.sorting.strategies.QuickSort;
import pl.put.poznan.madness.logic.sorting.strategies.SelectionSort;
import pl.put.poznan.madness.logic.sorting.strategies.ShellSort;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

/**
 * Implementation of the {@link ISortRunner} interface that provides methods for benchmarking the
 * performance of different sorting algorithms.
 *
 * <p>This class provides a method for running a benchmark on a given list of sorting algorithms and
 * a data set, and returning a {@link SortBenchmarkResult} object that contains the sorted data and
 * the performance results of each sorting algorithm.
 *
 * <p>The sorting algorithms that can be benchmarked are defined in the {@link SortingAlgorithm}
 * enum and their implementation is provided by the {@link SortingStrategy} interface.
 *
 * <p>This class uses the {@link Sorter} class to perform the actual sorting using the provided
 * sorting strategies.
 */
@Component
public class SortRunnerImpl implements ISortRunner {

  /**
   * Mapping of {@link SortingAlgorithm} values to their corresponding {@link SortingStrategy}
   * implementations.
   */
  private static final Map<SortingAlgorithm, SortingStrategy> SORTING_ALGORITHMS_MAPPING =
      Map.of(
          SortingAlgorithm.QUICK_SORT, new QuickSort(),
          SortingAlgorithm.INSERTION_SORT, new InsertionSort(),
          SortingAlgorithm.JAVA_SORT, new JavaSort(),
          SortingAlgorithm.SELECTION_SORT, new SelectionSort(),
          SortingAlgorithm.SHELL_SORT, new ShellSort(),
          SortingAlgorithm.BUBBLE_SORT, new BubbleSort(),
          SortingAlgorithm.MERGE_SORT, new MergeSort());

  /** Logger for this class. */
  private static final Logger logger = LoggerFactory.getLogger(SortRunnerImpl.class);

  /**
   * Runs a benchmark on the given list of sorting algorithms and data set, and returns a {@link
   * SortBenchmarkResult} object containing the sorted data and the performance results of each
   * sorting algorithm.
   *
   * @param algorithms a list of {@link SortingAlgorithm} values representing the sorting algorithms
   *     to be benchmarked
   * @param data the data set to be sorted
   * @param direction the sort order
   * @param <T> the type of the elements in the data set, must implement {@link Comparable}
   * @return a {@link SortBenchmarkResult} object containing the sorted data and the performance
   *     results of each sorting algorithm
   */
  @Override
  public <T extends Comparable<T>> SortBenchmarkResult<T> runBenchmark(
      List<SortingAlgorithm> algorithms,
      T[] data,
      SortDirection direction,
      Optional<Integer> stopAfterIteration) {
    List<SortPerformance> performances = new ArrayList<>();
    List<T> sortedData = null;

    logger.info(
        String.format(
            "Running benchmarks for: %s [%s]", algorithms.toString(), direction.toString()));

    for (SortingAlgorithm algorithm : algorithms) {
      T[] arr = data.clone();
      performances.add(
          getSortingPerformanceOfGivenAlgorithm(algorithm, arr, direction, stopAfterIteration));

      if (sortedData == null) {
        sortedData = new ArrayList<>(Arrays.asList(arr));
      }
    }
    return new SortBenchmarkResult<>(sortedData, performances, direction);
  }

  /**
   * Runs the actual sort while timing it, and returns a {@link SortPerformance} of given {@link
   * SortingAlgorithm}
   *
   * @param sortingAlgorithm a {@link SortingAlgorithm} strategy to do the sort
   * @param data the data set to be sorted
   * @param <T> the type of the elements in the data set, must implement {@link Comparable}
   * @return a {@link SortPerformance} object containing information about elapsed time and an
   *     algorithm to do the test
   */
  private <T extends Comparable<? super T>> SortPerformance getSortingPerformanceOfGivenAlgorithm(
      SortingAlgorithm sortingAlgorithm,
      T[] data,
      SortDirection sortDirection,
      Optional<Integer> stopAfterIteration) {
    SortPerformance performance;
    Sorter sorter;

    if (sortingAlgorithm == SortingAlgorithm.AUTOMATIC) {
      // TODO #37 - add detecting best algorithm
      sorter = getProperSortingStrategy(SortingAlgorithm.JAVA_SORT);
      performance = new AutomaticSortPerformance(SortingAlgorithm.JAVA_SORT, 0);
    } else {
      sorter = getProperSortingStrategy(sortingAlgorithm);
      performance = new SortPerformance(sortingAlgorithm, 0);
    }

    Instant start = Instant.now();
    if (stopAfterIteration.isPresent()) {
      sorter.sort(data, sortDirection, stopAfterIteration.get());
    } else {
      sorter.sort(data, sortDirection);
    }
    Instant end = Instant.now();
    performance.elapsedMilliseconds = Duration.between(start, end).getNano() / 1000000.0;
    logger.debug(
        String.format(
            "Sorting data using %s took %s milliseconds",
            performance.algorithm, performance.elapsedMilliseconds));

    return performance;
  }

  /**
   * Accesses the proper {@link Sorter} object based on given enum value
   *
   * @param sortingAlgorithm a {@link SortingAlgorithm} strategy to do the sort
   * @return a proper {@link Sorter} capable of sorting with the given strategy
   */
  private Sorter getProperSortingStrategy(SortingAlgorithm sortingAlgorithm) {
    return new Sorter(SORTING_ALGORITHMS_MAPPING.get(sortingAlgorithm));
  }
}
