package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.AbstractSort;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class QuickSort extends AbstractSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a) {
    sort(a, 0, a.length - 1);
  }

  private <T extends Comparable<? super T>> void sort(T[] a, int s_low, int s_high) {
    int low = s_low;
    int high = s_high;
    if (low == high - 1) {
      if (a[low].compareTo(a[high]) > 0) swap(a, low, high);
    } else if (low < high) {
      T pivot = a[(low + high) / 2];
      a[(low + high) / 2] = a[high];
      a[high] = pivot;
      while (low < high) {
        while ((a[low].compareTo(pivot) < 1) && (low < high)) low++;
        while ((pivot.compareTo(a[high]) < 1) && (low < high)) high--;
        if (low < high) swap(a, low, high);
      }
      a[s_high] = a[high];
      a[high] = pivot;
      sort(a, s_low, low - 1);
      sort(a, high + 1, s_high);
    }
  }
}
