package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class InsertionSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection) {
    for (int i = 1; i < a.length; i++) {
      int j = i;
      T o = a[i];
      if (SortDirection.ASC.equals(sortDirection)) {
        while ((j > 0) && o.compareTo(a[j - 1]) < 0) {
          a[j] = a[j - 1];
          j--;
        }
      } else {
        while ((j > 0) && o.compareTo(a[j - 1]) > 0) {
          a[j] = a[j - 1];
          j--;
        }
      }
      a[j] = o;
    }
  }

  @Override
  public <T extends Comparable<? super T>> void sort(
      T[] a, SortDirection sortDirection, int iterationsCount) {
    int iterations = 0;
    for (int i = 1; i < a.length && iterations < iterationsCount; i++, iterations++) {
      int j = i;
      T o = a[i];
      if (SortDirection.ASC.equals(sortDirection)) {
        while ((j > 0) && o.compareTo(a[j - 1]) < 0) {
          a[j] = a[j - 1];
          j--;
        }
      } else {
        while ((j > 0) && o.compareTo(a[j - 1]) > 0) {
          a[j] = a[j - 1];
          j--;
        }
      }
      a[j] = o;
    }
  }
}
