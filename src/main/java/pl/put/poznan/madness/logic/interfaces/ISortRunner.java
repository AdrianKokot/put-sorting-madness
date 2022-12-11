package pl.put.poznan.madness.logic.interfaces;

import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

import java.util.List;

public interface ISortRunner {

  <T extends Comparable<T>> SortBenchmarkResult<T> runBenchmark(List<SortingAlgorithm> algorithms, T[] data);
}
