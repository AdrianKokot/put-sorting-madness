package pl.put.poznan.madness.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.put.poznan.madness.logic.interfaces.ISortRunner;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.models.SortPerformance;
import pl.put.poznan.madness.logic.sorting.Sorter;
import pl.put.poznan.madness.logic.sorting.strategies.BubbleSort;
import pl.put.poznan.madness.logic.sorting.strategies.InsertionSort;
import pl.put.poznan.madness.logic.sorting.strategies.JavaSort;
import pl.put.poznan.madness.logic.sorting.strategies.QuickSort;
import pl.put.poznan.madness.logic.sorting.strategies.SelectionSort;
import pl.put.poznan.madness.logic.sorting.strategies.ShellSort;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class SortRunnerImpl implements ISortRunner {

  private static final Map<SortingAlgorithm, SortingStrategy> SORTING_ALGORITHMS_MAPPING = Map.of(
    SortingAlgorithm.QUICK_SORT, new QuickSort(),
    SortingAlgorithm.INSERTION_SORT, new InsertionSort(),
    SortingAlgorithm.JAVA_SORT, new JavaSort(),
    SortingAlgorithm.SELECTION_SORT, new SelectionSort(),
    SortingAlgorithm.SHELL_SORT, new ShellSort(),
    SortingAlgorithm.BUBBLE_SORT, new BubbleSort()
  );

  private static final Logger logger = LoggerFactory.getLogger(SortRunnerImpl.class);

  @Override
  public <T extends Comparable<T>> SortBenchmarkResult<T> runBenchmark(List<SortingAlgorithm> algorithms, T[] data) {
    List<SortPerformance> performances = new ArrayList<>();
    List<T> sortedData = null;

    logger.info(String.format("Running benchmarks for: %s", algorithms.toString()));

    for (SortingAlgorithm algorithm : algorithms) {
      T[] arr = data.clone();
      performances.add(getSortingPerformanceOfGivenAlgorithm(algorithm, arr));

      if (sortedData == null) {
        sortedData = new ArrayList<>(Arrays.asList(arr));
      }
    }
    return new SortBenchmarkResult<>(sortedData, performances);
  }

  private <T extends Comparable<? super T>> SortPerformance getSortingPerformanceOfGivenAlgorithm(SortingAlgorithm sortingAlgorithm, T[] data) {
    Sorter sorter = getProperSortingStrategy(sortingAlgorithm);
    Instant start = Instant.now();
    sorter.sort(data);
    Instant end = Instant.now();
    double elapsedMiliseconds = Duration.between(start, end).getNano() / 1000000.0;
    logger.info(String.format("Sorting data using %s took %s miliseconds", sortingAlgorithm, elapsedMiliseconds));
    return new SortPerformance(sortingAlgorithm, elapsedMiliseconds);
  }

  private Sorter getProperSortingStrategy(SortingAlgorithm sortingAlgorithm) {
    return new Sorter(SORTING_ALGORITHMS_MAPPING.get(sortingAlgorithm));
  }

}
