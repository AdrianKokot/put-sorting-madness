package pl.put.poznan.madness.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.put.poznan.madness.logic.interfaces.ISortRunner;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.models.SortPerformance;
import pl.put.poznan.madness.logic.sorting.Sorter;
import pl.put.poznan.madness.logic.sorting.strategies.*;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link ISortRunner} interface that provides methods for
 * benchmarking the performance
 * of different sorting algorithms.
 *
 * This class provides a method for running a benchmark on a given list of
 * sorting algorithms and a data set,
 * and returning a {@link SortBenchmarkResult} object that contains the sorted
 * data and the performance results
 * of each sorting algorithm.
 *
 * The sorting algorithms that can be benchmarked are defined in the
 * {@link SortingAlgorithm} enum and
 * their implementation is provided by the {@link SortingStrategy} interface.
 *
 * This class uses the {@link Sorter} class to perform the actual sorting using
 * the provided sorting strategies.
 */
@Component
public class SortRunnerImpl implements ISortRunner {

  /**
   * Mapping of {@link SortingAlgorithm} values to their corresponding
   * {@link SortingStrategy} implementations.
   */
  private static final Map<SortingAlgorithm, SortingStrategy> SORTING_ALGORITHMS_MAPPING = Map.of(
      SortingAlgorithm.QUICK_SORT, new QuickSort(),
      SortingAlgorithm.INSERTION_SORT, new InsertionSort(),
      SortingAlgorithm.JAVA_SORT, new JavaSort(),
      SortingAlgorithm.SELECTION_SORT, new SelectionSort(),
      SortingAlgorithm.SHELL_SORT, new ShellSort(),
      SortingAlgorithm.BUBBLE_SORT, new BubbleSort(),
      SortingAlgorithm.MERGE_SORT, new MergeSort());

  /**
   * Logger for this class.
   */
  private static final Logger logger = LoggerFactory.getLogger(SortRunnerImpl.class);

  /**
   * Runs a benchmark on the given list of sorting algorithms and data set, and
   * returns a {@link SortBenchmarkResult}
   * object containing the sorted data and the performance results of each sorting
   * algorithm.
   *
   * @param algorithms a list of {@link SortingAlgorithm} values representing the
   *                   sorting algorithms to be benchmarked
   * @param data       the data set to be sorted
   * @param direction  the sort order
   * @param <T>        the type of the elements in the data set, must implement
   *                   {@link Comparable}
   * @return a {@link SortBenchmarkResult} object containing the sorted data and
   *         the performance results of each sorting algorithm
   */
  @Override
  public <T extends Comparable<T>> SortBenchmarkResult<T> runBenchmark(List<SortingAlgorithm> algorithms, T[] data,
      SortDirection direction) {
    List<SortPerformance> performances = new ArrayList<>();
    List<T> sortedData = null;

    logger.info(String.format("Running benchmarks for: %s [%s]", algorithms.toString(), direction.toString()));

    for (SortingAlgorithm algorithm : algorithms) {
      T[] arr = data.clone();
      performances.add(getSortingPerformanceOfGivenAlgorithm(algorithm, arr));

      if (sortedData == null) {
        sortedData = new ArrayList<>(Arrays.asList(arr));
      }
    }
    return new SortBenchmarkResult<>(sortedData, performances, direction);
  }

  /**
   * Runs the actual sort while timing it, and returns a {@link SortPerformance}
   * of given {@link SortingAlgorithm}
   *
   * @param sortingAlgorithm a {@link SortingAlgorithm} strategy to do the sort
   * @param data             the data set to be sorted
   * @param <T>              the type of the elements in the data set, must
   *                         implement {@link Comparable}
   * @return a {@link SortPerformance} object containing information about elapsed
   *         time and an algorithm to do the test
   */
  private <T extends Comparable<? super T>> SortPerformance getSortingPerformanceOfGivenAlgorithm(
      SortingAlgorithm sortingAlgorithm, T[] data) {
    Sorter sorter = getProperSortingStrategy(sortingAlgorithm);
    Instant start = Instant.now();
    sorter.sort(data);
    Instant end = Instant.now();
    double elapsedMilliseconds = Duration.between(start, end).getNano() / 1000000.0;
    logger.debug(String.format("Sorting data using %s took %s milliseconds", sortingAlgorithm, elapsedMilliseconds));
    return new SortPerformance(sortingAlgorithm, elapsedMilliseconds);
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
