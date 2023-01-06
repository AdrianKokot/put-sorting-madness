package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.AbstractSort;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class SelectionSort extends AbstractSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection) {
    for (int i = 0; i < a.length; i++) {
      int min = i;
      for (int j = i + 1; j < a.length; j++) {
        if (SortDirection.ASC.equals(sortDirection)) {
          if (a[j].compareTo(a[min]) < 0) min = j;
        } else {
          if (a[j].compareTo(a[min]) > 0) min = j;
        }
      }
      swap(a, min, i);
    }
  }
}
