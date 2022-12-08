package pl.put.poznan.madness.logic.interfaces;

import pl.put.poznan.madness.logic.SortingAlgorithm;
import pl.put.poznan.madness.logic.models.SortResult;

public interface ISortRunner {
  public <T extends Comparable<T>> SortResult<T> runSort(SortingAlgorithm algorithm, T[] data);
}
