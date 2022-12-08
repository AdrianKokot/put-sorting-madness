package pl.put.poznan.madness.logic;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.put.poznan.madness.logic.interfaces.ISortRunner;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.models.SortPerformance;

@Component
public class SortRunnerImpl implements ISortRunner {
  @Override
  public <T extends Comparable<T>> SortBenchmarkResult<T> runBenchmark(List<SortingAlgorithm> algorithms, T[] data) {
    List<SortPerformance> performances = new ArrayList<>();
    List<T> sortedData = null;

    for (SortingAlgorithm algorithm : algorithms) {
      T[] arr = data.clone();

      Sort sorter = new JavaSort();

      Instant start = Instant.now();
      sorter.sort(arr);
      Instant end = Instant.now();

      if(sortedData == null) {
        sortedData = new ArrayList<>(Arrays.asList(arr));
      }

      performances.add(new SortPerformance(algorithm, Duration.between(start, end).getNano()/1000000.0));
    }

    return new SortBenchmarkResult<>(sortedData, performances);
  }

}
