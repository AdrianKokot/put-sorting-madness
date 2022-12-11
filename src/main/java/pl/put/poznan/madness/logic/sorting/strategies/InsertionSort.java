package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class InsertionSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a) {
    for (int i = 1; i < a.length; i++) {
      int j = i;
      T o = a[i];
      while ((j > 0) && o.compareTo(a[j - 1]) < 0) {
        a[j] = a[j - 1];
        j--;
      }
      a[j] = o;
    }
  }
}
