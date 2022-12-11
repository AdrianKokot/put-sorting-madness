/*
package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.AbstractSort;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class MergeSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a) {
    // Generic array creation is not possible! (grr)
    Comparable[] b = new Comparable[a.length];
    sort(a, b, 0, a.length - 1);
  }

  private <T extends Comparable<? super T>> void sort(T[] a, T[] b, int low, int high) {
    if (low < high) {
      int middle = (low + high) / 2;
      sort(a, b, low, middle);
      sort(a, b, middle + 1, high);
      int s_high = middle + 1;
      int s_low = low;
      for (int i = low; i <= high; i++) {
        if ((s_low <= middle) && ((s_high > high) || (a[s_low].compareTo(a[s_high]) < 0)))
          b[i] = a[s_low++];
        else
          b[i] = a[s_high++];
      }
      for (int i = low; i <= high; i++)
        a[i] = b[i];
    }
  }
}
*/