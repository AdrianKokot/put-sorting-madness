package pl.put.poznan.madness.logic.interfaces;

import java.util.List;

import pl.put.poznan.madness.logic.SortingAlgorithm;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;

public interface ISortRunner {
  public <T extends Comparable<T>> SortBenchmarkResult<T> runBenchmark(List<SortingAlgorithm> algorithms, T[] data);
}
