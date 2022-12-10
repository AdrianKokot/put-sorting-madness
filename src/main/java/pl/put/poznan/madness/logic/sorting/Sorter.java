package pl.put.poznan.madness.logic.sorting;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class Sorter {

  private final SortingStrategy sortingStrategy;

  public Sorter(SortingStrategy sortingStrategy) {
    this.sortingStrategy = sortingStrategy;
  }

  public <T extends Comparable<? super T>> void sort(T[] a) {
    sortingStrategy.sort(a);
  }

}
